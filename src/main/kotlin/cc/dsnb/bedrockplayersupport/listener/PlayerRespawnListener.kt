package cc.dsnb.bedrockplayersupport.listener

import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerRespawnEvent

/**
 * @author DongShaoNB
 */
class PlayerRespawnListener : Listener {

    @EventHandler
    fun onPlayerRespawn(event: PlayerRespawnEvent) {
        if (event.respawnFlags.contains(PlayerRespawnEvent.RespawnFlag.END_PORTAL).not()) {
            val player = event.player
            val uuid = player.uniqueId
            if (BedrockPlayerSupport.floodgateApi.isFloodgatePlayer(uuid)) {
                val mainConfig = BedrockPlayerSupport.mainConfigManager.getConfigData()
                if (mainConfig.enableBackDeathLocForm()) {
                    if (player.hasPermission("bedrockplayersupport.form.backdeath")) {
                        BedrockPlayerSupport.scheduler
                            .runTaskLaterAsynchronously(
                                { BedrockPlayerSupport.mainForm.openBedrockBackDeathLocForm(player) },
                                mainConfig.backDeathLocFormOpenDelayTick()
                            )
                    }
                }
            }
        }
    }

}