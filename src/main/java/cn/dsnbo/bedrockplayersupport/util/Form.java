package cn.dsnbo.bedrockplayersupport.util;

import cn.dsnbo.bedrockplayersupport.BedrockPlayerSupport;
import cn.dsnbo.bedrockplayersupport.TeleportType;
import fr.xephi.authme.api.v3.AuthMeApi;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.geysermc.cumulus.form.CustomForm;
import org.geysermc.cumulus.form.SimpleForm;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author DongShaoNB
 */
public class Form {

    public static void openRegisterForm(Player player) {
        CustomForm.Builder customForm = CustomForm.builder()
                .title("§6§l注册菜单")
                .input("§b密码", "请输入注册密码")
                .validResultHandler(((customForm1, customFormResponse) -> {
                    AuthMeApi.getInstance().registerPlayer(player.getName(), customFormResponse.asInput(0));
                }));
        BedrockPlayerSupport.getFloodgateApi().sendForm(player.getUniqueId(), customForm);
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
        BedrockPlayerSupport.getFloodgateApi().sendForm(player.getUniqueId(), simpleForm);
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
        BedrockPlayerSupport.getFloodgateApi().sendForm(player.getUniqueId(), simpleForm);

    }

    public static void openBedrockTeleportRequestMenu(TeleportType tpType, Player requestor, Player receiver) {
        SimpleForm.Builder form = null;
        String requestorName = requestor.getName();
        UUID receiverUuid = receiver.getUniqueId();
        if (tpType == TeleportType.Tpa) {
            form = SimpleForm.builder()
                    .title("§6§l玩家请求传送到你的位置 §f(TPA)")
                    .content("玩家 " + requestorName + " 请求传送到你的位置\n\n\n\n")
                    .button("§a同意")
                    .button("§c拒绝")
                    .button("§e忽略")
                    .validResultHandler((simpleForm, result) -> {
                        switch (result.clickedButtonId()) {
                            case 0 -> receiver.chat("/tpaccept");
                            case 1 -> receiver.chat("/tpdeny");
                        }
                    });
        } else if (tpType == TeleportType.TpaHere) {
            form = SimpleForm.builder()
                    .title("§6§l玩家请求你传送到他的位置 §f(TPAHERE)")
                    .content("玩家 " + requestorName + " 请求你传送到他的位置\n\n\n\n")
                    .button("§a同意")
                    .button("§c拒绝")
                    .button("§e忽略")
                    .validResultHandler((simpleForm, result) -> {
                        switch (result.clickedButtonId()) {
                            case 0 -> receiver.chat("/tpaccept");
                            case 1 -> receiver.chat("/tpdeny");
                        }
                    });
        }
        BedrockPlayerSupport.getFloodgateApi().sendForm(receiverUuid, form);
    }

    public static void openBedrockMsgMenu(Player player) {
        List<String> onlinePlayerName = new ArrayList<>();
        for (Player onlinePlayer: Bukkit.getOnlinePlayers()) {
            if (onlinePlayer != player) {
                onlinePlayerName.add(onlinePlayer.getName());
            }
        }
        CustomForm.Builder customForm = CustomForm.builder()
                .title("§6§l信息菜单")
                .dropdown("§a请选择接收信息的玩家", onlinePlayerName)
                .input("§a请填写要发送的信息")
                .validResultHandler(((response, customFormResponse) -> {
                    player.chat("/msg " + onlinePlayerName.get(customFormResponse.asDropdown(0)) + " " + customFormResponse.asInput(1));
                }));
        BedrockPlayerSupport.getFloodgateApi().sendForm(player.getUniqueId(), customForm);
    }
}
