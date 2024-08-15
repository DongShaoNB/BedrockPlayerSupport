package cc.dsnb.bedrockplayersupport.listener.basic

import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import cc.dsnb.bedrockplayersupport.TeleportType
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import su.nightexpress.sunlight.api.event.PlayerTeleportRequestEvent
import su.nightexpress.sunlight.module.ptp.Mode

class SunLightListener : Listener {

    @EventHandler
    fun onPlayerTeleportRequest(event: PlayerTeleportRequestEvent) {
        val requestMode = event.request.mode
        val requester = Bukkit.getPlayer(event.request.senderInfo.id)
        val receiver = Bukkit.getPlayer(event.request.targetInfo.id)
        if (requester != null && receiver != null) {
            val receiverUUID = receiver.uniqueId
            if (BedrockPlayerSupport.floodgateApi.isFloodgatePlayer(receiverUUID)) {
                if (requestMode == Mode.REQUEST) {
                    BedrockPlayerSupport.mainForm.openBedrockReceiveTeleportForm(
                        TeleportType.Tpa,
                        requester,
                        receiver
                    )
                } else if (requestMode == Mode.INVITE) {
                    BedrockPlayerSupport.mainForm.openBedrockReceiveTeleportForm(
                        TeleportType.TpaHere,
                        requester,
                        receiver
                    )
                }
            }
        }
    }

}