package cn.dsnbo.bedrockplayersupport.listener.login;

import cn.dsnbo.bedrockplayersupport.BedrockPlayerSupport;
import cn.dsnbo.bedrockplayersupport.config.Config;
import cn.dsnbo.bedrockplayersupport.util.StringRandom;
import fr.xephi.authme.api.v3.AuthMeApi;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.geysermc.floodgate.api.FloodgateApi;

import java.util.UUID;

/**
 * @author DongShaoNB
 */
public class AuthMeListener implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID playerUuid = player.getUniqueId();
        String playerName = player.getName();
        Config config = BedrockPlayerSupport.getMainConfigManager().getConfigData();
        if (FloodgateApi.getInstance().isFloodgatePlayer(playerUuid)) {
            if (!AuthMeApi.getInstance().isAuthenticated(player)) {
                if (AuthMeApi.getInstance().isRegistered(playerName)) {
                    if (config.enableLogin()) {
                        AuthMeApi.getInstance().forceLogin(player);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.loginMessage()));
                    }
                } else {
                    if (config.enableRegister()) {
                        String password = StringRandom.random(config.passwordLength());
                        AuthMeApi.getInstance().registerPlayer(playerName, password);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.registerMessage()
                                .replace("%password%", password)));
                    }
                }
            }
        }
    }
}
