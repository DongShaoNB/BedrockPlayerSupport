package cn.dsnbo.bedrockplayersupport.listener.login;

import cn.dsnbo.bedrockplayersupport.BedrockPlayerSupport;
import cn.dsnbo.bedrockplayersupport.config.Config;
import cn.dsnbo.bedrockplayersupport.util.StringRandom;
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
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Config config = BedrockPlayerSupport.getMainConfigManager().getConfigData();
        if (FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId())) {
            if (AuthPlayer.getOrCreate(player).isRegistered()) {
                if (AuthPlayer.getOrCreate(player).getState() == PlayerState.IN_LOGIN) {
                    if (config.enableLogin()) {
                        NexAuthAPI.getAuthManager().login(player);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.loginMessage()));
                    }
                }
            } else {
                if (config.enableRegister()) {
                    String password = StringRandom.random(config.passwordLength());
                    NexAuthAPI.getAuthManager().register(player, password);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.registerMessage()
                            .replace("%password%", password)));
                }
            }
        }
    }
}
