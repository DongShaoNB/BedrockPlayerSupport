package cn.dsnbo.bedrockplayersupport;

import cn.dsnbo.bedrockplayersupport.command.TpaCommand;
import cn.dsnbo.bedrockplayersupport.command.TpaHereCommand;
import cn.dsnbo.bedrockplayersupport.listener.login.CatSeedLoginListener;
import cn.dsnbo.bedrockplayersupport.listener.login.NexAuthListener;
import cn.dsnbo.bedrockplayersupport.listener.login.OtherLoginListener;
import cn.dsnbo.bedrockplayersupport.listener.teleport.HuskHomesTeleportListener;
import cn.dsnbo.bedrockplayersupport.listener.login.AuthMeListener;
import cn.dsnbo.bedrockplayersupport.listener.teleport.CMITeleportListener;
import cn.dsnbo.bedrockplayersupport.listener.teleport.EssXTeleportListener;
import cn.dsnbo.bedrockplayersupport.util.Update;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * @author DongShaoNB
 */
public final class BedrockPlayerSupport extends JavaPlugin implements Listener {

    @Getter
    public static BedrockPlayerSupport instance;
    private boolean isCmiEnabled;
    private boolean isEssxEnabled;
    private boolean isHuskHomesEnabled;
    private boolean isAuthMeEnabled;
    private boolean isCatSeedLoginEnabled;
    private boolean isNexAuthEnabled;
    private boolean isFloodgateEnabled;


    @Override
    public void onEnable() {
        instance = this;
        loadDependLoadStatus();
        saveDefaultConfig();
        Update.updateConfig();
        loadConfig();
        printSupportPluginLoadStatus();
        getLogger().info("感谢选择使用本插件，作者: DongShaoNB，QQ群: 159323818");
        new Metrics(this, 17107);
    }

    @Override
    public void onDisable() {

    }

    private void loadDependLoadStatus() {
        isCmiEnabled = !(Bukkit.getPluginManager().getPlugin("CMI") == null);
        isEssxEnabled = !(Bukkit.getPluginManager().getPlugin("Essentials") == null);
        isHuskHomesEnabled = !(Bukkit.getPluginManager().getPlugin("HuskHomes") == null);
        isAuthMeEnabled = !(Bukkit.getPluginManager().getPlugin("AuthMe") == null);
        isCatSeedLoginEnabled = !(Bukkit.getPluginManager().getPlugin("CatSeedLogin") == null);
        isNexAuthEnabled = !(Bukkit.getPluginManager().getPlugin("NexAuth") == null);
        isFloodgateEnabled = !(Bukkit.getPluginManager().getPlugin("floodgate") == null);
    }

    public void loadConfig() {
        switch (getConfig().getString("basic-plugin").toLowerCase()) {
            case "cmi" -> {
                Bukkit.getPluginManager().registerEvents(new CMITeleportListener(), this);
            }
            case "essentialsx" -> {
                Bukkit.getPluginManager().registerEvents(new EssXTeleportListener(), this);
            }
            case "huskhomes" -> {
                Bukkit.getPluginManager().registerEvents(new HuskHomesTeleportListener(), this);
            }
            default -> {
                File file = new File(getDataFolder(), "/config.yml");
                if (isCmiEnabled) {
                    getConfig().set("basic-plugin", "cmi");
                    Bukkit.getPluginManager().registerEvents(new CMITeleportListener(), this);
                } else if (isEssxEnabled) {
                    getConfig().set("basic-plugin", "essentialsx");
                    Bukkit.getPluginManager().registerEvents(new EssXTeleportListener(), this);
                } else if (isHuskHomesEnabled) {
                    getConfig().set("basic-plugin", "huskhomes");
                    Bukkit.getPluginManager().registerEvents(new HuskHomesTeleportListener(), this);
                } else {
                    getConfig().set("basic-plugin", "disable");
                    getLogger().warning("检测不到支持的基础插件,已关闭相关功能!");
                }

                try {
                    getConfig().save(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (getConfig().getBoolean("login.enable")) {
            switch (getConfig().getString("login.plugin").toLowerCase()) {
                case "authme" -> {
                    Bukkit.getPluginManager().registerEvents(new AuthMeListener(), this);
                    getLogger().info("已开启基岩版玩家自动登录功能，使用插件: AuthMe");
                }
                case "catseedlogin" -> {
                    Bukkit.getPluginManager().registerEvents(new CatSeedLoginListener(), this);
                    getLogger().info("已开启基岩版玩家自动登录功能，使用插件: CatSeedLogin");
                }
                case "nexauth" -> {
                    Bukkit.getPluginManager().registerEvents(new NexAuthListener(), this);
                    getLogger().info("已开启基岩版玩家自动登录功能，使用插件: NexAuth");
                }
                case "other" -> {
                    Bukkit.getPluginManager().registerEvents(new OtherLoginListener(), this);
                    getLogger().info("已开启基岩版玩家自动登录功能，使用插件: 其他(控制台使用命令强制登录)");
                }
                default -> {
                    File file = new File(getDataFolder(), "/config.yml");
                    if (isAuthMeEnabled) {
                        getConfig().set("login.plugin", "authme");
                        Bukkit.getPluginManager().registerEvents(new AuthMeListener(), this);
                        getLogger().info("已开启基岩版玩家自动登录功能，使用插件: AuthMe");
                    } else if (isCatSeedLoginEnabled) {
                        getConfig().set("login.plugin", "catseedlogin");
                        Bukkit.getPluginManager().registerEvents(new CatSeedLoginListener(), this);
                        getLogger().info("已开启基岩版玩家自动登录功能，使用插件: CatSeedLogin");
                    } else if (isNexAuthEnabled) {
                        getConfig().set("login.plugin", "nexauth");
                        Bukkit.getPluginManager().registerEvents(new NexAuthListener(), this);
                        getLogger().info("已开启基岩版玩家自动登录功能，使用插件: NexAuth");
                    } else {
                        getConfig().set("login.enable", "false");
                        getLogger().warning("检测不到支持的登录插件，已关闭自动登录功能!");
                    }
                    try {
                        getConfig().save(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        if (getConfig().getBoolean("check-update")) {
            Update.checkUpdate();
        }

        if (getConfig().getBoolean("bedrock-player-teleport-menu")) {
            getCommand("tpagui").setExecutor(new TpaCommand());
            getCommand("tpaheregui").setExecutor(new TpaHereCommand());
        }

    }

    private void printSupportPluginLoadStatus() {
        getLogger().info("-----------------");
        getLogger().info("检测支持插件是否启用: ");
        getLogger().info("floodgate: " + isFloodgateEnabled);
        getLogger().info("CMI: " + isCmiEnabled);
        getLogger().info("Essentials: " + isEssxEnabled);
        getLogger().info("HuskHomes: " + isHuskHomesEnabled);
        getLogger().info("AuthMe: " + isAuthMeEnabled);
        getLogger().info("CatSeedLogin: " + isCatSeedLoginEnabled);
        getLogger().info("NexAuth: " + isNexAuthEnabled);
        getLogger().info("-----------------");
    }

}
