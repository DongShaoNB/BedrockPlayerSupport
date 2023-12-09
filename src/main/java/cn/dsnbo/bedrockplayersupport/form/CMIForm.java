package cn.dsnbo.bedrockplayersupport.form;

import cn.dsnbo.bedrockplayersupport.BedrockPlayerSupport;
import com.Zrips.CMI.CMI;
import com.Zrips.CMI.Containers.CMIUser;
import com.Zrips.CMI.Modules.Homes.CmiHome;
import org.bukkit.entity.Player;
import org.geysermc.cumulus.form.SimpleForm;

import java.util.LinkedHashMap;

public class CMIForm {

    public static void sendHomeForm(Player player) {
        CMIUser cmiUser = CMI.getInstance().getPlayerManager().getUser(player);
        LinkedHashMap<String, CmiHome> playerHomesList = cmiUser.getHomes();
        SimpleForm.Builder form = SimpleForm.builder()
                .title("§6§l我的家")
                .validResultHandler(simpleFormResponse -> {
                    player.chat("/home " + simpleFormResponse.clickedButton().text());
                });
        for (String homeName : playerHomesList.keySet()) {
            form.button(homeName);
        }
        BedrockPlayerSupport.getFloodgateApi().sendForm(player.getUniqueId(), form);
    }

}
