package cc.dsnb.bedrockplayersupport.form.basic

import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.william278.huskhomes.api.HuskHomesAPI
import org.bukkit.entity.Player
import org.geysermc.cumulus.form.SimpleForm

/**
 * @author DongShaoNB
 */
class HuskHomesForm {

    fun sendDelHomeForm(player: Player) {
        val onlineUser = HuskHomesAPI.getInstance().adaptUser(player)
        val huskHomes = HuskHomesAPI.getInstance().getUserHomes(onlineUser)
        val form = SimpleForm.builder()
            .title(
                LegacyComponentSerializer.legacySection().serialize(
                    BedrockPlayerSupport.miniMessage.deserialize(
                        BedrockPlayerSupport.langConfigManager.getConfigData().delHomeFormTitle()
                    )
                )
            )
            .validResultHandler { simpleFormResponse ->
                player.chat("/delhome " + simpleFormResponse.clickedButton().text())
            }
        huskHomes.thenAccept { homeList ->
            for (home in homeList) {
                form.button(home.name)
            }
            BedrockPlayerSupport.floodgateApi.sendForm(player.uniqueId, form)
        }
    }

    fun sendGoHomeForm(player: Player) {
        val onlineUser = HuskHomesAPI.getInstance().adaptUser(player)
        val huskHomes = HuskHomesAPI.getInstance().getUserHomes(onlineUser)
        val form = SimpleForm.builder()
            .title(
                LegacyComponentSerializer.legacySection().serialize(
                    BedrockPlayerSupport.miniMessage.deserialize(
                        BedrockPlayerSupport.langConfigManager.getConfigData().goHomeFormTitle()
                    )
                )
            )
            .validResultHandler { simpleFormResponse ->
                player.chat("/home " + simpleFormResponse.clickedButton().text())
            }
        huskHomes.thenAccept { homeList ->
            for (home in homeList) {
                form.button(home.name)
            }
            BedrockPlayerSupport.floodgateApi.sendForm(player.uniqueId, form)
        }
    }

    fun sendWarpForm(player: Player) {
        val warps = HuskHomesAPI.getInstance().warps
        val form = SimpleForm.builder()
            .title(
                LegacyComponentSerializer.legacySection().serialize(
                    BedrockPlayerSupport.miniMessage.deserialize(
                        BedrockPlayerSupport.langConfigManager.getConfigData().warpFormTitle()
                    )
                )
            )
            .validResultHandler { simpleFormResponse ->
                player.chat("/warp " + simpleFormResponse.clickedButton().text())
            }
        warps.thenAccept { warpList ->
            warpList.forEach { form.button(it.name) }
        }
        BedrockPlayerSupport.floodgateApi.sendForm(player.uniqueId, form)
    }

}