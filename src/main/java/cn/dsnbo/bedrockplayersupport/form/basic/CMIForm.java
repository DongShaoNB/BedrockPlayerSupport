package cn.dsnbo.bedrockplayersupport.form.basic;

import cn.dsnbo.bedrockplayersupport.BedrockPlayerSupport;
import com.Zrips.CMI.CMI;
import com.Zrips.CMI.Containers.CMIUser;
import com.Zrips.CMI.Modules.Homes.CmiHome;
import com.Zrips.CMI.Modules.tp.Teleportations.TeleportType;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.geysermc.cumulus.form.SimpleForm;

import java.util.LinkedHashMap;

/**
 * @author DongShaoNB
 */
public class CMIForm {

  public static void sendHomeForm(Player player) {
    CMIUser cmiUser = CMI.getInstance().getPlayerManager().getUser(player);
    LinkedHashMap<String, CmiHome> playerHomesList = cmiUser.getHomes();
    SimpleForm.Builder form = SimpleForm.builder()
        .title(ChatColor.translateAlternateColorCodes(
            '&', BedrockPlayerSupport.getLanguageConfigManager().getConfigData().homeFormTitle()))
        .validResultHandler(simpleFormResponse -> {
          player.chat("/home " + simpleFormResponse.clickedButton().text());
        });
    for (String homeName : playerHomesList.keySet()) {
      form.button(homeName);
    }
    BedrockPlayerSupport.getFloodgateApi().sendForm(player.getUniqueId(), form);
  }

}
