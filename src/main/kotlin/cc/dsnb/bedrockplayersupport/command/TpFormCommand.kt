package cc.dsnb.bedrockplayersupport.command

import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * @author DongShaoNB
 */
class TpFormCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender is Player) {
            if (BedrockPlayerSupport.floodgateApi.isFloodgatePlayer(sender.uniqueId)) {
                if (Bukkit.getOnlinePlayers().size > 1) {
                    BedrockPlayerSupport.mainForm.openBedrockTeleportForm(sender)
                    return true
                } else {
                    sender.sendMessage(
                        BedrockPlayerSupport.miniMessage
                            .deserialize(
                                BedrockPlayerSupport.langConfigManager.getConfigData()
                                    .noOtherOnlinePlayer()
                            )
                    )
                }
            } else {
                sender.sendMessage(
                    BedrockPlayerSupport.miniMessage.deserialize(
                        BedrockPlayerSupport.langConfigManager.getConfigData().notBedrockPlayer()
                    )
                )
            }
        }
        return false
    }

}