package cc.dsnb.bedrockplayersupport.form.basic

import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import cc.dsnb.bedrockplayersupport.util.StringUtil
import com.Zrips.CMI.CMI
import org.bukkit.entity.Player
import org.geysermc.cumulus.form.SimpleForm

/**
 * @author DongShaoNB
 */
class CMIForm {

    fun sendDelHomeForm(player: Player) {
        val cmiUser = CMI.getInstance().playerManager.getUser(player)
        val playerHomesList = cmiUser.homes
        val form = SimpleForm.builder()
            .title(
                StringUtil.formatTextToString(
                    player,
                    BedrockPlayerSupport.langConfigManager.getConfigData().delHomeFormTitle()
                )
            )
            .validResultHandler { simpleFormResponse ->
                player.chat("/removehome ${simpleFormResponse.clickedButton().text()}")
            }
        playerHomesList.forEach { form.button(it.key) }
        BedrockPlayerSupport.floodgateApi.sendForm(player.uniqueId, form)
    }

    fun sendGoHomeForm(player: Player) {
        val cmiUser = CMI.getInstance().playerManager.getUser(player)
        val playerHomesList = cmiUser.homes
        val form = SimpleForm.builder()
            .title(
                StringUtil.formatTextToString(
                    player,
                    BedrockPlayerSupport.langConfigManager.getConfigData().goHomeFormTitle()
                )
            )
            .validResultHandler { simpleFormResponse ->
                player.chat("/home ${simpleFormResponse.clickedButton().text()}")
            }
        playerHomesList.forEach { form.button(it.key) }
        BedrockPlayerSupport.floodgateApi.sendForm(player.uniqueId, form)
    }

    fun sendWarpForm(player: Player) {
        val warps = CMI.getInstance().warpManager.warps
        val form = SimpleForm.builder()
            .title(
                StringUtil.formatTextToString(
                    player,
                    BedrockPlayerSupport.langConfigManager.getConfigData().warpFormTitle()
                )
            )
            .validResultHandler { simpleFormResponse ->
                player.chat("/warp ${simpleFormResponse.clickedButton().text()}")
            }
        warps.forEach { form.button(it.key) }
        BedrockPlayerSupport.floodgateApi.sendForm(player.uniqueId, form)
    }

    fun sendKitForm(player: Player) {
        val kits = CMI.getInstance().kitsManager.kitMap
        val form = SimpleForm.builder()
            .title(
                StringUtil.formatTextToString(
                    player,
                    BedrockPlayerSupport.langConfigManager.getConfigData().kitFormTitle()
                )
            )
            .validResultHandler { simpleFormResponse ->
                player.chat("/kit ${simpleFormResponse.clickedButton().text()}")
            }
        kits.forEach { form.button(it.key) }
        BedrockPlayerSupport.floodgateApi.sendForm(player.uniqueId, form)
    }

}