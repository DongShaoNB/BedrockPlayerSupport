package cn.dsnbo.bedrockplayersupport.listener.login;

import cn.dsnbo.bedrockplayersupport.BedrockPlayerSupport;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.geysermc.floodgate.api.FloodgateApi;
import su.nexmedia.auth.NexAuthAPI;
import su.nexmedia.auth.auth.impl.AuthPlayer;
import su.nexmedia.auth.auth.impl.PlayerState;

/**
 * @author DongShaoNB
 */
public class NexAuthListener implements Listener {
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId())) {
            if (AuthPlayer.getOrCreate(player).getState() == PlayerState.IN_LOGIN && AuthPlayer.getOrCreate(player).isRegistered()) {
                NexAuthAPI.getAuthManager().login(player);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', BedrockPlayerSupport.getInstance().getConfig().getString("login.auto-message")));
            }
        }
    }
}
