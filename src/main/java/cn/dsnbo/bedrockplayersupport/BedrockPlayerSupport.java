package cn.dsnbo.bedrockplayersupport;

import cn.dsnbo.bedrockplayersupport.command.HomeFormCommand;
import cn.dsnbo.bedrockplayersupport.command.MsgFormCommand;
import cn.dsnbo.bedrockplayersupport.command.TpFormCommand;
import cn.dsnbo.bedrockplayersupport.config.Config;
import cn.dsnbo.bedrockplayersupport.config.ConfigManager;
import cn.dsnbo.bedrockplayersupport.config.Language;
import cn.dsnbo.bedrockplayersupport.form.MainForm;
import cn.dsnbo.bedrockplayersupport.listener.PlayerListener;
import cn.dsnbo.bedrockplayersupport.listener.login.CatSeedLoginListener;
import cn.dsnbo.bedrockplayersupport.listener.login.NexAuthListener;
import cn.dsnbo.bedrockplayersupport.listener.login.OtherAuthListener;
import cn.dsnbo.bedrockplayersupport.listener.teleport.HuskHomesTeleportListener;
import cn.dsnbo.bedrockplayersupport.listener.login.AuthMeListener;
import cn.dsnbo.bedrockplayersupport.listener.teleport.CMITeleportListener;
import cn.dsnbo.bedrockplayersupport.listener.teleport.EssXTeleportListener;
import cn.dsnbo.bedrockplayersupport.util.Update;
import java.io.File;
import java.util.Locale;
import com.github.Anon8281.universalScheduler.UniversalScheduler;
import com.github.Anon8281.universalScheduler.scheduling.schedulers.TaskScheduler;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.geysermc.floodgate.api.FloodgateApi;

/**
 * @author DongShaoNB
 */
public final class BedrockPlayerSupport extends JavaPlugin {

