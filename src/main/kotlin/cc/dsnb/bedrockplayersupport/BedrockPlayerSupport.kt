package cc.dsnb.bedrockplayersupport

import cc.dsnb.bedrockplayersupport.command.*
import cc.dsnb.bedrockplayersupport.config.LangConfig
import cc.dsnb.bedrockplayersupport.config.MainConfig
import cc.dsnb.bedrockplayersupport.form.MainForm
import cc.dsnb.bedrockplayersupport.form.basic.*
import cc.dsnb.bedrockplayersupport.listener.MainTabCompleteListener
import cc.dsnb.bedrockplayersupport.listener.PlayerRespawnListener
import cc.dsnb.bedrockplayersupport.listener.auth.AuthMeListener
import cc.dsnb.bedrockplayersupport.listener.auth.CatSeedLoginListener
import cc.dsnb.bedrockplayersupport.listener.auth.NexAuthListener
import cc.dsnb.bedrockplayersupport.listener.auth.OtherAuthListener
import cc.dsnb.bedrockplayersupport.listener.basic.*
import cc.dsnb.bedrockplayersupport.manager.ConfigManager
import cc.dsnb.bedrockplayersupport.util.UpdateUtil
import com.github.Anon8281.universalScheduler.UniversalScheduler
import com.github.Anon8281.universalScheduler.scheduling.schedulers.TaskScheduler
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import org.bstats.bukkit.Metrics
import org.bstats.charts.SimplePie
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.geysermc.floodgate.api.FloodgateApi
import java.io.File

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
        lateinit var basicForm: BasicForm
        lateinit var floodgateApi: FloodgateApi
        val miniMessage = MiniMessage.miniMessage()
        val legacySection = LegacyComponentSerializer.legacySection()
        var isPapiEnabled = false
        const val PREFIX = "[BedrockPlayerSupport] "
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
        // Don't need to do anything
    }

    private fun loadConfig() {
        mainConfigManager =
            ConfigManager.create(dataFolder.toPath(), "config.yml", MainConfig::class.java).also {
                it.reloadConfig()
                languageInUse = it.getConfigData().language()
            }
        val langDirectory = File(dataFolder, "/lang/")
        if (langDirectory.exists().not()) {
            langDirectory.mkdir()
        }
        ConfigManager.create(langDirectory.toPath(), "default.yml", LangConfig::class.java)
            .reloadConfig()
        if (File(langDirectory, "${languageInUse}.yml").exists().not()) {
            saveResource("lang/${languageInUse}.yml", false)
        }
        langConfigManager =
            ConfigManager.create(
                langDirectory.toPath(),
                "${languageInUse}.yml",
                LangConfig::class.java
            ).also {
                it.reloadConfig()
            }
    }

    private fun setPluginRunningStatus() {
        val pluginManager = Bukkit.getPluginManager()

        val basicPluginNameToEnum = mapOf(
            "cmi" to BasicPlugin.CMI,
            "essentialsx" to BasicPlugin.ESSENTIALS,
            "huskhomes" to BasicPlugin.HUSKHOMES,
            "advancedteleport" to BasicPlugin.ADVANCEDTELEPORT,
            "sunlight" to BasicPlugin.SUNLIGHT
        )
        val configBasicPlugin = mainConfigManager.getConfigData().basicPlugin().lowercase()
        val selectedPlugin = basicPluginNameToEnum[configBasicPlugin]
        basicPlugin = if ("auto" == configBasicPlugin) {
            listOf("CMI", "Essentials", "HuskHomes", "AdvancedTeleport", "SunLight").firstOrNull { plugin ->
                pluginManager.isPluginEnabled(plugin)
            }?.let {
                BasicPlugin.valueOf(it.uppercase())
            } ?: BasicPlugin.NONE
        } else {
            selectedPlugin ?: BasicPlugin.NONE
        }

        val authPluginNameToEnum = mapOf(
            "authme" to AuthPlugin.AUTHME,
            "catseedlogin" to AuthPlugin.CATSEEDLOGIN,
            "nexauth" to AuthPlugin.NEXAUTH,
            "other" to AuthPlugin.OTHER,
            "none" to AuthPlugin.NONE
        )
        val configAuthPlugin = mainConfigManager.getConfigData().authPlugin().lowercase()
        val selectedAuthPlugin = authPluginNameToEnum[configAuthPlugin]
        authPlugin = if ("auto" == configAuthPlugin) {
            listOf("AuthMe", "CatSeedLogin", "NexAuth").firstOrNull { plugin ->
                pluginManager.isPluginEnabled(plugin)
            }?.let {
                AuthPlugin.valueOf(it.uppercase())
            } ?: AuthPlugin.OTHER
        } else {
            selectedAuthPlugin ?: AuthPlugin.NONE
        }

        isPapiEnabled = pluginManager.isPluginEnabled("PlaceholderAPI")
    }

    private fun loadFunction() {
        mainForm = MainForm()
        val pluginManager = Bukkit.getPluginManager()
        val enableRTF = mainConfigManager.getConfigData().enableReceiveTeleportForm()
        when (basicPlugin) {
            BasicPlugin.CMI -> {
                basicForm = CMIForm()
                if (enableRTF) pluginManager.registerEvents(CMIListener(), this)
            }

            BasicPlugin.ESSENTIALS -> {
                basicForm = EssXForm()
                if (enableRTF) pluginManager.registerEvents(EssXListener(), this)
            }

            BasicPlugin.HUSKHOMES -> {
                basicForm = HuskHomesForm()
                if (enableRTF) pluginManager.registerEvents(HuskHomesListener(), this)
            }

            BasicPlugin.ADVANCEDTELEPORT -> {
                basicForm = ATForm()
                if (enableRTF) pluginManager.registerEvents(ATListener(), this)
            }

            BasicPlugin.SUNLIGHT -> {
                basicForm = SunLightForm()
                if (enableRTF) pluginManager.registerEvents(SunLightListener(), this)
            }

            BasicPlugin.NONE -> {
                // Don't need to do anything
            }
        }
        when (authPlugin) {
            AuthPlugin.AUTHME -> pluginManager.registerEvents(AuthMeListener(), this)
            AuthPlugin.CATSEEDLOGIN -> pluginManager.registerEvents(CatSeedLoginListener(), this)
            AuthPlugin.NEXAUTH -> pluginManager.registerEvents(NexAuthListener(), this)
            AuthPlugin.OTHER -> pluginManager.registerEvents(OtherAuthListener(), this)
            AuthPlugin.NONE -> {
                // Don't need to do anything
            }
        }
        if (mainConfigManager.getConfigData().enableBackDeathLocForm()) {
            pluginManager.registerEvents(PlayerRespawnListener(), this)
        }
    }

    private fun loadCommand() {
        val config = mainConfigManager.getConfigData()
        getCommand("bedrockplayersupport")?.also {
            it.setExecutor(MainCommand())
            it.tabCompleter = MainTabCompleteListener()
        }
        if (config.enableTeleportForm()) {
            getCommand("tpgui")?.setExecutor(TpFormCommand())
        }
        if (config.enableMsgForm()) {
            getCommand("msggui")?.setExecutor(MsgFormCommand())
        }
        if (config.enableHomeForm()) {
            getCommand("homegui")?.setExecutor(HomeFormCommand())
        }
        if (config.enablePublicHomeForm()) {
            getCommand("phomegui")?.setExecutor(PublicHomeFormCommand())
        }
        if (config.enableWarpForm()) {
            getCommand("warpgui")?.setExecutor(WarpFormCommand())
        }
        if (config.enableKitForm()) {
            getCommand("kitgui")?.setExecutor(KitFormCommand())
        }
    }

    private fun loadMetrics() {
        Metrics(this, 17107).also {
            it.addCustomChart(SimplePie("basic_plugin") {
                basicPlugin.pluginName
            })
            it.addCustomChart(SimplePie("auth_plugin") {
                authPlugin.pluginName
            })
        }
    }

    private fun checkUpdate() {
        if (mainConfigManager.getConfigData().enableCheckUpdate()) {
            scheduler.runTaskAsynchronously {
                val currentVersion = description.version
                UpdateUtil.getLatestVersion { latestVersion ->
                    if (currentVersion == latestVersion) {
                        if ("zh_cn".equals(languageInUse, ignoreCase = true)) {
                            logger.info("插件是最新版本, 继续保持 ~")
                        } else {
                            logger.info("The plugin is the latest version, keep up ~")
                        }
                    } else {
                        if ("zh_cn".equals(languageInUse, ignoreCase = true)) {
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