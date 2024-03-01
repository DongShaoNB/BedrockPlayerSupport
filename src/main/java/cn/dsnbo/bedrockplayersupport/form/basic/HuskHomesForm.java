package cn.dsnbo.bedrockplayersupport.form.basic;

import cn.dsnbo.bedrockplayersupport.BedrockPlayerSupport;
import net.william278.huskhomes.api.HuskHomesAPI;
import net.william278.huskhomes.position.Home;
import net.william278.huskhomes.user.OnlineUser;
import org.bukkit.entity.Player;
import org.geysermc.cumulus.form.SimpleForm;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author DongShaoNB
 */
public class HuskHomesForm {

    public static void sendHomeForm(Player player) {
        OnlineUser onlineUser = HuskHomesAPI.getInstance().adaptUser(player);
        CompletableFuture<List<Home>> huskHomes = HuskHomesAPI.getInstance().getUserHomes(onlineUser);
        SimpleForm.Builder form = SimpleForm.builder()
                .title(BedrockPlayerSupport.getLanguageConfigManager().getConfigData().homeFormTitle())
                .validResultHandler(simpleFormResponse -> {
                    player.chat("/home " + simpleFormResponse.clickedButton().text());
                });
        huskHomes.thenAccept(homeList -> {
            for (Home home: homeList) {
                form.button(home.getName());
            }
            BedrockPlayerSupport.getFloodgateApi().sendForm(player.getUniqueId(), form);
        });
    }

}
