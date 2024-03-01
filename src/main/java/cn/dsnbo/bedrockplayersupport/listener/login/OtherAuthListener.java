package cn.dsnbo.bedrockplayersupport.listener.login;

import cn.dsnbo.bedrockplayersupport.BedrockPlayerSupport;
import cn.dsnbo.bedrockplayersupport.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.geysermc.floodgate.api.FloodgateApi;

/**
 * @author DongShaoNB
 */
public class OtherAuthListener implements Listener {

  @EventHandler
  public void onPlayerJoinEvent(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    Config config = BedrockPlayerSupport.getMainConfigManager().getConfigData();
    if (FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId())) {
      if (config.enableLogin()) {
        Bukkit.dispatchCommand(
            Bukkit.getServer().getConsoleSender(),
            config.forceLoginCommand()
                .replace("%player%", player.getName()));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.loginMessage()));
      }
    }
  }

}
