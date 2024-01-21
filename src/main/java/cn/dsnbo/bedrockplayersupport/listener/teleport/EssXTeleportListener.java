package cn.dsnbo.bedrockplayersupport.listener.teleport;

import cn.dsnbo.bedrockplayersupport.TeleportType;
import cn.dsnbo.bedrockplayersupport.form.MainForm;
import net.ess3.api.events.TPARequestEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.geysermc.floodgate.api.FloodgateApi;

import java.util.UUID;

/**
 * @author DongShaoNB
 */
public class EssXTeleportListener implements Listener {
    @EventHandler
    public void onTeleportWarmupEvent(TPARequestEvent event){
        boolean isTeleportHere = event.isTeleportHere();
        Player requestor = event.getRequester().getPlayer();
        Player receiver = event.getTarget().getBase();
        UUID receiverUuid = receiver.getUniqueId();
        if (FloodgateApi.getInstance().isFloodgatePlayer(receiverUuid)) {
            if (!isTeleportHere) {
                MainForm.getInstance().openBedrockTeleportHereForm(TeleportType.Tpa, requestor, receiver);
            } else {
                MainForm.getInstance().openBedrockTeleportHereForm(TeleportType.TpaHere, requestor, receiver);
            }
        }

    }
}
