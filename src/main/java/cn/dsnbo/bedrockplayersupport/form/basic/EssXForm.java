package cn.dsnbo.bedrockplayersupport.form.basic;

import cn.dsnbo.bedrockplayersupport.BedrockPlayerSupport;
import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.geysermc.cumulus.form.SimpleForm;

public class EssXForm {

    public static void sendHomeForm(Player player) {
        User user = new User(player, (Essentials) Bukkit.getPluginManager().getPlugin("Essentials"));
        SimpleForm.Builder form = SimpleForm.builder()
                .title("§6§l我的家")
                .validResultHandler(simpleFormResponse -> {
                    player.chat("/home " + simpleFormResponse.clickedButton().text());
                });
        for (String homeName: user.getHomes()) {
            form.button(homeName);
        }
        BedrockPlayerSupport.getFloodgateApi().sendForm(player.getUniqueId(), form);
    }

}
