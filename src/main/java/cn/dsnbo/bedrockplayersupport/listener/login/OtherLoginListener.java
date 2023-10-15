package cn.dsnbo.bedrockplayersupport.listener.login;

import cn.dsnbo.bedrockplayersupport.BedrockPlayerSupport;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.geysermc.floodgate.api.FloodgateApi;

/**
 * @author DongShaoNB
 */
public class OtherLoginListener implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId())) {
            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), BedrockPlayerSupport.getInstance().getConfig().getString("login.forcelogin-command")
                    .replace("%player%", player.getName()));
        }
    }

}
