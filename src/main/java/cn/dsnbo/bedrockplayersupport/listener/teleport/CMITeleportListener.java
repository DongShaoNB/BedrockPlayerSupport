package cn.dsnbo.bedrockplayersupport.listener.teleport;

import cn.dsnbo.bedrockplayersupport.TeleportType;
import cn.dsnbo.bedrockplayersupport.form.MainForm;
import com.Zrips.CMI.Modules.Teleportations.TeleportManager.TpAction;
import com.Zrips.CMI.events.CMIPlayerTeleportRequestEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.geysermc.floodgate.api.FloodgateApi;

import java.util.UUID;

/**
 * @author DongShaoNB
 */
public class CMITeleportListener implements Listener {

  @EventHandler
  public void onCmiPlayerTeleportRequestEvent(CMIPlayerTeleportRequestEvent event) {
    TpAction tpAction = event.getAction();
    Player requestor = event.getWhoOffers().getPlayer();
    Player receiver = event.getWhoAccepts().getPlayer();
    UUID teleportLocationPlayerUuid = receiver.getUniqueId();
    if (FloodgateApi.getInstance().isFloodgatePlayer(teleportLocationPlayerUuid)) {
      if (tpAction == TpAction.tpa) {
        MainForm.getInstance().openBedrockTeleportHereForm(TeleportType.Tpa, requestor, receiver);
      } else if (tpAction == TpAction.tpahere) {
        MainForm.getInstance()
            .openBedrockTeleportHereForm(TeleportType.TpaHere, requestor, receiver);
      }
    }
  }
}
