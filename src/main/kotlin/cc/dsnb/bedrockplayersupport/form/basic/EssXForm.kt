package cc.dsnb.bedrockplayersupport.form.basic

import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import com.earth2me.essentials.Essentials
import com.earth2me.essentials.User
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.geysermc.cumulus.form.SimpleForm

/**
 * @author DongShaoNB
 */
class EssXForm {

    fun sendDelHomeForm(player: Player) {
        val userHomes = User(player, Bukkit.getPluginManager().getPlugin("Essentials") as Essentials).homes
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
        userHomes.forEach { form.button(it) }
        BedrockPlayerSupport.floodgateApi.sendForm(player.uniqueId, form)
    }

    fun sendGoHomeForm(player: Player) {
        val userHomes = User(player, Bukkit.getPluginManager().getPlugin("Essentials") as Essentials).homes
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
        userHomes.forEach { form.button(it) }
        BedrockPlayerSupport.floodgateApi.sendForm(player.uniqueId, form)
    }

    fun sendWarpForm(player: Player) {
        val userWarps = (Bukkit.getPluginManager().getPlugin("Essentials") as Essentials).warps.list
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
        userWarps.forEach { form.button(it) }
        BedrockPlayerSupport.floodgateApi.sendForm(player.uniqueId, form)
    }

}