package cn.dsnbo.bedrockplayersupport.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.geysermc.cumulus.form.SimpleForm;
import org.geysermc.floodgate.api.FloodgateApi;

/**
 * @author DongShaoNB
 */
public class Forms {

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
    // AuthMe的怪问题 已取消登录GUI功能
//    public static void openLoginForm(Player player) {
//        CustomForm.Builder LoginForm = CustomForm.builder()
//                .title(ChatColor.GOLD + "登录系统")
//                .input(ChatColor.GREEN + "密码", "请输入你的密码")
//                .responseHandler((form, result) -> {
//                    CustomFormResponse response = form.parseResponse(result);
//                    if (response.isCorrect()) {
//                        String password = response.getInput(0);
//                        if (password.isEmpty()) {
//                            if (!AuthMeApi.getInstance().isAuthenticated(player)) {
//                                openLoginForm(player);
//                            }
//                        } else {
//                            player.performCommand("login " + password);
//                        }
//                    } else if (response.isClosed()) {
//                        if (!AuthMeApi.getInstance().isAuthenticated(player)) {
//                            player.kickPlayer(ChatColor.translateAlternateColorCodes('&', BedrockPlayerSupport.Plugin.getConfig().getString("login.kick-message")));
//                        }
//                    }
//                });
//        FloodgateApi.getInstance().sendForm(player.getUniqueId(), LoginForm);
//    }

//    public static void openRegisterForm(Player player) {
//        CustomForm.Builder RegisterForm = CustomForm.builder()
//                .title(ChatColor.GOLD + "登录系统")
//                .input(ChatColor.GREEN + "注册", "请输入你的密码以注册账号")
//                .responseHandler((form, result) -> {
//                    CustomFormResponse response = form.parseResponse(result);
//                    if (response.isCorrect()) {
//                        String password = response.getInput(0);
//                        if (password.isEmpty()) {
//                            if (!AuthMeApi.getInstance().isRegistered(player.getName())) {
//                                openRegisterForm(player);
//                            }
//                        } else {
//                            player.performCommand("register " + password + " " + password);
//                        }
//                    } else if (response.isClosed()) {
//                        if (!AuthMeApi.getInstance().isRegistered(player.getName())) {
//                            player.kickPlayer(ChatColor.translateAlternateColorCodes('&', BedrockPlayerSupport.Plugin.getConfig().getString("login.kick-message")));
//                        }
//                    }
//                });
//        FloodgateApi.getInstance().sendForm(player.getUniqueId(), RegisterForm);
//    }
}
