package cc.dsnb.bedrockplayersupport.command

import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import cc.dsnb.bedrockplayersupport.util.StringUtil
import cc.dsnb.bedrockplayersupport.util.TimeUtil
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * @author DongShaoNB
 */
class MainCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (args.isEmpty()) return false
        
        val isZh = BedrockPlayerSupport.languageInUse.equals("zh_cn", ignoreCase = true)
        val isPlayer = sender is Player

        when (args[0].lowercase()) {
            "status" -> {
                val basicPluginName = BedrockPlayerSupport.basicPlugin.pluginName
                val authPluginName = BedrockPlayerSupport.authPlugin.pluginName

                val prefix = if (isPlayer) "" else BedrockPlayerSupport.PREFIX
                val basicMessage = if (isZh) "基础插件: $basicPluginName" else "Basic plugin: $basicPluginName"
                val authMessage = if (isZh) "验证插件: $authPluginName" else "Auth plugin: $authPluginName"

                sender.sendMessage(prefix + basicMessage)
                sender.sendMessage(prefix + authMessage)
                return true
            }

            "reload" -> {
                val langConfig = BedrockPlayerSupport.langConfigManager.getConfigData()
                val time = TimeUtil.measureTimeMillis {
                    BedrockPlayerSupport.mainConfigManager.reloadConfig()
                    BedrockPlayerSupport.langConfigManager.reloadConfig()
                }

                val reloadMessage = langConfig.reloadSuccessfully().replace("%time%", time.toString())
                val formattedMessage = StringUtil.formatTextToComponent(sender as? Player, reloadMessage)

                sender.sendMessage(
                    if (isPlayer) formattedMessage else StringUtil.formatTextToComponent(
                        null,
                        BedrockPlayerSupport.PREFIX + reloadMessage
                    )
                )
                return true
            }

            else -> return false
        }
    }

}