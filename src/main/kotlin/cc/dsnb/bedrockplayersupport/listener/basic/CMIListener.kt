package cc.dsnb.bedrockplayersupport.listener.basic

import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import cc.dsnb.bedrockplayersupport.TeleportType
import com.Zrips.CMI.Modules.Teleportations.TeleportManager.TpAction
import com.Zrips.CMI.events.CMIPlayerTeleportRequestEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

/**
 * @author DongShaoNB
 */
class CMIListener : Listener {

    @EventHandler
    fun onCMIPlayerTeleportRequest(event: CMIPlayerTeleportRequestEvent) {
        val tpAction = event.action
        val requester = event.whoOffers.player
        val receiver = event.whoAccepts.player
        if (requester != null && receiver != null) {
            val receiverUuid = receiver.uniqueId
            if (BedrockPlayerSupport.floodgateApi.isFloodgatePlayer(receiverUuid)) {
                if (tpAction == TpAction.tpa) {
                    BedrockPlayerSupport.mainForm.openBedrockReceiveTeleportForm(TeleportType.Tpa, requester, receiver)
                } else if (tpAction == TpAction.tpahere) {
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