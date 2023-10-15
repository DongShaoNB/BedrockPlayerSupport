package cn.dsnbo.bedrockplayersupport.listener.login;

import cn.dsnbo.bedrockplayersupport.BedrockPlayerSupport;
import fr.xephi.authme.api.v3.AuthMeApi;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.geysermc.floodgate.api.FloodgateApi;

/**
 * @author DongShaoNB
 */
public class AuthMeListener implements Listener {
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId())) {
            if (!AuthMeApi.getInstance().isAuthenticated(player)) {
                if (AuthMeApi.getInstance().isRegistered(player.getName())) {
                    AuthMeApi.getInstance().forceLogin(player);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', BedrockPlayerSupport.getInstance().getConfig().getString("login.auto-message")));
                }
            }
        }
    }
}
