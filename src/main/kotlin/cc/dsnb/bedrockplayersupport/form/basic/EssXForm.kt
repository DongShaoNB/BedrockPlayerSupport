package cc.dsnb.bedrockplayersupport.form.basic

import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import cc.dsnb.bedrockplayersupport.util.StringUtil
import com.earth2me.essentials.Essentials
import com.earth2me.essentials.User
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.geysermc.cumulus.form.SimpleForm

/**
 * @author DongShaoNB
 */
class EssXForm : BasicForm {

    override fun sendDelHomeForm(player: Player) {
        val userHomes =
            User(player, Bukkit.getPluginManager().getPlugin("Essentials") as Essentials).homes
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
        userHomes.forEach { form.button(it) }
        BedrockPlayerSupport.floodgateApi.sendForm(player.uniqueId, form)
    }

    override fun sendGoHomeForm(player: Player) {
        val userHomes =
            User(player, Bukkit.getPluginManager().getPlugin("Essentials") as Essentials).homes
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
        userHomes.forEach { form.button(it) }
        BedrockPlayerSupport.floodgateApi.sendForm(player.uniqueId, form)
    }

    override fun sendWarpForm(player: Player) {
        val userWarps = (Bukkit.getPluginManager().getPlugin("Essentials") as Essentials).warps.list
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
        userWarps.forEach { form.button(it) }
        BedrockPlayerSupport.floodgateApi.sendForm(player.uniqueId, form)
    }

    fun sendKitForm(player: Player) {
        val kits = (Bukkit.getPluginManager().getPlugin("Essentials") as Essentials).kits.kitKeys
        val form = SimpleForm.builder()
            .title(
                StringUtil.formatTextToString(
                    player,
                    BedrockPlayerSupport.langConfigManager.getConfigData().kitFormTitle()
                )
            )
            .validResultHandler { simpleFormResponse ->
                player.chat("/kit " + simpleFormResponse.clickedButton().text())
            }
        kits.forEach { form.button(it) }
        BedrockPlayerSupport.floodgateApi.sendForm(player.uniqueId, form)
    }

}