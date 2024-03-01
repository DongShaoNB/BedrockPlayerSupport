package cn.dsnbo.bedrockplayersupport.listener.login;

import cc.baka9.catseedlogin.bukkit.CatSeedLogin;
import cc.baka9.catseedlogin.bukkit.Config;
import cc.baka9.catseedlogin.bukkit.database.Cache;
import cc.baka9.catseedlogin.bukkit.event.CatSeedPlayerRegisterEvent;
import cc.baka9.catseedlogin.bukkit.object.LoginPlayer;
import cc.baka9.catseedlogin.bukkit.object.LoginPlayerHelper;
import cn.dsnbo.bedrockplayersupport.BedrockPlayerSupport;
import cc.baka9.catseedlogin.bukkit.CatSeedLoginAPI;
import cn.dsnbo.bedrockplayersupport.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.geysermc.floodgate.api.FloodgateApi;

import java.util.List;

/**
 * @author DongShaoNB
 */
public class CatSeedLoginListener implements Listener {
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        cn.dsnbo.bedrockplayersupport.config.Config config = BedrockPlayerSupport.getMainConfigManager().getConfigData();
        if (FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId())) {
            if (CatSeedLoginAPI.isRegister(playerName)) {
                if (!CatSeedLoginAPI.isLogin(playerName)) {
                    if (config.enableLogin()) {
                        LoginPlayerHelper.add(new LoginPlayer(player.getName(), ""));
                        Cache.refresh(player.getName());
                        if (Config.Settings.AfterLoginBack && Config.Settings.CanTpSpawnLocation) {
                            Config.getOfflineLocation(player).ifPresent(player::teleport);
                        }
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.loginMessage()));
                    }
                }
            } else {
                if (config.enableRegister()) {
                    try {
                        String password = StringUtil.randomString(config.passwordLength());
                        String currentIp = player.getAddress().getAddress().getHostAddress();
                        List<LoginPlayer> LoginPlayerListlikeByIp = CatSeedLogin.sql.getLikeByIp(currentIp);
                        if (LoginPlayerListlikeByIp.size() >= Config.Settings.IpRegisterCountLimit) {
                            player.sendMessage(Config.Language.REGISTER_MORE
                                    .replace("{count}", String.valueOf(LoginPlayerListlikeByIp.size()))
                                    .replace("{accounts}", String.join(", ", LoginPlayerListlikeByIp.stream().map(LoginPlayer::getName).toArray(String[]::new))));
                        } else {
                            LoginPlayer lp = new LoginPlayer(playerName, password);
                            lp.crypt();
                            CatSeedLogin.sql.add(lp);
                            LoginPlayerHelper.add(lp);
                            BedrockPlayerSupport.getScheduler().runTask(CatSeedLogin.instance, () -> {
                                CatSeedPlayerRegisterEvent event1 = new CatSeedPlayerRegisterEvent(player);
                                Bukkit.getServer().getPluginManager().callEvent(event1);
                            });
                            player.sendMessage(Config.Language.REGISTER_SUCCESS);
                            player.updateInventory();
                            LoginPlayerHelper.recordCurrentIP(player, lp);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.registerMessage()
                                    .replace("%password%", password)));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        player.sendMessage("§c服务器内部错误!");
                    }
                }
            }
        }
    }
}
