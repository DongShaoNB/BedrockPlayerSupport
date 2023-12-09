package cn.dsnbo.bedrockplayersupport.util;

import cn.dsnbo.bedrockplayersupport.BedrockPlayerSupport;
import cn.dsnbo.bedrockplayersupport.TeleportType;
import fr.xephi.authme.api.v3.AuthMeApi;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.geysermc.cumulus.form.CustomForm;
import org.geysermc.cumulus.form.ModalForm;
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
        UUID uuid = player.getUniqueId();
        List<String> onlinePlayerNameList = new ArrayList<>();
        for (Player onlinePlayer: Bukkit.getOnlinePlayers()) {
            if (onlinePlayer != player) {
                onlinePlayerNameList.add(onlinePlayer.getName());
            }
        }
        CustomForm.Builder form = CustomForm.builder()
                .title("§6§l传送菜单")
                .dropdown("请选择传送类型", List.of("TPA", "TPAHERE"))
                .dropdown("请选择要传送的玩家", onlinePlayerNameList)
                .validResultHandler((customForm, customFormResponse) -> {
                    if (customFormResponse.asDropdown(0) == 0) {
                        player.chat("/tpa " + onlinePlayerNameList.get(customFormResponse.asDropdown(1)));
                    } else if (customFormResponse.asDropdown(0) == 1) {
                        player.chat("/tpahere " + onlinePlayerNameList.get(customFormResponse.asDropdown(1)));
                    }
                });
        BedrockPlayerSupport.getFloodgateApi().sendForm(uuid, form);
    }

    public static void openBedrockTeleportRequestMenu(TeleportType tpType, Player requestor, Player receiver) {
        ModalForm.Builder form = null;
        String requestorName = requestor.getName();
        UUID receiverUuid = receiver.getUniqueId();
        if (tpType == TeleportType.Tpa) {
            form = ModalForm.builder()
                    .title("§6§l玩家请求传送到你的位置 §f(TPA)")
                    .content("玩家 " + requestorName + " 请求传送到你的位置")
                    .button1("§a同意")
                    .button2("§c拒绝")
                    .validResultHandler((simpleForm, result) -> {
                        switch (result.clickedButtonId()) {
                            case 0 -> receiver.chat("/tpaccept");
                            case 1 -> receiver.chat("/tpdeny");
                        }
                    });
        } else if (tpType == TeleportType.TpaHere) {
            form = ModalForm.builder()
                    .title("§6§l玩家请求你传送到他的位置 §f(TPAHERE)")
                    .content("玩家 " + requestorName + " 请求你传送到他的位置")
                    .button1("§a同意")
                    .button2("§c拒绝")
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
