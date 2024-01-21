package cn.dsnbo.bedrockplayersupport.listener.teleport;

import cn.dsnbo.bedrockplayersupport.TeleportType;
import cn.dsnbo.bedrockplayersupport.form.MainForm;
import net.william278.huskhomes.event.ReceiveTeleportRequestEvent;
import net.william278.huskhomes.teleport.TeleportRequest;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * @author DongShaoNB
 */
public class HuskHomesTeleportListener implements Listener {

    @EventHandler
    public void onReceiveTeleportRequestEvent(ReceiveTeleportRequestEvent event) {
        TeleportRequest.Type requestType = event.getRequest().getType();
        Player requestor = Bukkit.getPlayer(event.getRequest().getRequesterName());
        Player receiver = Bukkit.getPlayer(event.getRecipient().getUuid());
        if (requestType == TeleportRequest.Type.TPA) {
            MainForm.getInstance().openBedrockTeleportHereForm(TeleportType.Tpa, requestor, receiver);
        } else if (requestType == TeleportRequest.Type.TPA_HERE) {
            MainForm.getInstance().openBedrockTeleportHereForm(TeleportType.TpaHere, requestor, receiver);
        }
    }

}
