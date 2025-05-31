package cc.dsnb.bedrockplayersupport.form.basic

import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import cc.dsnb.bedrockplayersupport.util.StringUtil
import io.github.niestrat99.advancedteleport.api.ATPlayer
import io.github.niestrat99.advancedteleport.api.AdvancedTeleportAPI
import org.bukkit.entity.Player
import org.geysermc.cumulus.form.SimpleForm

class ATForm : BasicForm {

    override fun sendDelHomeForm(player: Player) {
        val atPlayer = ATPlayer.getPlayer(player)
        val playerHomesList = atPlayer.homes
        val form = SimpleForm.builder()
            .title(
                StringUtil.formatTextToString(
                    player,
                    BedrockPlayerSupport.langConfigManager.getConfigData().delHomeFormTitle()
                )
            )
            .validResultHandler { simpleFormResponse ->
                player.chat("/delhome ${simpleFormResponse.clickedButton().text()}")
            }
        playerHomesList.forEach { form.button(it.key) }
        BedrockPlayerSupport.floodgateApi.sendForm(player.uniqueId, form)
    }

    override fun sendGoHomeForm(player: Player) {
        val atPlayer = ATPlayer.getPlayer(player)
        val playerHomesList = atPlayer.homes
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

    override fun sendWarpForm(player: Player) {
        val warps = AdvancedTeleportAPI.getWarps()
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

}