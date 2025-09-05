package cc.dsnb.bedrockplayersupport.util

import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import java.net.URL
import java.util.*

object UpdateUtil {

    private const val SPIGOT_RESOURCE_ID = 115450

    fun getLatestVersion(consumer: (String) -> Unit) {
        runCatching {
            URL("https://api.spigotmc.org/legacy/update.php?resource=$SPIGOT_RESOURCE_ID/~")
                .openStream().use { input ->
                    Scanner(input).use { scanner ->
                        if (scanner.hasNext()) {
                            consumer(scanner.next())
                        }
                    }
                }
        }.onFailure { exception ->
            val message = if (BedrockPlayerSupport.languageInUse == "zh_cn") {
                "无法检查更新: ${exception.message}"
            } else {
                "Unable to check for update: ${exception.message}"
            }
            BedrockPlayerSupport.instance.logger.warning(message)
        }
    }

}