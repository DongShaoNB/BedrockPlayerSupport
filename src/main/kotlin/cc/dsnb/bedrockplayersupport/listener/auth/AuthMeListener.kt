package cc.dsnb.bedrockplayersupport.listener.auth

import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import cc.dsnb.bedrockplayersupport.util.StringUtil
import fr.xephi.authme.api.v3.AuthMeApi
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

/**
 * @author DongShaoNB
 */
class AuthMeListener : Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player
        val playerUuid = player.uniqueId
        val playerName = player.name
        val mainConfig = BedrockPlayerSupport.mainConfigManager.getConfigData()
        val langConfig = BedrockPlayerSupport.langConfigManager.getConfigData()
        BedrockPlayerSupport.scheduler.runTaskLater(player, {
            if (BedrockPlayerSupport.floodgateApi.isFloodgatePlayer(playerUuid)) {
                if (AuthMeApi.getInstance().isAuthenticated(player).not()) {
                    if (AuthMeApi.getInstance().isRegistered(playerName)) {
                        if (mainConfig.enableLogin()) {
                            AuthMeApi.getInstance().forceLogin(player)
                            player.sendMessage(StringUtil.formatTextToComponent(player, langConfig.loginSuccessfully()))
                        }
                    } else {
                        if (mainConfig.enableRegister()) {
                            val password = StringUtil.randomString(mainConfig.passwordLength())
                            AuthMeApi.getInstance().forceRegister(player, password)
                            player.sendMessage(
                                StringUtil.formatTextToComponent(
                                    player,
                                    langConfig.registerSuccessfully().replace("%password%", password)
                                )
                            )
                        }
                    }
                }
            }
        }, 20L)
    }

}