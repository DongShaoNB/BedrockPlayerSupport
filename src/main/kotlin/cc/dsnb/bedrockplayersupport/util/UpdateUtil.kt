package cc.dsnb.bedrockplayersupport.util

import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import org.bukkit.util.Consumer
import java.io.IOException
import java.net.URI
import java.util.*

/**
 * @author DongShaoNB
 */
object UpdateUtil {

    private const val SPIGOT_RESOURCE_ID = 115450

    fun getLatestVersion(consumer: Consumer<String>) {
        try {
            URI("https://api.spigotmc.org/legacy/update.php?resource=$SPIGOT_RESOURCE_ID/~").toURL()
                .openStream().use { `is` ->
                    Scanner(`is`).use { scanner ->
                        if (scanner.hasNext()) {
                            consumer.accept(scanner.next())
                        }
                    }
                }
        } catch (e: IOException) {
            if ("zh_cn".equals(BedrockPlayerSupport.languageInUse, ignoreCase = true)) {
                BedrockPlayerSupport.instance.logger
                    .warning("无法检查更新: " + e.message)
            } else {
                BedrockPlayerSupport.instance.logger
                    .warning("Unable to check for update: " + e.message)
            }
        }
    }

}