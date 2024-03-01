package cn.dsnbo.bedrockplayersupport;

import cn.dsnbo.bedrockplayersupport.command.HomeFormCommand;
import cn.dsnbo.bedrockplayersupport.command.MsgFormCommand;
import cn.dsnbo.bedrockplayersupport.command.TpFormCommand;
import cn.dsnbo.bedrockplayersupport.config.Config;
import cn.dsnbo.bedrockplayersupport.config.ConfigManager;
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
    new MainForm();
    loadConfig();
    loadDependLoadStatus();
    printSupportPluginLoadStatus();
    registerBasicListener();
    registerAuthListener();
    loadMetrics();
    getLogger().info("感谢选择使用本插件, 交流QQ群: 159323818");
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
    mainConfigManager = ConfigManager.create(getDataFolder().toPath(), "config.yml", Config.class);
    getMainConfigManager().reloadConfig();
    Config config = getMainConfigManager().getConfigData();
    // Temporary disable version checker to prepare for bug fix release
//    if (config.checkUpdate()) {
//      Update.checkUpdate(version -> {
//        if (getDescription().getVersion().equals(version)) {
//          getLogger().info("当前版本已经是最新");
//        } else {
//          getLogger().info("检测到新版本, 请更新插件");
//        }
//      });
//    }
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
  }

  private void registerBasicListener() {
    switch (getMainConfigManager().getConfigData().basicPlugin().toLowerCase()) {
      case "cmi" -> {
        if (isCmiEnabled) {
          basicPlugin = BasicPlugin.CMI;
          Bukkit.getPluginManager().registerEvents(new CMITeleportListener(), this);
          getLogger().info("已注册基础插件监听器, 使用插件: CMI");
        } else {
          basicPlugin = BasicPlugin.NONE;
          getLogger().warning("配置文件设置了CMI基础插件, 但没有检测到CMI, 已关闭相关功能");
        }
      }
      case "essentialsx" -> {
        if (isEssxEnabled) {
          basicPlugin = BasicPlugin.EssentialsX;
          Bukkit.getPluginManager().registerEvents(new EssXTeleportListener(), this);
          getLogger().info("已注册基础插件监听器, 使用插件: EssentialsX");
        } else {
          basicPlugin = BasicPlugin.NONE;
          getLogger().warning(
              "配置文件设置了EssentialsX基础插件, 但没有检测到EssentialsX, 已关闭相关功能");
        }
      }
      case "huskhomes" -> {
        if (isHuskHomesEnabled) {
          basicPlugin = BasicPlugin.HuskHomes;
          Bukkit.getPluginManager().registerEvents(new HuskHomesTeleportListener(), this);
          getLogger().info("已注册基础插件监听器, 使用插件: HuskHomes");
        } else {
          basicPlugin = BasicPlugin.NONE;
          getLogger().warning(
              "配置文件设置了HuskHomes基础插件, 但没有检测到HuskHomes, 已关闭相关功能");
        }
      }
      case "none" -> {
        basicPlugin = BasicPlugin.NONE;
        getLogger().info("已关闭基础插件功能(配置文件设置)");
      }
      default -> {
        if (isCmiEnabled) {
          Bukkit.getPluginManager().registerEvents(new CMITeleportListener(), this);
          getLogger().info("已注册基础插件监听器, 使用插件: CMI");
          basicPlugin = BasicPlugin.CMI;
        } else if (isEssxEnabled) {
          Bukkit.getPluginManager().registerEvents(new EssXTeleportListener(), this);
          getLogger().info("已注册基础插件监听器, 使用插件: EssentialsX");
          basicPlugin = BasicPlugin.EssentialsX;
        } else if (isHuskHomesEnabled) {
          Bukkit.getPluginManager().registerEvents(new HuskHomesTeleportListener(), this);
          getLogger().info("已注册基础插件监听器, 使用插件: HuskHomes");
          basicPlugin = BasicPlugin.HuskHomes;
        } else {
          basicPlugin = BasicPlugin.NONE;
          getLogger().warning("检测不到支持的基础插件, 已关闭相关功能");
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
            getLogger().info("已开启基岩版玩家自动验证功能, 使用插件: AuthMe");
          } else {
            getLogger().warning("配置文件设置了AuthMe登录插件, 但没有检测到AuthMe, 已关闭相关功能");
          }
        }
        case "catseedlogin" -> {
          if (isCatSeedLoginEnabled) {
            Bukkit.getPluginManager().registerEvents(new CatSeedLoginListener(), this);
            getLogger().info("已开启基岩版玩家自动验证功能, 使用插件: CatSeedLogin");
          } else {
            getLogger().warning(
                "配置文件设置了CatSeedLogin登录插件, 但没有检测到CatSeedLogin, 已关闭相关功能");
          }
        }
        case "nexauth" -> {
          if (isNexAuthEnabled) {
            Bukkit.getPluginManager().registerEvents(new NexAuthListener(), this);
            getLogger().info("已开启基岩版玩家自动验证功能, 使用插件: NexAuth");
          } else {
            getLogger().warning(
                "配置文件设置了NexAuth登录插件, 但没有检测到NexAuth, 已关闭相关功能");
          }
        }
        case "other" -> {
          Bukkit.getPluginManager().registerEvents(new OtherAuthListener(), this);
          getLogger().info("已开启基岩版玩家自动验证功能, 使用插件: Other");
        }
        default -> {
          if (isAuthMeEnabled) {
            Bukkit.getPluginManager().registerEvents(new AuthMeListener(), this);
            getLogger().info("已开启基岩版玩家自动验证功能, 使用插件: AuthMe");
          } else if (isCatSeedLoginEnabled) {
            Bukkit.getPluginManager().registerEvents(new CatSeedLoginListener(), this);
            getLogger().info("已开启基岩版玩家自动验证功能, 使用插件: CatSeedLogin");
          } else if (isNexAuthEnabled) {
            Bukkit.getPluginManager().registerEvents(new NexAuthListener(), this);
            getLogger().info("已开启基岩版玩家自动验证功能, 使用插件: NexAuth");
          } else {
            getLogger().warning("检测不到支持的登录插件, 已关闭自动验证功能!");
          }
        }
      }
    }
  }


  private void printSupportPluginLoadStatus() {
    if (getMainConfigManager().getConfigData().loggingSupportPluginStatus()) {
      getLogger().info("-----------------");
      getLogger().info("检测支持插件是否启用: ");
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
