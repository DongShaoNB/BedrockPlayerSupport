package cn.dsnbo.bedrockplayersupport.listeners;

import com.Zrips.CMI.events.CMIPlayerTeleportRequestEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.geysermc.cumulus.SimpleForm;
import org.geysermc.cumulus.response.SimpleFormResponse;
import org.geysermc.floodgate.api.FloodgateApi;

import java.util.UUID;

/**
 * @author DongShaoNB
 */
public class CMITeleportListener implements Listener {
    @EventHandler
    public void onCmiPlayerTeleportRequestEvent(CMIPlayerTeleportRequestEvent e) {
        String requestType = e.getAction().name();
        Player postTeleportPlayer = e.getWhoOffers().getPlayer();
        Player teleportLocationPlayer = e.getWhoAccepts().getPlayer();
        UUID teleportLocationPlayerUuid = teleportLocationPlayer.getUniqueId();
        if (FloodgateApi.getInstance().isFloodgatePlayer(teleportLocationPlayerUuid)) {
            if ("tpa".equals(requestType)) {
                SimpleForm.Builder teleportForm = SimpleForm.builder()
                        .title(ChatColor.DARK_PURPLE + "有玩家请求传送到你的位置")
                        .content(ChatColor.WHITE + "玩家 " + postTeleportPlayer.getName() + " 请求传送到你所在的位置\n\n\n\n\n\n")
                        .button(ChatColor.GREEN + "同意")
                        .button(ChatColor.RED + "拒绝")
                        .button(ChatColor.YELLOW + "忽略")
                        .responseHandler((form, result) -> {
                            SimpleFormResponse response = form.parseResponse(result);
                            if (response.isCorrect()) {
                                int buttonId = response.getClickedButtonId();
                                if (buttonId == 0) {
                                    teleportLocationPlayer.performCommand("cmi tpaccept");
                                } else if (buttonId == 1) {
                                    teleportLocationPlayer.performCommand("cmi tpdeny");
                                }
                            }
                        });
                FloodgateApi.getInstance().sendForm(teleportLocationPlayerUuid, teleportForm);
            } else if ("tpahere".equals(requestType)) {
                SimpleForm.Builder teleportForm = SimpleForm.builder()
                        .title(ChatColor.DARK_PURPLE + "有玩家请求你传送到他的位置")
                        .content(ChatColor.WHITE + "玩家 " + postTeleportPlayer.getName() + " 请求你传送到他所在的位置\n\n\n\n\n\n")
                        .button(ChatColor.GREEN + "同意")
                        .button(ChatColor.RED + "拒绝")
                        .button(ChatColor.YELLOW + "忽略")
                        .responseHandler((form, result) -> {
                            SimpleFormResponse response = form.parseResponse(result);
                            if (response.isCorrect()) {
                                int buttonId = response.getClickedButtonId();
                                if (buttonId == 0) {
                                    teleportLocationPlayer.performCommand("cmi tpaccept");
                                } else if (buttonId == 1) {
                                    teleportLocationPlayer.performCommand("cmi tpdeny");
                                }
                            }
                        });
                FloodgateApi.getInstance().sendForm(teleportLocationPlayerUuid, teleportForm);
            }

        }
    }
}
