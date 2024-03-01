package cn.dsnbo.bedrockplayersupport.form;

import cn.dsnbo.bedrockplayersupport.BasicPlugin;
import cn.dsnbo.bedrockplayersupport.BedrockPlayerSupport;
import cn.dsnbo.bedrockplayersupport.TeleportType;
import cn.dsnbo.bedrockplayersupport.config.Language;
import lombok.Getter;
import net.william278.huskhomes.BukkitHuskHomes;
import net.william278.huskhomes.user.OnlineUser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.geysermc.cumulus.form.CustomForm;
import org.geysermc.cumulus.form.ModalForm;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author DongShaoNB
 */
public class MainForm {

  @Getter
  private static MainForm instance;
  private static Language language;

  public MainForm() {
    loadMainForm();
  }

  public void loadMainForm() {
    instance = this;
    language = BedrockPlayerSupport.getLanguageConfigManager().getConfigData();
  }


  public void openBedrockTeleportForm(Player player) {
    UUID uuid = player.getUniqueId();
    List<String> onlinePlayerNameList = new ArrayList<>();
    if (BedrockPlayerSupport.getBasicPlugin() == BasicPlugin.HuskHomes
        && BedrockPlayerSupport.getMainConfigManager().getConfigData().enableCrossServer()) {
      BukkitHuskHomes bukkitHuskHomes = (BukkitHuskHomes) Bukkit.getPluginManager()
          .getPlugin("Huskhomes");
      for (OnlineUser onlineUser : bukkitHuskHomes.getOnlineUsers()) {
        onlinePlayerNameList.add(onlineUser.getUsername());
      }
    } else {
      for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
        if (onlinePlayer != player) {
          onlinePlayerNameList.add(onlinePlayer.getName());
        }
      }
    }
    CustomForm.Builder form = CustomForm.builder()
        .title(language.teleportFormTitle())
        .dropdown(language.teleportFormChooseTypeText(), List.of("TPA", "TPAHERE"))
        .dropdown(language.teleportFormChoosePlayerText(), onlinePlayerNameList)
        .validResultHandler((customForm, customFormResponse) -> {
          if (customFormResponse.asDropdown(0) == 0) {
            player.chat("/tpa " + onlinePlayerNameList.get(customFormResponse.asDropdown(1)));
          } else if (customFormResponse.asDropdown(0) == 1) {
            player.chat("/tpahere " + onlinePlayerNameList.get(customFormResponse.asDropdown(1)));
          }
        });
    BedrockPlayerSupport.getFloodgateApi().sendForm(uuid, form);
  }

  public void openBedrockTeleportHereForm(TeleportType tpType, Player requestor, Player receiver) {
    ModalForm.Builder form = null;
    String requestorName = requestor.getName();
    UUID receiverUuid = receiver.getUniqueId();
    if (tpType == TeleportType.Tpa) {
      form = ModalForm.builder()
          .title(language.receivedTpaFormTitle())
          .content(
              language.receivedTpaFormText()
                  .replace("%requesterName%", requestorName)
          )
          .button1(language.receivedTpFormAcceptButton())
          .button2(language.receivedTpFormDenyButton())
          .validResultHandler((modalForm, modalFormResponse) -> {
            switch (modalFormResponse.clickedButtonId()) {
              case 0 -> receiver.chat("/tpaccept");
              case 1 -> receiver.chat("/tpdeny");
              default -> {
              }
            }
          });
    } else if (tpType == TeleportType.TpaHere) {
      form = ModalForm.builder()
          .title(language.receivedTpaHereFormTitle())
          .content(
              language.receivedTpaHereFormText()
                  .replace("%requesterName%", requestorName)
          )
          .button1(language.receivedTpFormAcceptButton())
          .button2(language.receivedTpFormDenyButton())
          .validResultHandler((modalForm, modalFormResponse) -> {
            switch (modalFormResponse.clickedButtonId()) {
              case 0 -> receiver.chat("/tpaccept");
              case 1 -> receiver.chat("/tpdeny");
              default -> {
              }
            }
          });
    }
    BedrockPlayerSupport.getFloodgateApi().sendForm(receiverUuid, form);
  }

  public void openBedrockMsgForm(Player player) {
    List<String> onlinePlayerName = new ArrayList<>();
    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
      if (onlinePlayer != player) {
        onlinePlayerName.add(onlinePlayer.getName());
      }
    }
    CustomForm.Builder form = CustomForm.builder()
        .title(language.msgFormTitle())
        .dropdown(language.msgFormChoosePlayerText(), onlinePlayerName)
        .input(language.msgFormInputMessageText())
        .validResultHandler(((response, customFormResponse) -> {
          player.chat("/msg " + onlinePlayerName.get(customFormResponse.asDropdown(0)) + " "
              + customFormResponse.asInput(1));
        }));
    BedrockPlayerSupport.getFloodgateApi().sendForm(player.getUniqueId(), form);
  }

  public void openBedrockBackForm(Player player) {
    ModalForm.Builder form = ModalForm.builder()
        .title(language.backFormTitle())
        .content(language.backFormText())
        .button1(
            BedrockPlayerSupport.getLanguageConfigManager().getConfigData().formTrueButtonText())
        .button2(
            BedrockPlayerSupport.getLanguageConfigManager().getConfigData().formFalseButtonText())
        .validResultHandler(((modalForm, modalFormResponse) -> {
          switch (modalFormResponse.clickedButtonId()) {
            case 0 -> player.chat("/back");
            default -> {
            }
          }
        }));
    BedrockPlayerSupport.getFloodgateApi().sendForm(player.getUniqueId(), form);
  }

}
