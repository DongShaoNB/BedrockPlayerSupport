package cc.dsnb.bedrockplayersupport

import cc.dsnb.bedrockplayersupport.AuthPlugin.*
import cc.dsnb.bedrockplayersupport.command.HomeFormCommand
import cc.dsnb.bedrockplayersupport.command.MsgFormCommand
import cc.dsnb.bedrockplayersupport.command.TpFormCommand
import cc.dsnb.bedrockplayersupport.config.LangConfig
import cc.dsnb.bedrockplayersupport.config.MainConfig
import cc.dsnb.bedrockplayersupport.form.MainForm
import cc.dsnb.bedrockplayersupport.form.basic.CMIForm
import cc.dsnb.bedrockplayersupport.form.basic.EssXForm
import cc.dsnb.bedrockplayersupport.form.basic.HuskHomesForm
import cc.dsnb.bedrockplayersupport.listener.PlayerRespawnListener
import cc.dsnb.bedrockplayersupport.listener.auth.AuthMeListener
import cc.dsnb.bedrockplayersupport.listener.auth.CatSeedLoginListener
import cc.dsnb.bedrockplayersupport.listener.auth.NexAuthListener
import cc.dsnb.bedrockplayersupport.listener.auth.OtherAuthListener
import cc.dsnb.bedrockplayersupport.listener.basic.CMIListener
import cc.dsnb.bedrockplayersupport.listener.basic.EssXListener
import cc.dsnb.bedrockplayersupport.listener.basic.HuskHomesListener
import cc.dsnb.bedrockplayersupport.manager.ConfigManager
import cc.dsnb.bedrockplayersupport.util.UpdateUtil
import com.github.Anon8281.universalScheduler.UniversalScheduler
import com.github.Anon8281.universalScheduler.scheduling.schedulers.TaskScheduler
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bstats.bukkit.Metrics
import org.bstats.charts.SimplePie
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.geysermc.floodgate.api.FloodgateApi
import java.io.File
import java.util.*

/**
 * @author DongShaoNB
 */
class BedrockPlayerSupport : JavaPlugin() {

    companion object {
        lateinit var instance: BedrockPlayerSupport
        lateinit var basicPlugin: BasicPlugin
        lateinit var authPlugin: AuthPlugin
        lateinit var mainConfigManager: ConfigManager<MainConfig>
        lateinit var langConfigManager: ConfigManager<LangConfig>
        lateinit var scheduler: TaskScheduler
        lateinit var languageInUse: String
        lateinit var mainForm: MainForm
        lateinit var cmiForm: CMIForm
        lateinit var essxForm: EssXForm
        lateinit var huskhomesForm: HuskHomesForm
        lateinit var floodgateApi: FloodgateApi
        val miniMessage: MiniMessage = MiniMessage.miniMessage()
        var systemLanguage: String = Locale.getDefault().language
    }

    override fun onEnable() {
        instance = this
        scheduler = UniversalScheduler.getScheduler(this)
        floodgateApi = FloodgateApi.getInstance()
        loadConfig()
        setPluginRunningStatus()
        loadFunction()
        loadCommand()
        loadMetrics()
        checkUpdate()
    }

    override fun onDisable() {

    }

    private fun loadConfig() {
        mainConfigManager =
            ConfigManager.create(dataFolder.toPath(), "config.yml", MainConfig::class.java).apply {
                reloadConfig()
                languageInUse = getConfigData().language()
            }
        val langDirectory = File(dataFolder, "/lang/")
        if (langDirectory.exists().not()) {
            langDirectory.mkdir()
        }
        ConfigManager.create(langDirectory.toPath(), "default.yml", LangConfig::class.java)
        if (File(langDirectory, "${languageInUse}.yml").exists().not()) {
            saveResource("lang/${languageInUse}.yml", false)
        }
        langConfigManager =
            ConfigManager.create(
                langDirectory.toPath(),
                "${languageInUse}.yml",
                LangConfig::class.java
            ).apply {
                reloadConfig()
            }
    }

