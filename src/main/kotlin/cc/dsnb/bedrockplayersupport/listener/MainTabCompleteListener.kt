package cc.dsnb.bedrockplayersupport.listener

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class MainTabCompleteListener : TabCompleter {

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        s: String,
        strings: Array<String>,
    ): List<String>? {
        if (sender.hasPermission("bedrockplayersupport.admin")) {
            return when (strings.size) {
                1 -> listOf("status", "reload")
                else -> return null
            }
        }
        return null
    }

}