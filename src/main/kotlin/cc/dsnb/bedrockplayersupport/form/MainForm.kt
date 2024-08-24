package cc.dsnb.bedrockplayersupport.form

import cc.dsnb.bedrockplayersupport.BasicPlugin.*
import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import cc.dsnb.bedrockplayersupport.TeleportType
import cc.dsnb.bedrockplayersupport.config.LangConfig
import cc.dsnb.bedrockplayersupport.config.MainConfig
import cc.dsnb.bedrockplayersupport.util.StringUtil
import net.william278.huskhomes.BukkitHuskHomes
import net.william278.huskhomes.api.HuskHomesAPI
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.geysermc.cumulus.form.CustomForm
import org.geysermc.cumulus.form.ModalForm
import org.geysermc.cumulus.form.SimpleForm
import java.util.*

/**
 * @author DongShaoNB
 */
class MainForm {

    private var mainConfig: MainConfig = BedrockPlayerSupport.mainConfigManager.getConfigData()
    private var langConfig: LangConfig = BedrockPlayerSupport.langConfigManager.getConfigData()

    fun openBedrockTeleportForm(player: Player) {
        val uuid = player.uniqueId
        val onlinePlayerNameList = ArrayList<String>()
        if (BedrockPlayerSupport.basicPlugin === HUSKHOMES && mainConfig.enableCrossServer()) {
            val bukkitHuskHomes =
                Bukkit.getPluginManager().getPlugin("HuskHomes") as BukkitHuskHomes
            for (onlineUser in bukkitHuskHomes.onlineUsers) {
                if (onlineUser !== HuskHomesAPI.getInstance().adaptUser(player)) {
                    onlinePlayerNameList.add(onlineUser.username)
                }
            }
        } else {
            for (onlinePlayer in Bukkit.getOnlinePlayers()) {
                if (onlinePlayer !== player) {
                    onlinePlayerNameList.add(onlinePlayer.name)
                }
            }
        }
        val form =
            CustomForm.builder().title(StringUtil.formatTextToString(player, langConfig.teleportFormTitle())).dropdown(
                StringUtil.formatTextToString(player, langConfig.teleportFormChooseTypeText()),
                listOf("Tpa", "TpaHere")
            ).dropdown(
                StringUtil.formatTextToString(player, langConfig.teleportFormChoosePlayerText()),
                onlinePlayerNameList
            ).validResultHandler { _, customFormResponse ->
                when (customFormResponse.asDropdown(0)) {
                    0 -> player.chat("/tpa " + onlinePlayerNameList[customFormResponse.asDropdown(1)])
                    1 -> if (BedrockPlayerSupport.basicPlugin == SUNLIGHT) {
                        player.chat("/tphere " + onlinePlayerNameList[customFormResponse.asDropdown(1)])
                    } else {
                        player.chat("/tpahere " + onlinePlayerNameList[customFormResponse.asDropdown(1)])
                    }
                }
            }
        BedrockPlayerSupport.floodgateApi.sendForm(uuid, form)
    }

    fun openBedrockReceiveTeleportForm(tpType: TeleportType, requester: Player, receiver: Player) {
        lateinit var form: ModalForm.Builder
        val requesterName = requester.name
        val receiverUuid = receiver.uniqueId
        if (tpType === TeleportType.Tpa) {
            form = ModalForm.builder()
                .title(StringUtil.formatTextToString(receiver, langConfig.receivedTpaFormTitle()))
                .content(
                    StringUtil.formatTextToString(
                        receiver,
                        langConfig.receivedTpaFormText().replace("%requesterName%", requesterName)
                    )
                )
                .button1(StringUtil.formatTextToString(receiver, langConfig.receivedTpFormAcceptButton()))
                .button2(StringUtil.formatTextToString(receiver, langConfig.receivedTpFormDenyButton()))
                .validResultHandler { _, modalFormResponse ->
                    when (modalFormResponse.clickedButtonId()) {
                        0 -> if (BedrockPlayerSupport.basicPlugin == SUNLIGHT) {
                            receiver.chat("/tpyes")
                        } else {
                            receiver.chat("/tpaccept")
                        }

                        1 -> if (BedrockPlayerSupport.basicPlugin == SUNLIGHT) {
                            receiver.chat("/tpno")
                        } else {
                            receiver.chat("/tpdeny")
                        }
                    }
                }
        } else if (tpType === TeleportType.TpaHere) {
            form = ModalForm.builder()
                .title(StringUtil.formatTextToString(receiver, langConfig.receivedTpaHereFormTitle()))
                .content(
                    StringUtil.formatTextToString(
                        receiver,
                        langConfig.receivedTpaHereFormText().replace("%requesterName%", requesterName)
                    )
                )
                .button1(StringUtil.formatTextToString(receiver, langConfig.receivedTpFormAcceptButton()))
                .button2(StringUtil.formatTextToString(receiver, langConfig.receivedTpFormDenyButton()))
                .validResultHandler { _, modalFormResponse ->
                    when (modalFormResponse.clickedButtonId()) {
                        0 -> if (BedrockPlayerSupport.basicPlugin == SUNLIGHT) {
                            receiver.chat("/tpyes")
                        } else {
                            receiver.chat("/tpaccept")
                        }

                        1 -> if (BedrockPlayerSupport.basicPlugin == SUNLIGHT) {
                            receiver.chat("/tpno")
                        } else {
                            receiver.chat("/tpdeny")
                        }
                    }
                }
        }
        BedrockPlayerSupport.floodgateApi.sendForm(receiverUuid, form)
    }

