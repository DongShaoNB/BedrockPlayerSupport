package cn.dsnbo.bedrockplayersupport;

import cn.dsnbo.bedrockplayersupport.command.tpaCommand;
import cn.dsnbo.bedrockplayersupport.command.tpahereCommand;
import cn.dsnbo.bedrockplayersupport.listeners.PlayerListener;
import cn.dsnbo.bedrockplayersupport.listeners.CMITeleportListener;
import cn.dsnbo.bedrockplayersupport.listeners.EssXTeleportListener;
import cn.dsnbo.bedrockplayersupport.utils.Update;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * @author DongShaoNB
 */
public final class BedrockPlayerSupport extends JavaPlugin implements Listener {

    private static BedrockPlayerSupport Plugin;
    private boolean isCmiEnabled;
    private boolean isEssxEnabled;
    private boolean isAuthMeEnabled;
    private boolean isFloodgateEnabled;
    private static final String VERSION = "1.5";

    @Override
    public void onEnable() {
        Plugin = this;
        saveResource();
        Update.updateConfig();
        if (getConfig().getBoolean("bedrock-player-teleport-menu")) {
            getCommand("tpagui").setExecutor(new tpaCommand());
            getCommand("tpaheregui").setExecutor(new tpahereCommand());
        }
        getLogger().info("§b感谢选择使用本插件，作者: DongShaoNB，QQ群: 159323818");
        isCmiEnabled = !(Bukkit.getPluginManager().getPlugin("CMI") == null);
        isEssxEnabled = !(Bukkit.getPluginManager().getPlugin("Essentials") == null);
        isAuthMeEnabled = !(Bukkit.getPluginManager().getPlugin("AuthMe") == null);
        isFloodgateEnabled = !(Bukkit.getPluginManager().getPlugin("floodgate") == null);
        new Metrics(this, 17107);
        if (isCmiEnabled) {
            Bukkit.getPluginManager().registerEvents(new CMITeleportListener(), this);
        } else if (isEssxEnabled) {
            Bukkit.getPluginManager().registerEvents(new EssXTeleportListener(), this);
        }

        getLogger().info("-----------------");
        getLogger().info("§b检测支持插件是否启用: ");
        if (isFloodgateEnabled) {
            getLogger().info("§bfloodgate: §atrue");
        } else {
            getLogger().info("§bfloodgate: §cfalse");
        }

        if (isCmiEnabled) {
            getLogger().info("§bCMI: §atrue");
        } else {
            getLogger().info("§bCMI: §cfalse");
        }

        if (isEssxEnabled) {
            getLogger().info("§bEssentials: §atrue");
        } else {
            getLogger().info("§bEssentials: §cfalse");
        }

        if (isAuthMeEnabled) {
            getLogger().info("§bAuthMe: §atrue");
        } else {
            getLogger().info("§bAuthMe: §cfalse");
        }
        getLogger().info("-----------------");

        if (getConfig().getBoolean("login.enable")) {
            if (isAuthMeEnabled) {
                Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
            }
        }

        if (getConfig().getBoolean("check-update")) {
            Update.checkUpdate();
        }

    }

    @Override
    public void onDisable() {

    }

    public static void saveResource() {
        getInstance().saveDefaultConfig();
        File updateFolder = new File(BedrockPlayerSupport.getUpdateFolder());
        if (!updateFolder.exists()) {
            updateFolder.mkdir();
        }
    }

    public static String getUpdateFolder() {
        return getInstance().getDataFolder() + "/update";
    }

    public static String getVersion() {
        return VERSION;
    }

    public static BedrockPlayerSupport getInstance() {
        return Plugin;
    }

}
