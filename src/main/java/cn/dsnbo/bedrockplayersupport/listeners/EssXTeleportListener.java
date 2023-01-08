package cn.dsnbo.bedrockplayersupport.listeners;

import net.ess3.api.events.TPARequestEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.geysermc.cumulus.SimpleForm;
import org.geysermc.cumulus.response.SimpleFormResponse;
import org.geysermc.floodgate.api.FloodgateApi;

import java.util.UUID;

public class EssXTeleportListener implements Listener {
    @EventHandler
    public void TeleportWarmupEvent(TPARequestEvent e){
        boolean isTeleportHere = e.isTeleportHere();
        Player postTeleportPlayer = e.getRequester().getPlayer();
        Player TeleportLocationPlayer = e.getTarget().getBase();
        UUID TeleportLocationPlayerUUID = TeleportLocationPlayer.getUniqueId();
        if (FloodgateApi.getInstance().isFloodgatePlayer(TeleportLocationPlayerUUID)) {
            SimpleForm.Builder TeleportForm;
            if (!isTeleportHere) {
                TeleportForm = SimpleForm.builder()
                        .title(ChatColor.DARK_PURPLE + "有玩家请求传送到你的位置")
                        .content(ChatColor.WHITE + "玩家 " + postTeleportPlayer.getName() + " 请求传送到你所在的位置\n\n\n\n\n\n")
                        .button(ChatColor.GREEN + "同意")
                        .button(ChatColor.RED + "拒绝")
                        .button(ChatColor.YELLOW + "忽略")
                        .responseHandler((form, result) -> {
                            SimpleFormResponse response = form.parseResponse(result);
                            if (response.isCorrect()) {
                                int ButtonId = response.getClickedButtonId();
                                if (ButtonId == 0) {
                                    TeleportLocationPlayer.performCommand("tpaccept");
                                } else if (ButtonId == 1) {
                                    TeleportLocationPlayer.performCommand("tpdeny");
                                }
                            }
                        });
            } else {
                TeleportForm = SimpleForm.builder()
                        .title(ChatColor.DARK_PURPLE + "有玩家请求你传送到他的位置")
                        .content(ChatColor.WHITE + "玩家 " + postTeleportPlayer.getName() + " 请求你传送到他所在的位置\n\n\n\n\n\n")
                        .button(ChatColor.GREEN + "同意")
                        .button(ChatColor.RED + "拒绝")
                        .button(ChatColor.YELLOW + "忽略")
                        .responseHandler((form, result) -> {
                            SimpleFormResponse response = form.parseResponse(result);
                            if (response.isCorrect()) {
                                int ButtonId = response.getClickedButtonId();
                                if (ButtonId == 0) {
                                    TeleportLocationPlayer.performCommand("tpaccept");
                                } else if (ButtonId == 1) {
                                    TeleportLocationPlayer.performCommand("tpdeny");
                                }
                            }
                        });
            }
            FloodgateApi.getInstance().sendForm(TeleportLocationPlayerUUID, TeleportForm);

        }

    }
}
