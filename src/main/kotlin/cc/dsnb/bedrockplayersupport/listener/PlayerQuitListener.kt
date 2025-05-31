package cc.dsnb.bedrockplayersupport.listener

import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

class PlayerQuitListener : Listener {

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        val player = event.player
        if (BedrockPlayerSupport.floodgateApi.isFloodgatePlayer(player.uniqueId)) {
            val commands = BedrockPlayerSupport.mainConfigManager.getConfigData().onQuitCommands()
            commands.forEach {
                val commandSplit = it.split(" ", limit = 2)
                val executor = commandSplit[0]
                val command = commandSplit[1].replace("%playerName%", player.name)
                when (executor.uppercase()) {
                    "[CONSOLE]" -> Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command)
                    "[PLAYER]" -> player.performCommand(command)
                }
            }
        }
    }

}