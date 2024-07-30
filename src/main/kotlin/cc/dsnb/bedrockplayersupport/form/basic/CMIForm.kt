package cc.dsnb.bedrockplayersupport.form.basic

import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import com.Zrips.CMI.CMI
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
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
                LegacyComponentSerializer.legacySection().serialize(
                    BedrockPlayerSupport.miniMessage.deserialize(
                        BedrockPlayerSupport.langConfigManager.getConfigData().delHomeFormTitle()
                    )
                )
            )
            .validResultHandler { simpleFormResponse ->
                player.chat("/delhome ${simpleFormResponse.clickedButton().text()}")
            }
        for (homeName in playerHomesList.keys) {
            form.button(homeName)
        }
        BedrockPlayerSupport.floodgateApi.sendForm(player.uniqueId, form)
    }

    fun sendGoHomeForm(player: Player) {
        val cmiUser = CMI.getInstance().playerManager.getUser(player)
        val playerHomesList = cmiUser.homes
        val form = SimpleForm.builder()
            .title(
                LegacyComponentSerializer.legacySection().serialize(
                    BedrockPlayerSupport.miniMessage.deserialize(
                        BedrockPlayerSupport.langConfigManager.getConfigData().goHomeFormTitle()
                    )
                )
            )
            .validResultHandler { simpleFormResponse ->
                player.chat("/home ${simpleFormResponse.clickedButton().text()}")
            }
        for (homeName in playerHomesList.keys) {
            form.button(homeName)
        }
        BedrockPlayerSupport.floodgateApi.sendForm(player.uniqueId, form)
    }

    fun sendWarpForm(player: Player) {
        val warps = CMI.getInstance().warpManager.warps.keys
        val form = SimpleForm.builder()
            .title(
                LegacyComponentSerializer.legacySection().serialize(
                    BedrockPlayerSupport.miniMessage.deserialize(
                        BedrockPlayerSupport.langConfigManager.getConfigData().warpFormTitle()
                    )
                )
            )
            .validResultHandler { simpleFormResponse ->
                player.chat("/warp ${simpleFormResponse.clickedButton().text()}")
            }
        warps.forEach { form.button(it) }
        BedrockPlayerSupport.floodgateApi.sendForm(player.uniqueId, form)
    }

}