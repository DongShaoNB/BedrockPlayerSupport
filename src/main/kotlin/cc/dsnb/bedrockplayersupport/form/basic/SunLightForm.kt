package cc.dsnb.bedrockplayersupport.form.basic

import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import org.bukkit.entity.Player
import org.geysermc.cumulus.form.SimpleForm
import su.nightexpress.sunlight.SunLightAPI
import su.nightexpress.sunlight.module.kits.KitsModule
import su.nightexpress.sunlight.module.warps.WarpsModule

class SunLightForm {

    fun sendDelHomeForm(player: Player) {
        val slUser = SunLightAPI.PLUGIN.userManager.getUserData(player)
        player.sendMessage(slUser.id)
        val playerHomesList = SunLightAPI.PLUGIN.data.getHomes(slUser.id)
        val form = SimpleForm.builder()
            .title(
                LegacyComponentSerializer.legacySection().serialize(
                    BedrockPlayerSupport.miniMessage.deserialize(
                        BedrockPlayerSupport.langConfigManager.getConfigData().delHomeFormTitle()
                    )
                )
            )
            .validResultHandler { simpleFormResponse ->
                player.chat("/delhome ${simpleFormResponse.clickedButton().text()}")
            }
        playerHomesList.forEach { form.button(it.id) }
        BedrockPlayerSupport.floodgateApi.sendForm(player.uniqueId, form)
    }

    fun sendGoHomeForm(player: Player) {
        val slUser = SunLightAPI.PLUGIN.userManager.getUserData(player)
        val playerHomesList = SunLightAPI.PLUGIN.data.getHomes(slUser.id)
        val form = SimpleForm.builder()
            .title(
                LegacyComponentSerializer.legacySection().serialize(
                    BedrockPlayerSupport.miniMessage.deserialize(
                        BedrockPlayerSupport.langConfigManager.getConfigData().goHomeFormTitle()
                    )
                )
            )
            .validResultHandler { simpleFormResponse ->
                player.chat("/home ${simpleFormResponse.clickedButton().text()}")
            }
        playerHomesList.forEach { form.button(it.id) }
        BedrockPlayerSupport.floodgateApi.sendForm(player.uniqueId, form)
    }

    fun sendWarpForm(player: Player) {
        val warps = SunLightAPI.PLUGIN.moduleManager.getModule(WarpsModule::class.java).let {
            if (it.isPresent) {
                it.get().warps
            } else {
                if ("zh_cn".equals(BedrockPlayerSupport.languageInUse, ignoreCase = true)) {
                    throw NullPointerException("在加载传送点模块时遇到错误, 请联系 BedrockPlayerSupport 插件作者")
                } else {
                    throw NullPointerException("An error was encountered while loading the warp module, please contact BedrockPlayerSupport plguin author")
                }
            }
        }
        val form = SimpleForm.builder()
            .title(
                LegacyComponentSerializer.legacySection().serialize(
                    BedrockPlayerSupport.miniMessage.deserialize(
                        BedrockPlayerSupport.langConfigManager.getConfigData().warpFormTitle()
                    )
                )
            )
            .validResultHandler { simpleFormResponse ->
                player.chat("/warp ${simpleFormResponse.clickedButton().text()}")
            }
        warps.forEach { form.button(it.id) }
        BedrockPlayerSupport.floodgateApi.sendForm(player.uniqueId, form)
    }

    fun sendKitForm(player: Player) {
        val kits = SunLightAPI.PLUGIN.moduleManager.getModule(KitsModule::class.java).let {
            if (it.isPresent) {
                it.get().kits
            } else {
                if ("zh_cn".equals(BedrockPlayerSupport.languageInUse, ignoreCase = true)) {
                    throw NullPointerException("在加载礼包模块时遇到错误, 请联系 BedrockPlayerSupport 插件作者")
                } else {
                    throw NullPointerException("An error was encountered while loading the kit module, please contact BedrockPlayerSupport plguin author")
                }
            }
        }
        val form = SimpleForm.builder()
            .title(
                LegacyComponentSerializer.legacySection().serialize(
                    BedrockPlayerSupport.miniMessage.deserialize(
                        BedrockPlayerSupport.langConfigManager.getConfigData().kitFormTitle()
                    )
                )
            )
            .validResultHandler { simpleFormResponse ->
                player.chat("/kit ${simpleFormResponse.clickedButton().text()}")
            }
        kits.forEach { form.button(it.id) }
        BedrockPlayerSupport.floodgateApi.sendForm(player.uniqueId, form)
    }

}