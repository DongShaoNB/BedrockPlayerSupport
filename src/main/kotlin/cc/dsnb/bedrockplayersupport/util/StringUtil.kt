package cc.dsnb.bedrockplayersupport.util

import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import me.clip.placeholderapi.PlaceholderAPI
import net.kyori.adventure.text.Component
import org.bukkit.entity.Player
import java.security.SecureRandom

/**
 * @author DongShaoNB
 */
object StringUtil {

    fun randomString(length: Int): String {
        val str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        val random = SecureRandom()
        val stringBuilder = StringBuilder()
        for (i in 0 until length) {
            val number = random.nextInt(62)
            stringBuilder.append(str[number])
        }
        return stringBuilder.toString()
    }

    fun formatTextToComponent(player: Player?, originalText: String): Component {
        return BedrockPlayerSupport.miniMessage.deserialize(
            if (BedrockPlayerSupport.mainConfigManager.getConfigData()
                    .enableSupportPAPI() && BedrockPlayerSupport.isPapiEnabled
            ) {
                PlaceholderAPI.setPlaceholders(player, originalText)
            } else {
                originalText
            }
        )
    }

    fun formatTextToString(player: Player?, originalText: String): String {
        return BedrockPlayerSupport.legacySection.serialize(
            formatTextToComponent(player, originalText)
        )
    }

    fun String.ensureStartsWithSlash(): String {
        return if (this.startsWith("/")) {
            this
        } else {
            "/$this"
        }
    }
}