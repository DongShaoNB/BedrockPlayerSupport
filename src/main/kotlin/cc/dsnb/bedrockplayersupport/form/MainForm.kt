package cc.dsnb.bedrockplayersupport.form

import cc.dsnb.bedrockplayersupport.BasicPlugin.HUSKHOMES
import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import cc.dsnb.bedrockplayersupport.TeleportType
import cc.dsnb.bedrockplayersupport.config.LangConfig
import cc.dsnb.bedrockplayersupport.config.MainConfig
import cc.dsnb.bedrockplayersupport.form.basic.CMIForm
import cc.dsnb.bedrockplayersupport.form.basic.EssXForm
import cc.dsnb.bedrockplayersupport.form.basic.SunLightForm
import cc.dsnb.bedrockplayersupport.util.StringUtil
import net.william278.huskhomes.BukkitHuskHomes
import net.william278.huskhomes.api.HuskHomesAPI
import net.william278.huskhomes.user.OnlineUser
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.geysermc.cumulus.form.CustomForm
import org.geysermc.cumulus.form.ModalForm
import org.geysermc.cumulus.form.SimpleForm

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
            onlinePlayerNameList.addAll((bukkitHuskHomes.onlineUsers as Collection<OnlineUser>).filter {
                it != HuskHomesAPI.getInstance().adaptUser(player)
            }.map { it.name })
        } else {
            onlinePlayerNameList.addAll(Bukkit.getOnlinePlayers().filter { it != player }.map { it.name })
        }
        val form =
            CustomForm.builder().title(
                StringUtil.formatTextToString(player, langConfig.teleportFormTitle())
            ).dropdown(
                StringUtil.formatTextToString(player, langConfig.teleportFormChooseTypeText()),
                listOf("Tpa", "TpaHere")
            ).dropdown(
                StringUtil.formatTextToString(player, langConfig.teleportFormChoosePlayerText()),
                onlinePlayerNameList
            ).validResultHandler { customFormResponse ->
                val playerName = onlinePlayerNameList[customFormResponse.asDropdown(1)]
                when (customFormResponse.asDropdown(0)) {
                    0 -> player.chat("${BedrockPlayerSupport.basicPlugin.tpaCommand} $playerName")
                    1 -> player.chat("${BedrockPlayerSupport.basicPlugin.tpaHereCommand} $playerName")
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
                .validResultHandler { modalFormResponse ->
                    when (modalFormResponse.clickedButtonId()) {
                        0 -> receiver.chat(BedrockPlayerSupport.basicPlugin.tpaAcceptCommand)
                        1 -> receiver.chat(BedrockPlayerSupport.basicPlugin.tpaRejectCommand)
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
                .validResultHandler { modalFormResponse ->
                    when (modalFormResponse.clickedButtonId()) {
                        0 -> receiver.chat(BedrockPlayerSupport.basicPlugin.tpaAcceptCommand)
                        1 -> receiver.chat(BedrockPlayerSupport.basicPlugin.tpaRejectCommand)
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
            .validResultHandler { customFormResponse ->
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
                    1 -> BedrockPlayerSupport.basicForm.sendDelHomeForm(player)
                    2 -> BedrockPlayerSupport.basicForm.sendGoHomeForm(player)
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
            .validResultHandler { modalFormResponse ->
                if (modalFormResponse.clickedButtonId() == 0) {
                    player.chat(mainConfig.backDeathLocCommand())
                }
            }
        BedrockPlayerSupport.floodgateApi.sendForm(uuid, form)
    }

    fun openBedrockWarpForm(player: Player) {
        BedrockPlayerSupport.basicForm.sendWarpForm(player)
    }

    fun openBedrockKitForm(player: Player) {
        when (val basicForm = BedrockPlayerSupport.basicForm) {
            is CMIForm -> basicForm.sendKitForm(player)
            is EssXForm -> basicForm.sendKitForm(player)
            is SunLightForm -> basicForm.sendKitForm(player)
        }
    }

    fun openBedrockMoneyForm(player: Player) {
        val uuid = player.uniqueId
        val onlinePlayerName = ArrayList<String>()
        for (onlinePlayer in Bukkit.getOnlinePlayers()) {
            if (onlinePlayer !== player) {
                onlinePlayerName.add(onlinePlayer.name)
            }
        }
        val form = CustomForm.builder()
            .title(StringUtil.formatTextToString(player, langConfig.moneyFormTitle()))
            .dropdown(StringUtil.formatTextToString(player, langConfig.moneyFormChoosePlayerText()), onlinePlayerName)
            .input(StringUtil.formatTextToString(player, langConfig.moneyFormInputAmountText()))
            .validResultHandler { customFormResponse ->
                player.chat(
                    mainConfig.payMoneyCommand()
                        .replace("%playerName%", onlinePlayerName[customFormResponse.asDropdown(0)])
                        .replace("%amount%", customFormResponse.asInput(1) ?: "0")
                )
            }
        BedrockPlayerSupport.floodgateApi.sendForm(uuid, form)
    }

    fun openBedrockPointsForm(player: Player) {
        val uuid = player.uniqueId
        val onlinePlayerName = ArrayList<String>()
        for (onlinePlayer in Bukkit.getOnlinePlayers()) {
            if (onlinePlayer !== player) {
                onlinePlayerName.add(onlinePlayer.name)
            }
        }
        val form = CustomForm.builder()
            .title(StringUtil.formatTextToString(player, langConfig.pointsFormTitle()))
            .dropdown(StringUtil.formatTextToString(player, langConfig.pointsFormChoosePlayerText()), onlinePlayerName)
            .input(StringUtil.formatTextToString(player, langConfig.pointsFormInputAmountText()))
            .validResultHandler { customFormResponse ->
                player.chat(
                    mainConfig.payPointsCommand()
                        .replace("%playerName%", onlinePlayerName[customFormResponse.asDropdown(0)])
                        .replace("%amount%", customFormResponse.asInput(1) ?: "0")
                )
            }
        BedrockPlayerSupport.floodgateApi.sendForm(uuid, form)
    }

}