package cn.dsnbo.bedrockplayersupport.listener.login;

import cn.dsnbo.bedrockplayersupport.BedrockPlayerSupport;
import cn.dsnbo.bedrockplayersupport.config.Config;
import cn.dsnbo.bedrockplayersupport.config.Language;
import cn.dsnbo.bedrockplayersupport.util.StringUtil;
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
    Language language = BedrockPlayerSupport.getLanguageConfigManager().getConfigData();
    if (FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId())) {
      if (AuthPlayer.getOrCreate(player).isRegistered()) {
        if (AuthPlayer.getOrCreate(player).getState() == PlayerState.IN_LOGIN) {
          if (config.enableLogin()) {
            NexAuthAPI.getAuthManager().login(player);
            player.sendMessage(
                BedrockPlayerSupport.getMiniMessage().deserialize(language.loginSuccessfully()));
          }
        }
      } else {
        if (config.enableRegister()) {
          String password = StringUtil.randomString(config.passwordLength());
          NexAuthAPI.getAuthManager().register(player, password);
          player.sendMessage(
                BedrockPlayerSupport.getMiniMessage().deserialize(language.registerSuccessfully()
                    .replaceAll("%password%", password)));
        }
      }
    }
  }
}
