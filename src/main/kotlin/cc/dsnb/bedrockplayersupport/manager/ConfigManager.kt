package cc.dsnb.bedrockplayersupport.manager

import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import space.arim.dazzleconf.ConfigurationOptions
import space.arim.dazzleconf.error.ConfigFormatSyntaxException
import space.arim.dazzleconf.error.InvalidConfigException
import space.arim.dazzleconf.ext.snakeyaml.CommentMode
import space.arim.dazzleconf.ext.snakeyaml.SnakeYamlConfigurationFactory
import space.arim.dazzleconf.ext.snakeyaml.SnakeYamlOptions
import space.arim.dazzleconf.helper.ConfigurationHelper
import java.io.IOException
import java.io.UncheckedIOException
import java.nio.file.Path
import kotlin.concurrent.Volatile

/**
 * @author A248
 */
class ConfigManager<C> private constructor(private val configHelper: ConfigurationHelper<C>) {
    @Volatile
    private var configData: C? = null

    fun reloadConfig() {
        try {
            configData = configHelper.reloadConfigData()
        } catch (ex: IOException) {
            throw UncheckedIOException(ex)
        } catch (ex: ConfigFormatSyntaxException) {
            configData = configHelper.factory.loadDefaults()
            BedrockPlayerSupport.instance.logger
                .severe("配置文件格式错误, 请检查你的配置文件 | The yaml syntax in your configuration is invalid")
            ex.printStackTrace()
        } catch (ex: InvalidConfigException) {
            configData = configHelper.factory.loadDefaults()
            BedrockPlayerSupport.instance.logger
                .severe("配置文件某个键值无效, 请检查你的配置文件 | One of the values in your configuration is not valid")
            ex.printStackTrace()
        }
    }

    fun getConfigData(): C {
        val configData = this.configData
            ?: throw IllegalStateException("配置文件还没有加载 | Configuration has not been loaded yet")
        return configData
    }

    companion object {
        fun <C> create(
            configFolder: Path?, fileName: String?,
            configClass: Class<C>?
        ): ConfigManager<C> {
            // SnakeYaml example
            val yamlOptions = SnakeYamlOptions.Builder()
                .commentMode(CommentMode.alternativeWriter()) // Enables writing YAML comments
                .build()
            val configFactory = SnakeYamlConfigurationFactory.create(
                configClass,
                ConfigurationOptions.defaults(), // change this if desired
                yamlOptions
            )
            return ConfigManager(ConfigurationHelper(configFolder, fileName, configFactory))
        }
    }
}
