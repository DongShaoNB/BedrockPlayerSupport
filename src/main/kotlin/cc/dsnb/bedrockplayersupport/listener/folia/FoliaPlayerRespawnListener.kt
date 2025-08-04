package cc.dsnb.bedrockplayersupport.listener.folia

import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryType

// Temp solution for Folia
class FoliaPlayerRespawnListener: Listener {
    @EventHandler
    fun onRespawn(event: InventoryCloseEvent) {
        val player = event.player as? Player ?: return
        if (event.inventory.type != InventoryType.CRAFTING || !player.isDead || !player.isConnected || player.health > 0) return
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