package cn.dsnbo.bedrockplayersupport.listener.teleport;

import net.william278.huskhomes.event.SendTeleportRequestEvent;
import net.william278.huskhomes.teleport.TeleportRequest;
import net.william278.huskhomes.user.OnlineUser;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.geysermc.cumulus.SimpleForm;
import org.geysermc.cumulus.response.SimpleFormResponse;
import org.geysermc.floodgate.api.FloodgateApi;

/**
 * @author DongShaoNB
 */
public class HuskHomesTeleportListener implements Listener {

    @EventHandler
    public void onSendTeleportRequestEvent(SendTeleportRequestEvent event) {
        TeleportRequest.Type requestType = event.getRequest().getType();
        OnlineUser sender = event.getSender();
        Player recipient = Bukkit.getPlayer(event.getRequest().getRecipientName());
        SimpleForm.Builder teleportForm = null;
        if (requestType == TeleportRequest.Type.TPA) {
            teleportForm = SimpleForm.builder()
                    .title(ChatColor.DARK_PURPLE + "有玩家请求传送到你的位置")
                    .content(ChatColor.WHITE + "玩家 " + sender.getUsername() + " 请求传送到你所在的位置\n\n\n\n\n\n")
                    .button(ChatColor.GREEN + "同意")
                    .button(ChatColor.RED + "拒绝")
                    .button(ChatColor.YELLOW + "忽略")
                    .responseHandler((form, result) -> {
                        SimpleFormResponse response = form.parseResponse(result);
                        if (response.isCorrect()) {
                            int buttonId = response.getClickedButtonId();
                            if (buttonId == 0) {
                                recipient.chat("/tpaccept");
                            } else if (buttonId == 1) {
                                recipient.chat("/tpdecline");
                            }
                        }
                    });
        } else if (requestType == TeleportRequest.Type.TPA_HERE) {
            teleportForm = SimpleForm.builder()
                    .title(ChatColor.DARK_PURPLE + "有玩家请求你传送到他的位置")
                    .content(ChatColor.WHITE + "玩家 " + sender.getUsername() + " 请求你传送到他所在的位置\n\n\n\n\n\n")
                    .button(ChatColor.GREEN + "同意")
                    .button(ChatColor.RED + "拒绝")
                    .button(ChatColor.YELLOW + "忽略")
                    .responseHandler((form, result) -> {
                        SimpleFormResponse response = form.parseResponse(result);
                        if (response.isCorrect()) {
                            int buttonId = response.getClickedButtonId();
                            if (buttonId == 0) {
                                recipient.chat("/tpaccept");
                            } else if (buttonId == 1) {
                                recipient.chat("/tpdecline");
                            }
                        }
                    });
        }
        FloodgateApi.getInstance().sendForm(recipient.getUniqueId(), teleportForm);
    }

}
