package cn.dsnbo.bedrockplayersupport.listener.login;

import cn.dsnbo.bedrockplayersupport.BedrockPlayerSupport;
import cn.dsnbo.bedrockplayersupport.config.Config;
import cn.dsnbo.bedrockplayersupport.config.Language;
import cn.dsnbo.bedrockplayersupport.util.StringUtil;
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
    Language language = BedrockPlayerSupport.getLanguageConfigManager().getConfigData();
    if (FloodgateApi.getInstance().isFloodgatePlayer(playerUuid)) {
      if (!AuthMeApi.getInstance().isAuthenticated(player)) {
        if (AuthMeApi.getInstance().isRegistered(playerName)) {
          if (config.enableLogin()) {
            AuthMeApi.getInstance().forceLogin(player);
            player.sendMessage(
                BedrockPlayerSupport.getMiniMessage().deserialize(language.loginSuccessfully()));
          }
        } else {
          if (config.enableRegister()) {
            String password = StringUtil.randomString(config.passwordLength());
            AuthMeApi.getInstance().registerPlayer(playerName, password);
            player.sendMessage(
                BedrockPlayerSupport.getMiniMessage().deserialize(language.registerSuccessfully()
                    .replaceAll("%password%", password)));
          }
        }
      }
    }
  }
}
