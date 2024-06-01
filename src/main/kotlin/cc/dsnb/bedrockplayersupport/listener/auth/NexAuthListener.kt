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
        val mainConfig = BedrockPlayerSupport.mainConfigManager.getConfigData()
        val langConfig = BedrockPlayerSupport.langConfigManager.getConfigData()
        if (BedrockPlayerSupport.floodgateApi.isFloodgatePlayer(player.uniqueId)) {
            if (AuthPlayer.getOrCreate(player).isRegistered) {
                if (AuthPlayer.getOrCreate(player).state == PlayerState.IN_LOGIN) {
                    if (mainConfig.enableLogin()) {
                        NexAuthAPI.getAuthManager().login(player)
                        player.sendMessage(
                            BedrockPlayerSupport.miniMessage.deserialize(langConfig.loginSuccessfully())
                        )
                    }
                }
            } else {
                if (mainConfig.enableRegister()) {
                    val password = StringUtil.randomString(mainConfig.passwordLength())
                    NexAuthAPI.getAuthManager().register(player, password)
                    player.sendMessage(
                        BedrockPlayerSupport.miniMessage.deserialize(
                            langConfig.registerSuccessfully()
                                .replace("%password%", password)
                        )
                    )
                }
            }
        }
    }

}