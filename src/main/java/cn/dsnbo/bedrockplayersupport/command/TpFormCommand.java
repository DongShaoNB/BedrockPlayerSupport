package cn.dsnbo.bedrockplayersupport.command;

import cn.dsnbo.bedrockplayersupport.BedrockPlayerSupport;
import cn.dsnbo.bedrockplayersupport.form.MainForm;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.geysermc.floodgate.api.FloodgateApi;

/**
 * @author DongShaoNB
 */
public class TpFormCommand implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player player) {
      if (FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId())) {
        if (Bukkit.getOnlinePlayers().size() > 1) {
          MainForm.getInstance().openBedrockTeleportForm(player);
        } else {
          player.sendMessage(BedrockPlayerSupport.getLanguageConfigManager().getConfigData().noOtherOnlinePlayer());
        }
      } else {
        player.sendMessage(BedrockPlayerSupport.getLanguageConfigManager().getConfigData().notBedrockPlayer());
      }
    }
    return false;
  }

}
