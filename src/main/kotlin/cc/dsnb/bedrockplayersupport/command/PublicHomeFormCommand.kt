package cc.dsnb.bedrockplayersupport.command

import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import cc.dsnb.bedrockplayersupport.form.basic.HuskHomesForm
import cc.dsnb.bedrockplayersupport.util.StringUtil
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class PublicHomeFormCommand : CommandExecutor {

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<String>,
    ): Boolean {
        if (sender is Player) {
            if (BedrockPlayerSupport.floodgateApi.isFloodgatePlayer(sender.uniqueId)) {
                val basicForm = BedrockPlayerSupport.basicForm
                if (basicForm is HuskHomesForm) {
                    basicForm.sendPublicHomeForm(sender)
                }
            } else {
                sender.sendMessage(
                    StringUtil.formatTextToComponent(
                        sender,
                        BedrockPlayerSupport.langConfigManager.getConfigData().notBedrockPlayer()
                    )
                )
            }
        }
        return false
    }

}