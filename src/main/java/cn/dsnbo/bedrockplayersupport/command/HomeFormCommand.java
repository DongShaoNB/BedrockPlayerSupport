package cn.dsnbo.bedrockplayersupport.command;

import cn.dsnbo.bedrockplayersupport.BedrockPlayerSupport;
import cn.dsnbo.bedrockplayersupport.form.basic.CMIForm;
import cn.dsnbo.bedrockplayersupport.form.basic.EssXForm;
import cn.dsnbo.bedrockplayersupport.form.basic.HuskHomesForm;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.geysermc.floodgate.api.FloodgateApi;

/**
 * @author DongShaoNB
 */
public class HomeFormCommand implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player player) {
      if (FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId())) {
        switch (BedrockPlayerSupport.getBasicPlugin()) {
          case CMI -> CMIForm.sendHomeForm(player);
          case EssentialsX -> EssXForm.sendHomeForm(player);
          case HuskHomes -> HuskHomesForm.sendHomeForm(player);
          default -> {}
        }
      } else {
        player.sendMessage(BedrockPlayerSupport.getLanguageConfigManager().getConfigData().notBedrockPlayer());
      }
    }
    return false;
  }
}
