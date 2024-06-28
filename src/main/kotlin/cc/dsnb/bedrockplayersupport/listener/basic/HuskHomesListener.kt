package cc.dsnb.bedrockplayersupport.listener.basic

import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import cc.dsnb.bedrockplayersupport.TeleportType
import net.william278.huskhomes.event.ReceiveTeleportRequestEvent
import net.william278.huskhomes.teleport.TeleportRequest
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

/**
 * @author DongShaoNB
 */
class HuskHomesListener : Listener {

    @EventHandler
    fun onReceiveTeleportRequestEvent(event: ReceiveTeleportRequestEvent) {
        val requestType = event.request.type
        val requester = Bukkit.getPlayer(event.request.requesterName)
        val receiver = Bukkit.getPlayer(event.recipient.uuid)
        if (requester != null && receiver != null) {
            val receiverUUID = receiver.uniqueId
            if (BedrockPlayerSupport.floodgateApi.isFloodgatePlayer(receiverUUID)) {
                if (requestType == TeleportRequest.Type.TPA) {
                    BedrockPlayerSupport.mainForm.openBedrockReceiveTeleportForm(TeleportType.Tpa, requester, receiver)
                } else if (requestType == TeleportRequest.Type.TPA_HERE) {
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