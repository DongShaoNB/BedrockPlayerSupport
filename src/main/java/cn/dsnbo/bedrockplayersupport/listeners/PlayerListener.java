package cn.dsnbo.bedrockplayersupport.listeners;

import cn.dsnbo.bedrockplayersupport.BedrockPlayerSupport;
import cn.dsnbo.bedrockplayersupport.utils.Forms;
import fr.xephi.authme.api.v3.AuthMeApi;
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
public class PlayerListener implements Listener {
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId())) {
            if (!AuthMeApi.getInstance().isAuthenticated(player)) {
                if (AuthMeApi.getInstance().isRegistered(player.getName())) {
                    if (BedrockPlayerSupport.Plugin.getConfig().getInt("login.mode") == 0) {
                        Forms.openLoginForm(player);
                    } else if (BedrockPlayerSupport.Plugin.getConfig().getInt("login.mode") == 1) {
                        AuthMeApi.getInstance().forceLogin(player);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', BedrockPlayerSupport.Plugin.getConfig().getString("login.auto-message")));
                    } else {
                        Bukkit.getLogger().warning("你在配置文件中启用了基岩版登录支持，但设置的模式不存在，已自动关闭该功能!");
                    }
                } else {
                    Forms.openRegisterForm(player);
                }
            }
        }
    }
}
