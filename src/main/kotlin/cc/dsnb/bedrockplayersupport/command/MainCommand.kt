package cc.dsnb.bedrockplayersupport.command

import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
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
        if (args.isNotEmpty()) {
            when (args[0]) {
                "status" -> {
                    if (sender is Player) {
                        if ("zh_cn".equals(BedrockPlayerSupport.languageInUse, ignoreCase = true)) {
                            sender.sendMessage("基础插件: " + BedrockPlayerSupport.basicPlugin.name)
                            sender.sendMessage("验证插件: " + BedrockPlayerSupport.authPlugin.name)
                        } else {
                            sender.sendMessage("Basic plugin: " + BedrockPlayerSupport.basicPlugin.name)
                            sender.sendMessage("Auth plugin: " + BedrockPlayerSupport.authPlugin.name)
                        }
                    } else {
                        if ("zh_cn".equals(BedrockPlayerSupport.languageInUse, ignoreCase = true)) {
                            sender.sendMessage(BedrockPlayerSupport.PREFIX + "基础插件: " + BedrockPlayerSupport.basicPlugin.name)
                            sender.sendMessage(BedrockPlayerSupport.PREFIX + "验证插件: " + BedrockPlayerSupport.authPlugin.name)
                        } else {
                            sender.sendMessage(BedrockPlayerSupport.PREFIX + "Basic plugin: " + BedrockPlayerSupport.basicPlugin.name)
                            sender.sendMessage(BedrockPlayerSupport.PREFIX + "Auth plugin: " + BedrockPlayerSupport.authPlugin.name)
                        }
                    }
                    return true
                }

                "reload" -> {
                    val langConfig = BedrockPlayerSupport.langConfigManager.getConfigData()
                    val time = TimeUtil.measureTimeMillis {
                        BedrockPlayerSupport.mainConfigManager.reloadConfig()
                        BedrockPlayerSupport.langConfigManager.reloadConfig()
                    }
                    if (sender is Player) {
                        sender.sendMessage(
                            BedrockPlayerSupport.miniMessage.deserialize(
                                langConfig.reloadSuccessfully()
                                    .replace("%time%", time.toString())
                            )
                        )
                    } else {
                        sender.sendMessage(
                            BedrockPlayerSupport.miniMessage.deserialize(
                                BedrockPlayerSupport.PREFIX +
                                        langConfig.reloadSuccessfully()
                                            .replace("%time%", time.toString())
                            )
                        )
                    }
                    return true
                }
            }
        }
        return false
    }

}