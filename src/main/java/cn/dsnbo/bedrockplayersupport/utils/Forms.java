package cn.dsnbo.bedrockplayersupport.utils;

import cn.dsnbo.bedrockplayersupport.BedrockPlayerSupport;
import fr.xephi.authme.api.v3.AuthMeApi;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.geysermc.cumulus.CustomForm;
import org.geysermc.cumulus.response.CustomFormResponse;
import org.geysermc.floodgate.api.FloodgateApi;

public class Forms {
    public static void openLoginForm(Player player) {
        CustomForm.Builder LoginForm = CustomForm.builder()
                .title(ChatColor.GOLD + "登录系统")
                .input(ChatColor.GREEN + "密码", "请输入你的密码")
                .responseHandler((form, result) -> {
                    CustomFormResponse response = form.parseResponse(result);
                    if (response.isCorrect()) {
                        String password = response.getInput(0);
                        if (password.isEmpty()) {
                            if (!AuthMeApi.getInstance().isAuthenticated(player)) {
                                openLoginForm(player);
                            }
                        } else {
                            player.performCommand("login " + password);
                        }
                    } else if (response.isClosed()) {
                        if (!AuthMeApi.getInstance().isAuthenticated(player)) {
                            player.kickPlayer(ChatColor.translateAlternateColorCodes('&', BedrockPlayerSupport.Plugin.getConfig().getString("login.kick-message")));
                        }
                    }
                });
        FloodgateApi.getInstance().sendForm(player.getUniqueId(), LoginForm);
    }

    public static void openRegisterForm(Player player) {
        CustomForm.Builder RegisterForm = CustomForm.builder()
                .title(ChatColor.GOLD + "登录系统")
                .input(ChatColor.GREEN + "注册", "请输入你的密码以注册账号")
                .responseHandler((form, result) -> {
                    CustomFormResponse response = form.parseResponse(result);
                    if (response.isCorrect()) {
                        String password = response.getInput(0);
                        if (password.isEmpty()) {
                            if (!AuthMeApi.getInstance().isRegistered(player.getName())) {
                                openRegisterForm(player);
                            }
                        } else {
                            player.performCommand("register " + password + " " + password);
                        }
                    } else if (response.isClosed()) {
                        if (!AuthMeApi.getInstance().isRegistered(player.getName())) {
                            player.kickPlayer(ChatColor.translateAlternateColorCodes('&', BedrockPlayerSupport.Plugin.getConfig().getString("login.kick-message")));
                        }
                    }
                });
        FloodgateApi.getInstance().sendForm(player.getUniqueId(), RegisterForm);
    }
}
