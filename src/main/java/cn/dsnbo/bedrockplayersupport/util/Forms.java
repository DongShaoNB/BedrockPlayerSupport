package cn.dsnbo.bedrockplayersupport.util;

import fr.xephi.authme.api.v3.AuthMeApi;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.geysermc.cumulus.form.CustomForm;
import org.geysermc.cumulus.form.SimpleForm;
import org.geysermc.floodgate.api.FloodgateApi;

/**
 * @author DongShaoNB
 */
public class Forms {

    public static void openRegisterForm(Player player) {
        CustomForm.Builder customForm = CustomForm.builder()
                .title("§a§l注册菜单")
                .input("§b密码", "请输入注册密码")
                .validResultHandler(((customForm1, customFormResponse) -> {
                    AuthMeApi.getInstance().registerPlayer(player.getName(), customFormResponse.asInput(0));
                }));
        FloodgateApi.getInstance().sendForm(player.getUniqueId(), customForm);
    }

    public static void openBedrockTeleportMenu(Player player) {
        SimpleForm.Builder simpleForm = SimpleForm.builder()
                .title("§6§l传送菜单")
                .content("§a请选择要传送的玩家")
                .validResultHandler((response, simpleFormResponse) -> {
                    player.chat("/tpa " + simpleFormResponse.clickedButton().text());
                });

        for (Player player1 : Bukkit.getOnlinePlayers()) {
            if (player1 != player) {
                simpleForm.button(player1.getName());
            }
        }
        FloodgateApi.getInstance().getPlayer(player.getUniqueId()).sendForm(simpleForm);
    }

    public static void openBedrockTeleportHereMenu(Player player) {
        SimpleForm.Builder simpleForm = SimpleForm.builder()
                .title("§6§l传送菜单")
                .content("§a请选择要传送到你身边的玩家")
                .validResultHandler((response, simpleFormResponse) -> {
                    player.chat("/tpahere " + simpleFormResponse.clickedButton().text());
                });

        for (Player player1 : Bukkit.getOnlinePlayers()) {
            if (player1 != player) {
                simpleForm.button(player1.getName());
            }
        }
        FloodgateApi.getInstance().getPlayer(player.getUniqueId()).sendForm(simpleForm);

    }
}
