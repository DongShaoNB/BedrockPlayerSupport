package cn.dsnbo.bedrockplayersupport.listeners.teleport;

import net.ess3.api.events.TPARequestEvent;
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
public class EssXTeleportListener implements Listener {
    @EventHandler
    public void onTeleportWarmupEvent(TPARequestEvent e){
        boolean isTeleportHere = e.isTeleportHere();
        Player postTeleportPlayer = e.getRequester().getPlayer();
        Player teleportLocationPlayer = e.getTarget().getBase();
        UUID teleportLocationPlayerUuid = teleportLocationPlayer.getUniqueId();
        if (FloodgateApi.getInstance().isFloodgatePlayer(teleportLocationPlayerUuid)) {
            SimpleForm.Builder teleportForm;
            if (!isTeleportHere) {
                teleportForm = SimpleForm.builder()
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
                                    teleportLocationPlayer.chat("/tpaccept");
                                } else if (buttonId == 1) {
                                    teleportLocationPlayer.chat("/tpdeny");
                                }
                            }
                        });
            } else {
                teleportForm = SimpleForm.builder()
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
                                    teleportLocationPlayer.chat("/tpaccept");
                                } else if (buttonId == 1) {
                                    teleportLocationPlayer.chat("/tpdeny");
                                }
                            }
                        });
            }
            FloodgateApi.getInstance().sendForm(teleportLocationPlayerUuid, teleportForm);

        }

    }
}
