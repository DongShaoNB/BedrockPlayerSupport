package cc.dsnb.bedrockplayersupport.form.basic

import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import cc.dsnb.bedrockplayersupport.util.StringUtil
import net.william278.huskhomes.api.HuskHomesAPI
import org.bukkit.entity.Player
import org.geysermc.cumulus.form.SimpleForm

/**
 * @author DongShaoNB
 */
class HuskHomesForm {

    fun sendPublicHomeForm(player: Player) {
        val publicHomes = HuskHomesAPI.getInstance().publicHomes
        val form = SimpleForm.builder()
            .title(
                StringUtil.formatTextToString(
                    player,
                    BedrockPlayerSupport.langConfigManager.getConfigData().publicHomeFormTitle()
                )
            )
            .validResultHandler { simpleFormResponse ->
                player.chat("/phome " + simpleFormResponse.clickedButton().text())
            }
        publicHomes.thenAccept { homeList ->
            homeList.forEach { form.button(it.name) }
            BedrockPlayerSupport.floodgateApi.sendForm(player.uniqueId, form)
        }
    }

    fun sendDelHomeForm(player: Player) {
        val onlineUser = HuskHomesAPI.getInstance().adaptUser(player)
        val userHomes = HuskHomesAPI.getInstance().getUserHomes(onlineUser)
        val form = SimpleForm.builder()
            .title(
                StringUtil.formatTextToString(
                    player,
                    BedrockPlayerSupport.langConfigManager.getConfigData().delHomeFormTitle()
                )
            )
            .validResultHandler { simpleFormResponse ->
                player.chat("/delhome " + simpleFormResponse.clickedButton().text())
            }
        userHomes.thenAccept { homeList ->
            homeList.forEach { form.button(it.name) }
            BedrockPlayerSupport.floodgateApi.sendForm(player.uniqueId, form)
        }
    }

    fun sendGoHomeForm(player: Player) {
        val onlineUser = HuskHomesAPI.getInstance().adaptUser(player)
        val userHomes = HuskHomesAPI.getInstance().getUserHomes(onlineUser)
        val form = SimpleForm.builder()
            .title(
                StringUtil.formatTextToString(
                    player,
                    BedrockPlayerSupport.langConfigManager.getConfigData().goHomeFormTitle()
                )
            )
            .validResultHandler { simpleFormResponse ->
                player.chat("/home " + simpleFormResponse.clickedButton().text())
            }
        userHomes.thenAccept { homeList ->
            homeList.forEach { form.button(it.name) }
            BedrockPlayerSupport.floodgateApi.sendForm(player.uniqueId, form)
        }
    }

    fun sendWarpForm(player: Player) {
        val warps = HuskHomesAPI.getInstance().warps
        val form = SimpleForm.builder()
            .title(
                StringUtil.formatTextToString(
                    player,
                    BedrockPlayerSupport.langConfigManager.getConfigData().warpFormTitle()
                )
            )
            .validResultHandler { simpleFormResponse ->
                player.chat("/warp " + simpleFormResponse.clickedButton().text())
            }
        warps.thenAccept { warpList ->
            warpList.forEach { form.button(it.name) }
            BedrockPlayerSupport.floodgateApi.sendForm(player.uniqueId, form)
        }
    }

}