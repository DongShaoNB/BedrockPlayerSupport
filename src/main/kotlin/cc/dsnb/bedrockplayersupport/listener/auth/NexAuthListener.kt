package cc.dsnb.bedrockplayersupport.listener.auth

import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import cc.dsnb.bedrockplayersupport.util.StringUtil
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import su.nexmedia.auth.NexAuthAPI
import su.nexmedia.auth.auth.impl.AuthPlayer
import su.nexmedia.auth.auth.impl.PlayerState

/**
 * @author DongShaoNB
 */
class NexAuthListener : Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player
        val config = BedrockPlayerSupport.mainConfigManager.getConfigData()
        val language = BedrockPlayerSupport.langConfigManager.getConfigData()
        if (BedrockPlayerSupport.floodgateApi.isFloodgatePlayer(player.uniqueId)) {
            if (AuthPlayer.getOrCreate(player).isRegistered) {
                if (AuthPlayer.getOrCreate(player).state == PlayerState.IN_LOGIN) {
                    if (config.enableLogin()) {
                        NexAuthAPI.getAuthManager().login(player)
                        player.sendMessage(
                            BedrockPlayerSupport.miniMessage.deserialize(language.loginSuccessfully())
                        )
                    }
                }
            } else {
                if (config.enableRegister()) {
                    val password = StringUtil.randomString(config.passwordLength())
                    NexAuthAPI.getAuthManager().register(player, password)
                    player.sendMessage(
                        BedrockPlayerSupport.miniMessage.deserialize(
                            language.registerSuccessfully()
                                .replace("%password%", password)
                        )
                    )
                }
            }
        }
    }

}