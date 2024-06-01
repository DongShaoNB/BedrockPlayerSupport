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

    fun sendHomeForm(player: Player) {
        val cmiUser = CMI.getInstance().playerManager.getUser(player)
        val playerHomesList = cmiUser.homes
        val form = SimpleForm.builder()
            .title(
                LegacyComponentSerializer.legacySection().serialize(
                    BedrockPlayerSupport.miniMessage.deserialize(
                        BedrockPlayerSupport.langConfigManager.getConfigData().homeFormTitle()
                    )
                )
            )
            .validResultHandler { simpleFormResponse ->
                player.chat("/home " + simpleFormResponse.clickedButton().text())
            }
        for (homeName in playerHomesList.keys) {
            form.button(homeName)
        }
        BedrockPlayerSupport.floodgateApi.sendForm(player.uniqueId, form)
    }

}