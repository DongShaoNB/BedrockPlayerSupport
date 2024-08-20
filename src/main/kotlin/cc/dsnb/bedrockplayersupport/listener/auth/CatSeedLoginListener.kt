package cc.dsnb.bedrockplayersupport.listener.auth

import cc.baka9.catseedlogin.bukkit.CatSeedLogin
import cc.baka9.catseedlogin.bukkit.CatSeedLoginAPI
import cc.baka9.catseedlogin.bukkit.Config
import cc.baka9.catseedlogin.bukkit.database.Cache
import cc.baka9.catseedlogin.bukkit.event.CatSeedPlayerRegisterEvent
import cc.baka9.catseedlogin.bukkit.`object`.LoginPlayer
import cc.baka9.catseedlogin.bukkit.`object`.LoginPlayerHelper
import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import cc.dsnb.bedrockplayersupport.util.StringUtil
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

/**
 * @author DongShaoNB
 */
class CatSeedLoginListener : Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player
        val playerName = player.name
        val mainConfig = BedrockPlayerSupport.mainConfigManager.getConfigData()
        val langConfig = BedrockPlayerSupport.langConfigManager.getConfigData()
        if (BedrockPlayerSupport.floodgateApi.isFloodgatePlayer(player.uniqueId)) {
            if (CatSeedLoginAPI.isRegister(playerName)) {
                if (CatSeedLoginAPI.isLogin(playerName).not()) {
                    if (mainConfig.enableLogin()) {
                        LoginPlayerHelper.add(LoginPlayer(player.name, ""))
                        Cache.refresh(player.name)
                        if (Config.Settings.AfterLoginBack && Config.Settings.CanTpSpawnLocation) {
                            Config.getOfflineLocation(player).ifPresent(player::teleport)
                        }
                        player.sendMessage(StringUtil.formatTextToComponent(player, langConfig.loginSuccessfully()))
                    }
                }
            } else {
                if (mainConfig.enableRegister()) {
                    try {
                        val password = StringUtil.randomString(mainConfig.passwordLength())
                        val currentIp = player.address.address.hostAddress
                        val loginPlayerListLikeByIp = CatSeedLogin.sql.getLikeByIp(currentIp)
                        if (loginPlayerListLikeByIp.size >= Config.Settings.IpRegisterCountLimit) {
                            player.sendMessage(
                                Config.Language.REGISTER_MORE
                                    .replace("{count}", loginPlayerListLikeByIp.size.toString())
                                    .replace("{accounts}", loginPlayerListLikeByIp.joinToString(", ") { it.name })
                            )
                        } else {
                            val lp = LoginPlayer(playerName, password)
                            lp.crypt()
                            CatSeedLogin.sql.add(lp)
                            LoginPlayerHelper.add(lp)
                            BedrockPlayerSupport.scheduler.runTask(CatSeedLogin.instance) {
                                val event1 = CatSeedPlayerRegisterEvent(player)
                                Bukkit.getServer().pluginManager.callEvent(event1)
                            }
                            player.sendMessage(Config.Language.REGISTER_SUCCESS)
                            player.updateInventory()
                            LoginPlayerHelper.recordCurrentIP(player, lp)
                            player.sendMessage(
                                StringUtil.formatTextToComponent(
                                    player,
                                    langConfig.registerSuccessfully().replace("%password%", password)
                                )
                            )
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

}