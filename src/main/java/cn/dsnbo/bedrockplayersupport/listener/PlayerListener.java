package cn.dsnbo.bedrockplayersupport.listener;

import cn.dsnbo.bedrockplayersupport.BedrockPlayerSupport;
import cn.dsnbo.bedrockplayersupport.config.Config;
import cn.dsnbo.bedrockplayersupport.util.Form;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.UUID;

/**
 * @author DongShaoNB
 */
public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerRespawnEvent(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        Config config = BedrockPlayerSupport.getMainConfigManager().getConfigData();
        if (BedrockPlayerSupport.getFloodgateApi().isFloodgatePlayer(uuid)) {
            if (config.enableBackForm()) {
                Bukkit.getScheduler().runTaskLaterAsynchronously(BedrockPlayerSupport.getInstance(), () -> {
                    Form.openBedrockBackForm(player);
                }, 20L);
            }
        }
    }

}
