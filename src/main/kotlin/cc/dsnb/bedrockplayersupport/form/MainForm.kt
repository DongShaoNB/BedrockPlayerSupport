package cc.dsnb.bedrockplayersupport.form

import cc.dsnb.bedrockplayersupport.BasicPlugin
import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import cc.dsnb.bedrockplayersupport.TeleportType
import cc.dsnb.bedrockplayersupport.config.LangConfig
import cc.dsnb.bedrockplayersupport.config.MainConfig
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.william278.huskhomes.BukkitHuskHomes
import net.william278.huskhomes.api.HuskHomesAPI
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.geysermc.cumulus.form.CustomForm
import org.geysermc.cumulus.form.ModalForm
import java.util.*

/**
 * @author DongShaoNB
 */
class MainForm {

    private lateinit var mainConfig: MainConfig
    private lateinit var langConfig: LangConfig
    private lateinit var miniMessage: MiniMessage

    fun load() {
        mainConfig = BedrockPlayerSupport.mainConfigManager.getConfigData()
        langConfig = BedrockPlayerSupport.langConfigManager.getConfigData()
        miniMessage = BedrockPlayerSupport.miniMessage
    }

    fun openBedrockTeleportForm(player: Player) {
        val uuid = player.uniqueId
        val onlinePlayerNameList = ArrayList<String>()
        if (BedrockPlayerSupport.basicPlugin === BasicPlugin.HuskHomes && mainConfig.enableCrossServer()) {
            val bukkitHuskHomes = Bukkit.getPluginManager().getPlugin("HuskHomes") as BukkitHuskHomes
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
        val form = CustomForm.builder().title(
            LegacyComponentSerializer.legacySection().serialize(
                miniMessage.deserialize(
                    langConfig.teleportFormTitle()
                )
            )
        ).dropdown(
            LegacyComponentSerializer.legacySection().serialize(
                miniMessage.deserialize(
                    langConfig.teleportFormChooseTypeText()
                )
            ), listOf("Tpa", "TpaHere")
        ).dropdown(
            LegacyComponentSerializer.legacySection().serialize(
                miniMessage.deserialize(
                    langConfig.teleportFormChoosePlayerText()
                )
            ), onlinePlayerNameList
        ).validResultHandler { _, customFormResponse ->
            when (customFormResponse.asDropdown(0)) {
                0 -> player.chat("/tpa " + onlinePlayerNameList[customFormResponse.asDropdown(1)])
                1 -> player.chat("/tpahere " + onlinePlayerNameList[customFormResponse.asDropdown(1)])
                else -> {}
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
                .title(
                    LegacyComponentSerializer.legacySection().serialize(
                        miniMessage.deserialize(
                            langConfig.receivedTpaFormTitle()
                        )
                    )
                )
                .content(
                    LegacyComponentSerializer.legacySection().serialize(
                        miniMessage.deserialize(
                            langConfig.receivedTpaFormText()
                                .replace("%requesterName%", requesterName)
                        )
                    )
                )
                .button1(
                    LegacyComponentSerializer.legacySection().serialize(
                        miniMessage.deserialize(
                            langConfig.receivedTpFormAcceptButton()
                        )
                    )
                )
                .button2(
                    LegacyComponentSerializer.legacySection().serialize(
                        miniMessage.deserialize(
                            langConfig.receivedTpFormDenyButton()
                        )
                    )
                )
                .validResultHandler { _, modalFormResponse ->
                    when (modalFormResponse.clickedButtonId()) {
                        0 -> receiver.chat("/tpaccept")
                        1 -> receiver.chat("/tpdeny")
                        else -> {}
                    }
                }
        } else if (tpType === TeleportType.TpaHere) {
            form = ModalForm.builder()
                .title(
                    LegacyComponentSerializer.legacySection().serialize(
                        miniMessage.deserialize(
                            langConfig.receivedTpaHereFormTitle()
                        )
                    )
                )
                .content(
                    LegacyComponentSerializer.legacySection().serialize(
                        miniMessage.deserialize(
                            langConfig.receivedTpaHereFormText()
                                .replace("%requesterName%", requesterName)
                        )
                    )
                )
                .button1(
                    LegacyComponentSerializer.legacySection().serialize(
                        miniMessage.deserialize(
                            langConfig.receivedTpFormAcceptButton()
                        )
                    )
                )
                .button2(
                    LegacyComponentSerializer.legacySection().serialize(
                        miniMessage.deserialize(
                            langConfig.receivedTpFormDenyButton()
                        )
                    )
                )
                .validResultHandler { _, modalFormResponse ->
                    when (modalFormResponse.clickedButtonId()) {
                        0 -> receiver.chat("/tpaccept")
                        1 -> receiver.chat("/tpdeny")
                        else -> {}
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
            .title(
                LegacyComponentSerializer.legacySection().serialize(
                    miniMessage.deserialize(
                        langConfig.msgFormTitle()
                    )
                )
            )
            .dropdown(
                LegacyComponentSerializer.legacySection().serialize(
                    miniMessage.deserialize(
                        langConfig.msgFormChoosePlayerText()
                    )
                ),
                onlinePlayerName
            )
            .input(
                LegacyComponentSerializer.legacySection().serialize(
                    miniMessage.deserialize(
                        langConfig.msgFormInputMessageText()
                    )
                )
            )
            .validResultHandler { _, customFormResponse ->
                player.chat(
                    "/msg " + onlinePlayerName[customFormResponse.asDropdown(0)] + " "
                            + customFormResponse.asInput(1)
                )
            }
        BedrockPlayerSupport.floodgateApi.sendForm(uuid, form)
    }

    fun openBedrockBackDeathLocForm(player: Player) {
        val uuid = player.uniqueId
        val form = ModalForm.builder()
            .title(
                LegacyComponentSerializer.legacySection().serialize(
                    miniMessage.deserialize(
                        langConfig.backFormTitle()
                    )
                )
            )
            .content(
                LegacyComponentSerializer.legacySection().serialize(
                    miniMessage.deserialize(
                        langConfig.backFormText()
                    )
                )
            )
            .button1(
                LegacyComponentSerializer.legacySection().serialize(
                    miniMessage.deserialize(
                        langConfig.backFormYesButton()
                    )
                )
            )
            .button2(
                LegacyComponentSerializer.legacySection().serialize(
                    miniMessage.deserialize(
                        langConfig.backFormNoButton()
                    )
                )
            )
            .validResultHandler { _, modalFormResponse ->
                if (modalFormResponse.clickedButtonId() == 0) {
                    player.chat(mainConfig.backDeathLocCommand())
                }
            }
        BedrockPlayerSupport.floodgateApi.sendForm(uuid, form)
    }

}