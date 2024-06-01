package cc.dsnb.bedrockplayersupport.listener.auth

import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

/**
 * @author DongShaoNB
 */
class OtherAuthListener : Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player
        val mainConfig = BedrockPlayerSupport.mainConfigManager.getConfigData()
        val langConfig = BedrockPlayerSupport.langConfigManager.getConfigData()
        if (BedrockPlayerSupport.floodgateApi.isFloodgatePlayer(player.uniqueId)) {
            if (mainConfig.enableLogin()) {
                Bukkit.dispatchCommand(
                    Bukkit.getServer().consoleSender,
                    mainConfig.forceLoginCommand().replace("%playerName%", player.name)
                )
                player.sendMessage(
                    BedrockPlayerSupport.miniMessage.deserialize(langConfig.loginSuccessfully())
                )
            }
        }
    }

}