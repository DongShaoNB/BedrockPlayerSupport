package cn.dsnbo.bedrockplayersupport;

import cn.dsnbo.bedrockplayersupport.listeners.PlayerListener;
import cn.dsnbo.bedrockplayersupport.listeners.CMITeleportListener;
import cn.dsnbo.bedrockplayersupport.listeners.EssXTeleportListener;
import cn.dsnbo.bedrockplayersupport.utils.Update;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class BedrockPlayerSupport extends JavaPlugin implements Listener {

    public static Plugin Plugin;
    public boolean isCMIEnabled;
    public boolean isEssXEnabled;
    public boolean isAuthMeEnabled;
    public boolean isFloodgateEnabled;
    public static String Version = "1.4";

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getLogger().info("感谢选择使用本插件，作者: DongShaoNB，QQ群: 159323818");
        Plugin = BedrockPlayerSupport.getProvidingPlugin(BedrockPlayerSupport.class);
        isCMIEnabled = !(Bukkit.getPluginManager().getPlugin("CMI") == null);
        isEssXEnabled = !(Bukkit.getPluginManager().getPlugin("Essentials") == null);
        isAuthMeEnabled = !(Bukkit.getPluginManager().getPlugin("Authme") == null);
        isFloodgateEnabled = !(Bukkit.getPluginManager().getPlugin("Floodgate") == null);
        new Metrics(this, 17107);
        if (isCMIEnabled) {
            Bukkit.getPluginManager().registerEvents(new CMITeleportListener(), this);
        } else if (isEssXEnabled) {
            Bukkit.getPluginManager().registerEvents(new EssXTeleportListener(), this);
        }

        if (getConfig().getBoolean("login.enable")) {
            if (isAuthMeEnabled) {
                Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
            } else {
                getLogger().warning("你在配置文件中启用了基岩版登录支持，但没有检测到本插件支持的登录插件，已自动关闭该功能!");
            }
        }

        if (getConfig().getBoolean("check-update")) {
            String latestVersion = Update.getLatestVersion();
            if (latestVersion != null) {
                if (!Version.equals(latestVersion)) {
                    Bukkit.getConsoleSender().sendMessage("[BedrockPlayerSupport] §2有新版本可以更新!");
                    Bukkit.getConsoleSender().sendMessage("[BedrockPlayerSupport] §2当前版本: " + Version + " | 最新版本: " + latestVersion);
                } else {
                    Bukkit.getConsoleSender().sendMessage("[BedrockPlayerSupport] §2插件是最新版本，继续保持哦~");
                }
            } else {
                getLogger().warning("§4无法检测更新，请检查网络情况，尝试访问: https://update.dsnbo.cn/ 是否正常");
            }

        }

    }

    @Override
    public void onDisable() {

    }
}
