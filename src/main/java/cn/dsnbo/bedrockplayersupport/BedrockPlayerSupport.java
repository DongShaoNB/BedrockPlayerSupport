package cn.dsnbo.bedrockplayersupport;

import cn.dsnbo.bedrockplayersupport.command.TpaCommand;
import cn.dsnbo.bedrockplayersupport.command.TpaHereCommand;
import cn.dsnbo.bedrockplayersupport.listeners.PlayerListener;
import cn.dsnbo.bedrockplayersupport.listeners.CMITeleportListener;
import cn.dsnbo.bedrockplayersupport.listeners.EssXTeleportListener;
import cn.dsnbo.bedrockplayersupport.listeners.login.AuthMeListener;
import cn.dsnbo.bedrockplayersupport.listeners.teleport.CMITeleportListener;
import cn.dsnbo.bedrockplayersupport.listeners.teleport.EssXTeleportListener;
import cn.dsnbo.bedrockplayersupport.utils.Update;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * @author DongShaoNB
 */
public final class BedrockPlayerSupport extends JavaPlugin implements Listener {

    private static BedrockPlayerSupport Plugin;
    private boolean isCmiEnabled;
    private boolean isEssxEnabled;
    private boolean isAuthMeEnabled;
    private boolean isFloodgateEnabled;

    @Override
    public void onEnable() {
        Plugin = this;
        new Metrics(this, 17107);
        saveDefaultConfig();
        Update.updateConfig();
        setDependLoadStatus();
        getLogger().info("§b感谢选择使用本插件，作者: DongShaoNB，QQ群: 159323818");
        if (getConfig().getBoolean("bedrock-player-teleport-menu")) {
            getCommand("tpagui").setExecutor(new TpaCommand());
            getCommand("tpaheregui").setExecutor(new TpaHereCommand());
        }
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

    private void setDependLoadStatus() {
        isCmiEnabled = !(Bukkit.getPluginManager().getPlugin("CMI") == null);
        isEssxEnabled = !(Bukkit.getPluginManager().getPlugin("Essentials") == null);
        isHuskHomesEnabled = !(Bukkit.getPluginManager().getPlugin("HuskHomes") == null);
        isAuthMeEnabled = !(Bukkit.getPluginManager().getPlugin("AuthMe") == null);
        isFloodgateEnabled = !(Bukkit.getPluginManager().getPlugin("floodgate") == null);
    }

    private void checkSupportPluginLoadStatus() {
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

        if (isHuskHomesEnabled) {
            getLogger().info("§bHuskHomes: §atrue");
        } else {
            getLogger().info("§bHuskHomes: §cfalse");
        }

        if (isAuthMeEnabled) {
            getLogger().info("§bAuthMe: §atrue");
        } else {
            getLogger().info("§bAuthMe: §cfalse");
        }
        getLogger().info("-----------------");
    }

    public static BedrockPlayerSupport getInstance() {
        return Plugin;
    }

}