    private fun setPluginRunningStatus() {
        val pluginManager = Bukkit.getPluginManager()
        basicPlugin = if (pluginManager.getPlugin("CMI") != null && "cmi".equals(
                mainConfigManager.getConfigData().basicPlugin(),
                ignoreCase = true
            )
        ) {
            BasicPlugin.CMI
        } else if (pluginManager.getPlugin("Essentials") != null && "essentialsx".equals(
                mainConfigManager.getConfigData().basicPlugin(), ignoreCase = true
            )
        ) {
            BasicPlugin.EssentialsX
        } else if (pluginManager.getPlugin("HuskHomes") != null && "huskhomes".equals(
                mainConfigManager.getConfigData().basicPlugin(), ignoreCase = true
            )
        ) {
            BasicPlugin.HuskHomes
        } else {
            BasicPlugin.None
        }
        authPlugin = if (pluginManager.getPlugin("AuthMe") != null && "authme".equals(
                mainConfigManager.getConfigData().authPlugin(), ignoreCase = true
            )
        ) {
            AuthMe
        } else if (pluginManager.getPlugin("CatSeedLogin") != null && "catseedlogin".equals(
                mainConfigManager.getConfigData().authPlugin(), ignoreCase = true
            )
        ) {
            CatSeedLogin
        } else if (pluginManager.getPlugin("NexAuth") != null && "nexauth".equals(
                mainConfigManager.getConfigData().authPlugin(), ignoreCase = true
            )
        ) {
            NexAuth
        } else if ("other".equals(
                mainConfigManager.getConfigData().authPlugin(),
                ignoreCase = true
            )
        ) {
            Other
        } else {
            None
        }
    }

    private fun loadFunction() {
        mainForm = MainForm().apply { load() }
        val pluginManager = Bukkit.getPluginManager()
        when (basicPlugin) {
            BasicPlugin.CMI -> {
                cmiForm = CMIForm()
                pluginManager.registerEvents(CMIListener(), this)
            }

            BasicPlugin.EssentialsX -> {
                essxForm = EssXForm()
                pluginManager.registerEvents(EssXListener(), this)
            }

            BasicPlugin.HuskHomes -> {
                huskhomesForm = HuskHomesForm()
                pluginManager.registerEvents(HuskHomesListener(), this)
            }

            else -> {}
        }
        when (authPlugin) {
            AuthMe -> pluginManager.registerEvents(AuthMeListener(), this)
            CatSeedLogin -> pluginManager.registerEvents(CatSeedLoginListener(), this)
            NexAuth -> pluginManager.registerEvents(NexAuthListener(), this)
            Other -> pluginManager.registerEvents(OtherAuthListener(), this)
            else -> {}
        }
        if (mainConfigManager.getConfigData().enableBackDeathLocForm()) {
            pluginManager.registerEvents(PlayerRespawnListener(), this)
        }
    }

    private fun loadCommand() {
        val config = mainConfigManager.getConfigData()
        if (config.enableTeleportForm()) {
            getCommand("tpgui")?.setExecutor(TpFormCommand())
        }
        if (config.enableMsgForm()) {
            getCommand("msggui")?.setExecutor(MsgFormCommand())
        }
        if (config.enableHomeForm()) {
            getCommand("homegui")?.setExecutor(HomeFormCommand())
        }
    }

    private fun loadMetrics() {
        Metrics(this, 17107).apply {
            this.addCustomChart(SimplePie("basic_plugin") {
                when (basicPlugin) {
                    BasicPlugin.CMI -> "CMI"
                    BasicPlugin.EssentialsX -> "EssentialsX"
                    BasicPlugin.HuskHomes -> "HuskHomes"
                    BasicPlugin.None -> "None"
                }
            })
            this.addCustomChart(SimplePie("auth_plugin") {
                when (authPlugin) {
                    AuthMe -> "AuthMe"
                    CatSeedLogin -> "CatSeedLogin"
                    NexAuth -> "NexAuth"
                    Other -> "Other"
                    None -> "None"
                }
            })
        }
    }

    private fun checkUpdate() {
        if (mainConfigManager.getConfigData().enableCheckUpdate()) {
            scheduler.runTaskAsynchronously {
                val currentVersion = description.version
                UpdateUtil.getLatestVersion { latestVersion ->
                    if (currentVersion == latestVersion) {
                        if ("zh".equals(systemLanguage, ignoreCase = true)) {
                            logger.info("插件是最新版本, 继续保持 ~")
                        } else {
                            logger.info("The plugin is the latest version, keep up ~")
                        }
                    } else {
                        if ("zh".equals(systemLanguage, ignoreCase = true)) {
                            logger.info("有新版本可以更新!")
                            logger.info("当前版本: $currentVersion | 最新版本: $latestVersion")
                        } else {
                            logger.info("There is a new version that can be updated!")
                            logger.info("Current version: $currentVersion | Latest version: $latestVersion")
                        }
                    }
                }
            }
        }
    }

}