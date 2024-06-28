package cc.dsnb.bedrockplayersupport.listener.basic

import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import cc.dsnb.bedrockplayersupport.TeleportType
import io.github.niestrat99.advancedteleport.api.TeleportRequestType
import io.github.niestrat99.advancedteleport.api.events.players.TeleportRequestEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class ATListener : Listener {

    @EventHandler
    fun onTeleportRequest(event: TeleportRequestEvent) {
        val requestType = event.requestType
        val requester = event.sendingPlayer
        val receiver = event.receivingPlayer
        val receiverUUID = receiver.uniqueId
        if (BedrockPlayerSupport.floodgateApi.isFloodgatePlayer(receiverUUID)) {
            if (requestType == TeleportRequestType.TPA) {
                BedrockPlayerSupport.mainForm.openBedrockReceiveTeleportForm(
                    TeleportType.Tpa,
                    requester,
                    receiver
                )
            } else if (requestType == TeleportRequestType.TPAHERE) {
                BedrockPlayerSupport.mainForm.openBedrockReceiveTeleportForm(
                    TeleportType.TpaHere,
                    requester,
                    receiver
                )
            }
        }
    }

}