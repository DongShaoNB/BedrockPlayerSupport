package cc.dsnb.bedrockplayersupport.form.basic

import org.bukkit.entity.Player

interface BasicForm {
    fun sendDelHomeForm(player: Player)
    fun sendGoHomeForm(player: Player)
    fun sendWarpForm(player: Player)
}