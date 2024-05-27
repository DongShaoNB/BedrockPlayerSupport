package cc.dsnb.bedrockplayersupport.listener.basic

import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import cc.dsnb.bedrockplayersupport.TeleportType
import net.ess3.api.events.TPARequestEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

/**
 * @author DongShaoNB
 */
class EssXListener : Listener {

    @EventHandler
    fun onTPARequest(event: TPARequestEvent) {
        val isTeleportHere = event.isTeleportHere
        val requester = event.requester.player
        val receiver = event.target.base
        val receiverUuid = receiver.uniqueId
        if (BedrockPlayerSupport.floodgateApi.isFloodgatePlayer(receiverUuid)) {
            if (isTeleportHere) {
                BedrockPlayerSupport.mainForm.openBedrockReceiveTeleportForm(TeleportType.TpaHere, requester, receiver)
            } else {
                BedrockPlayerSupport.mainForm.openBedrockReceiveTeleportForm(TeleportType.Tpa, requester, receiver)
            }
        }
    }

}