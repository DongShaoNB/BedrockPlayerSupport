package cc.dsnb.bedrockplayersupport.form.basic

import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import com.Zrips.CMI.CMI
import org.bukkit.ChatColor
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
                ChatColor.translateAlternateColorCodes(
                    '&', BedrockPlayerSupport.langConfigManager.getConfigData().homeFormTitle()
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