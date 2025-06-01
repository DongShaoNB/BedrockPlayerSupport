package cc.dsnb.bedrockplayersupport.util

import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import org.bukkit.util.Consumer
import java.net.URL
import java.util.*

/**
 * @author DongShaoNB
 */
object UpdateUtil {

    private const val SPIGOT_RESOURCE_ID = 115450

    fun getLatestVersion(consumer: Consumer<String>) {
        runCatching {
            URL("https://api.spigotmc.org/legacy/update.php?resource=$SPIGOT_RESOURCE_ID/~")
                .openStream().use { `is` ->
                    Scanner(`is`).use { scanner ->
                        if (scanner.hasNext()) {
                            consumer.accept(scanner.next())
                        }
                    }
                }
        }.onFailure { exception ->
            val message = if (BedrockPlayerSupport.languageInUse.equals("zh_cn", ignoreCase = true)) {
                "无法检查更新: ${exception.message}"
            } else {
                "Unable to check for update: ${exception.message}"
            }
            BedrockPlayerSupport.instance.logger.warning(message)
        }
    }

}