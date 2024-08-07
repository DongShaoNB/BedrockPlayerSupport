package cc.dsnb.bedrockplayersupport.command

import cc.dsnb.bedrockplayersupport.BasicPlugin.*
import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * @author DongShaoNB
 */
class WarpFormCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender is Player) {
            if (BedrockPlayerSupport.floodgateApi.isFloodgatePlayer(sender.uniqueId)) {
                when (BedrockPlayerSupport.basicPlugin) {
                            CMI -> BedrockPlayerSupport.cmiForm.sendWarpForm(sender)
                            EssentialsX -> BedrockPlayerSupport.essxForm.sendWarpForm(sender)
                            HuskHomes -> BedrockPlayerSupport.huskhomesForm.sendWarpForm(sender)
                            AdvancedTeleport -> BedrockPlayerSupport.atForm.sendWarpForm(sender)
                            None -> {
                                // Don't need to do anything
                            }
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