    fun openBedrockMsgForm(player: Player) {
        val uuid = player.uniqueId
        val onlinePlayerName = ArrayList<String>()
        for (onlinePlayer in Bukkit.getOnlinePlayers()) {
            if (onlinePlayer !== player) {
                onlinePlayerName.add(onlinePlayer.name)
            }
        }
        val form = CustomForm.builder()
            .title(StringUtil.formatTextToString(player, langConfig.msgFormTitle()))
            .dropdown(StringUtil.formatTextToString(player, langConfig.msgFormChoosePlayerText()), onlinePlayerName)
            .input(StringUtil.formatTextToString(player, langConfig.msgFormInputMessageText()))
            .validResultHandler { _, customFormResponse ->
                player.chat(
                    "/msg " + onlinePlayerName[customFormResponse.asDropdown(0)] + " "
                            + customFormResponse.asInput(1)
                )
            }
        BedrockPlayerSupport.floodgateApi.sendForm(uuid, form)
    }

    fun openBedrockHomeForm(player: Player) {
        val uuid = player.uniqueId
        val form = SimpleForm.builder()
            .title(StringUtil.formatTextToString(player, langConfig.homeFormTitle()))
            .button(StringUtil.formatTextToString(player, langConfig.homeFormSetHomeButton()))
            .button(StringUtil.formatTextToString(player, langConfig.homeFormDelHomeButton()))
            .button(StringUtil.formatTextToString(player, langConfig.homeFormGoHomeButton()))
            .validResultHandler { simpleFormResponse ->
                when (simpleFormResponse.clickedButtonId()) {
                    0 -> openBedrockPlayerSetHomeForm(player)
                    1 -> {
                        when (BedrockPlayerSupport.basicPlugin) {
                            CMI -> BedrockPlayerSupport.cmiForm.sendDelHomeForm(player)
                            ESSENTIALS -> BedrockPlayerSupport.essxForm.sendDelHomeForm(player)
                            HUSKHOMES -> BedrockPlayerSupport.huskhomesForm.sendDelHomeForm(player)
                            ADVANCEDTELEPORT -> BedrockPlayerSupport.atForm.sendDelHomeForm(player)
                            SUNLIGHT -> BedrockPlayerSupport.sunlightForm.sendDelHomeForm(player)
                            NONE -> {
                                // Don't need to do anything
                            }
                        }
                    }

                    2 -> {
                        when (BedrockPlayerSupport.basicPlugin) {
                            CMI -> BedrockPlayerSupport.cmiForm.sendGoHomeForm(player)
                            ESSENTIALS -> BedrockPlayerSupport.essxForm.sendGoHomeForm(player)
                            HUSKHOMES -> BedrockPlayerSupport.huskhomesForm.sendGoHomeForm(player)
                            ADVANCEDTELEPORT -> BedrockPlayerSupport.atForm.sendGoHomeForm(player)
                            SUNLIGHT -> BedrockPlayerSupport.sunlightForm.sendGoHomeForm(player)
                            NONE -> {
                                // Don't need to do anything
                            }
                        }
                    }
                }
            }
        BedrockPlayerSupport.floodgateApi.sendForm(uuid, form)
    }

    fun openBedrockPlayerSetHomeForm(player: Player) {
        val form = CustomForm.builder()
            .title(StringUtil.formatTextToString(player, langConfig.setHomeFormTitle()))
            .input(StringUtil.formatTextToString(player, langConfig.setHomeFormText()))
            .validResultHandler { simpleFormResponse ->
                player.chat("/sethome ${simpleFormResponse.asInput(0)}")
            }
        BedrockPlayerSupport.floodgateApi.sendForm(player.uniqueId, form)
    }

    fun openBedrockBackDeathLocForm(player: Player) {
        val uuid = player.uniqueId
        val form = ModalForm.builder()
            .title(StringUtil.formatTextToString(player, langConfig.backFormTitle()))
            .content(StringUtil.formatTextToString(player, langConfig.backFormText()))
            .button1(StringUtil.formatTextToString(player, langConfig.backFormYesButton()))
            .button2(StringUtil.formatTextToString(player, langConfig.backFormNoButton()))
            .validResultHandler { _, modalFormResponse ->
                if (modalFormResponse.clickedButtonId() == 0) {
                    player.chat(mainConfig.backDeathLocCommand())
                }
            }
        BedrockPlayerSupport.floodgateApi.sendForm(uuid, form)
    }

    fun openBedrockWarpForm(player: Player) {
        when (BedrockPlayerSupport.basicPlugin) {
            CMI -> BedrockPlayerSupport.cmiForm.sendWarpForm(player)
            ESSENTIALS -> BedrockPlayerSupport.essxForm.sendWarpForm(player)
            HUSKHOMES -> BedrockPlayerSupport.huskhomesForm.sendWarpForm(player)
            ADVANCEDTELEPORT -> BedrockPlayerSupport.atForm.sendWarpForm(player)
            SUNLIGHT -> BedrockPlayerSupport.sunlightForm.sendWarpForm(player)
            NONE -> {
                // Don't need to do anything
            }
        }
    }

    fun openBedrockKitForm(player: Player) {
        when (BedrockPlayerSupport.basicPlugin) {
            CMI -> BedrockPlayerSupport.cmiForm.sendKitForm(player)
            ESSENTIALS -> BedrockPlayerSupport.essxForm.sendKitForm(player)
            SUNLIGHT -> BedrockPlayerSupport.sunlightForm.sendKitForm(player)
            else -> {
                // Don't need to do anything
            }
        }
    }
}