  @Getter
  private static BedrockPlayerSupport instance;
  @Getter
  private static FloodgateApi floodgateApi;
  @Getter
  private static BasicPlugin basicPlugin;
  @Getter
  private static ConfigManager<Config> mainConfigManager;
  @Getter
  private static ConfigManager<Language> languageConfigManager;
  @Getter
  private static String language;
  @Getter
  private static String systemLanguage;
  @Getter
  private static TaskScheduler scheduler;
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
    scheduler = UniversalScheduler.getScheduler(this);
    floodgateApi = FloodgateApi.getInstance();
    systemLanguage = Locale.getDefault().getLanguage();
    loadConfig();
    loadDependLoadStatus();
    printSupportPluginLoadStatus();
    registerBasicListener();
    registerAuthListener();
    loadMetrics();
    new MainForm();
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
    File langDirectory = new File(getDataFolder(), "/lang/");
    if (!langDirectory.exists()) {
      langDirectory.mkdir();
    }
    mainConfigManager = ConfigManager.create(getDataFolder().toPath(), "config.yml", Config.class);
    mainConfigManager.reloadConfig();
    Config config = getMainConfigManager().getConfigData();
    language = config.language();
    saveResource("/lang/" + language + ".yml", false);
    languageConfigManager = ConfigManager.create(langDirectory.toPath(), language + ".yml",
        Language.class);
    languageConfigManager.reloadConfig();
    if (config.enableTeleportForm()) {
      getCommand("tpgui").setExecutor(new TpFormCommand());
    }
    if (config.enableMsgForm()) {
      getCommand("msggui").setExecutor(new MsgFormCommand());
    }
    if (config.enableHomeForm()) {
      getCommand("homegui").setExecutor(new HomeFormCommand());
    }
    if (config.enableBackForm()) {
      Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
    }
    if (config.checkUpdate()) {
      Update.checkUpdate(latestVersion -> {
        String currentVersion = getDescription().getVersion();
        if (currentVersion.equals(latestVersion)) {
          if ("zh".equalsIgnoreCase(systemLanguage)) {
            getLogger().info("插件是最新版本，继续保持~");
          } else {
            getLogger().info("The plugin is the latest version");
          }
        } else {
          if ("zh".equalsIgnoreCase(systemLanguage)) {
            getLogger().info("有新版本可以更新!");
            getLogger().info("当前版本: " + currentVersion + " | 最新版本: " + latestVersion);
          } else {
            getLogger().info("There is a new version that can be updated!");
          getLogger().info("Current version: " + currentVersion + " | Latest version: " + latestVersion);
          }
        }
      });
    }
  }

  private void registerBasicListener() {
    switch (getMainConfigManager().getConfigData().basicPlugin().toLowerCase()) {
      case "cmi" -> {
        if (isCmiEnabled) {
          basicPlugin = BasicPlugin.CMI;
          Bukkit.getPluginManager().registerEvents(new CMITeleportListener(), this);
        } else {
          basicPlugin = BasicPlugin.NONE;
        }
      }
      case "essentialsx" -> {
        if (isEssxEnabled) {
          basicPlugin = BasicPlugin.EssentialsX;
          Bukkit.getPluginManager().registerEvents(new EssXTeleportListener(), this);
        } else {
          basicPlugin = BasicPlugin.NONE;
        }
      }
      case "huskhomes" -> {
        if (isHuskHomesEnabled) {
          basicPlugin = BasicPlugin.HuskHomes;
          Bukkit.getPluginManager().registerEvents(new HuskHomesTeleportListener(), this);
        } else {
          basicPlugin = BasicPlugin.NONE;
        }
      }
      case "none" -> {
        basicPlugin = BasicPlugin.NONE;
      }
      default -> {
        if (isCmiEnabled) {
          Bukkit.getPluginManager().registerEvents(new CMITeleportListener(), this);
          basicPlugin = BasicPlugin.CMI;
        } else if (isEssxEnabled) {
          Bukkit.getPluginManager().registerEvents(new EssXTeleportListener(), this);
          basicPlugin = BasicPlugin.EssentialsX;
        } else if (isHuskHomesEnabled) {
          Bukkit.getPluginManager().registerEvents(new HuskHomesTeleportListener(), this);
          basicPlugin = BasicPlugin.HuskHomes;
        } else {
          basicPlugin = BasicPlugin.NONE;
        }
      }
    }
  }


  private void registerAuthListener() {
    if (getMainConfigManager().getConfigData().enableLogin()
        || getMainConfigManager().getConfigData().enableRegister()) {
      switch (getMainConfigManager().getConfigData().authPlugin().toLowerCase()) {
        case "authme" -> {
          if (isAuthMeEnabled) {
            Bukkit.getPluginManager().registerEvents(new AuthMeListener(), this);
          }
        }
        case "catseedlogin" -> {
          if (isCatSeedLoginEnabled) {
            Bukkit.getPluginManager().registerEvents(new CatSeedLoginListener(), this);
          }
        }
        case "nexauth" -> {
          if (isNexAuthEnabled) {
            Bukkit.getPluginManager().registerEvents(new NexAuthListener(), this);
          }
        }
        case "other" -> {
          Bukkit.getPluginManager().registerEvents(new OtherAuthListener(), this);
        }
        default -> {
          if (isAuthMeEnabled) {
            Bukkit.getPluginManager().registerEvents(new AuthMeListener(), this);
          } else if (isCatSeedLoginEnabled) {
            Bukkit.getPluginManager().registerEvents(new CatSeedLoginListener(), this);
          } else if (isNexAuthEnabled) {
            Bukkit.getPluginManager().registerEvents(new NexAuthListener(), this);
          }
        }
      }
    }
  }


  private void printSupportPluginLoadStatus() {
    if (getMainConfigManager().getConfigData().loggingSupportPluginStatus()) {
      getLogger().info("-----------------");
      if ("zh".equalsIgnoreCase(systemLanguage)) {
        getLogger().info("检测支持插件是否启用: ");
      } else {
        getLogger().info("Check support plugin enable status: ");
      }
      getLogger().info("floodgate: " + isFloodgateEnabled);
      getLogger().info("CMI: " + isCmiEnabled);
      getLogger().info("EssentialsX: " + isEssxEnabled);
      getLogger().info("HuskHomes: " + isHuskHomesEnabled);
      getLogger().info("AuthMe: " + isAuthMeEnabled);
      getLogger().info("CatSeedLogin: " + isCatSeedLoginEnabled);
      getLogger().info("NexAuth: " + isNexAuthEnabled);
      getLogger().info("-----------------");
    }
  }

  private void loadMetrics() {
    Metrics metrics = new Metrics(this, 17107);
    metrics.addCustomChart(new Metrics.SimplePie("basic_plugin", () -> {
      switch (getBasicPlugin()) {
        case CMI -> {
          return "CMI";
        }
        case EssentialsX -> {
          return "EssentialsX";
        }
        case HuskHomes -> {
          return "HuskHomes";
        }
        default -> {
          return "NONE";
        }
      }
    }));
  }

}
