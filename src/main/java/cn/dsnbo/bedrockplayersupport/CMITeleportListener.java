package cn.dsnbo.bedrockplayersupport;

import com.Zrips.CMI.events.CMIPlayerTeleportRequestEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.geysermc.cumulus.SimpleForm;
import org.geysermc.cumulus.response.SimpleFormResponse;
import org.geysermc.floodgate.api.FloodgateApi;

import java.util.UUID;

public class CMITeleportListener implements Listener {
    @EventHandler
    public void CMIPlayerTeleportRequestEvent(CMIPlayerTeleportRequestEvent e) {
        String RequestType = e.getAction().name();
        Player postTeleportPlayer = e.getWhoOffers().getPlayer();
        Player TeleportLocationPlayer = e.getWhoAccepts().getPlayer();
        UUID TeleportLocationPlayerUUID = TeleportLocationPlayer.getUniqueId();
        if (FloodgateApi.getInstance().isFloodgatePlayer(TeleportLocationPlayerUUID)) {
            if (RequestType.equals("tpa")) {
                SimpleForm.Builder TeleportForm = SimpleForm.builder()
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
                                    TeleportLocationPlayer.chat("/cmi tpaccept");
                                } else if (ButtonId == 1) {
                                    TeleportLocationPlayer.chat("/cmi tpdeny");
                                }
                            }
                        });
                FloodgateApi.getInstance().sendForm(TeleportLocationPlayerUUID, TeleportForm);
            } else if (RequestType.equals("tpahere")) {
                SimpleForm.Builder TeleportForm = SimpleForm.builder()
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
                                    TeleportLocationPlayer.chat("/cmi tpaccept");
                                } else if (ButtonId == 1) {
                                    TeleportLocationPlayer.chat("/cmi tpdeny");
                                }
                            }
                        });
                FloodgateApi.getInstance().sendForm(TeleportLocationPlayerUUID, TeleportForm);
            }

        }
    }
}
