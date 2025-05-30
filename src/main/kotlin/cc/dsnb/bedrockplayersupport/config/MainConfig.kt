package cc.dsnb.bedrockplayersupport.config

import space.arim.dazzleconf.annote.ConfComments
import space.arim.dazzleconf.annote.ConfDefault.*
import space.arim.dazzleconf.annote.ConfHeader
import space.arim.dazzleconf.annote.ConfKey

/**
 * @author DongShaoNB
 */
@ConfHeader(
    " -------------------------------------------------------------",
    " BedrockPlayerSupport Config File | Made by DongShaoNB",
    " Docs: https://docs.bps.dsnb.cc",
    " GitHub: https://github.com/DongShaoNB/BedrockPlayerSupport",
    " -------------------------------------------------------------"
)
interface MainConfig {

    @ConfKey("plugin.language")
    @ConfComments(
        "语言", "Language"
    )
    @DefaultString("zh_CN")
    fun language(): String

    @ConfKey("plugin.check-update")
    @ConfComments(
        "启动服务器时检测更新", "Check update when starting the server"
    )
    @DefaultBoolean(true)
    fun enableCheckUpdate(): Boolean

    @ConfKey("plugin.basic")
    @ConfComments(
        "填写正在使用的基础插件",
        "可选值: auto/cmi/essentialsx/huskhomes/advancedteleport/sunlight/none",
        "auto为自动检测 none为没有基础插件(即关闭功能)",
        "Fill in the basic plugins currently in use",
        "Optional value: auto/cmi/essentialsx/huskhomes/advancedteleport/sunlight/none",
        "auto is for automatic detection, none for no basic plugin (i.e. disable function)"
    )
    @DefaultString("auto")
    fun basicPlugin(): String

    @ConfKey("plugin.auth")
    @ConfComments(
        "填写正在使用的登录插件",
        "可选值: auto/authme/catseedlogin/nexauth/other/none",
        "auto 为自动检测, other 为其他登录插件, none 为没有登录插件(即关闭功能)",
        "当使用其他登录插件时 无法使用自动注册功能 只能使用自动登录功能",
        "Fill in the login plugin currently in use",
        "Optional value: auto/authme/catseedlogin/nexauth/other/none",
        "auto is for automatic detection, other is for other login plugins. none is for no login plugin (i.e. disable function)",
        "When using other login plugins, the automatic registration function cannot be used, and only the automatic login function can be used"
    )
    @DefaultString("auto")
    fun authPlugin(): String

    @ConfKey("plugin.support-papi")
    @ConfComments(
        "启用表单支持 PlaceholderAPI",
        "启用后可以在表单内使用 PlaceholderAPI",
        "Enable form support PlaceholderAPI",
        "Enable to use PlaceholderAPI within form"
    )
    @DefaultBoolean(true)
    fun enableSupportPAPI(): Boolean

    @ConfKey("form.teleport.enable")
    @ConfComments(
        "启用基岩版TP表单(/tpgui)", "Enable bedrock TP form (/tpgui)"
    )
    @DefaultBoolean(true)
    fun enableTeleportForm(): Boolean

    @ConfKey("form.teleport.cross-server")
    @ConfComments(
        "启用跨服模式(仅基础插件为 HuskHomes 时可用)",
        "启用后传送表单支持跨服传送",
        "Enable cross server mode (only available when the basic plugin is HuskHomes)",
        "Enable support cross server teleport of form"
    )
    @DefaultBoolean(false)
    fun enableCrossServer(): Boolean

    @ConfKey("form.teleport.receive.enable")
    @ConfComments(
        "启用基岩版接收传送表单(玩家收到传送请求时)",
        "Enable bedrock receive teleport form (Player receives teleport request)"
    )
    @DefaultBoolean(true)
    fun enableReceiveTeleportForm(): Boolean

    @ConfKey("form.msg.enable")
    @ConfComments(
        "启用基岩版消息表单(/msggui)", "Enable bedrock msg form (/msggui)"
    )
    @DefaultBoolean(true)
    fun enableMsgForm(): Boolean

    @ConfKey("form.home.enable")
    @ConfComments(
        "启用基岩版家表单(/homegui)", "Enable bedrock home form (/homegui)"
    )
    @DefaultBoolean(true)
    fun enableHomeForm(): Boolean

    @ConfKey("form.phome.enable")
    @ConfComments(
        "启用基岩版公共家表单(/phomegui)",
        "仅基础插件为 HuskHomes 时可用",
        "Enable bedrock public home form (/phomegui)",
        "Only available when the basic plugin is HuskHomes"
    )
    @DefaultBoolean(true)
    fun enablePublicHomeForm(): Boolean

    @ConfKey("form.back.enable")
    @ConfComments(
        "启用基岩版返回死亡点表单(玩家死亡复活后自动打开表单)",
        "Enable bedrock back death location form (Automatically open form after player's death and respawn)"
    )
    @DefaultBoolean(true)
    fun enableBackDeathLocForm(): Boolean

    @ConfKey("form.back.command")
    @ConfComments(
        "返回死亡点命令",
        "部分插件会用 /dback 或 其他命令, 请在此处替换",
        "The command of back death location",
        "If the command of back death location is /dback or other command, please replace it here",
    )
    @DefaultString("/back")
    fun backDeathLocCommand(): String

    @ConfKey("form.back.open-delay-time")
    @ConfComments(
        "返回死亡点表单打开延迟时间(单位: 刻, 20刻 = 1秒)",
        "默认为 20 刻, 但部分服务器加载世界较慢, 20刻不足以加载完世界",
        "若复活后表单没有打开, 请尝试调高此值",
        "The open delay time of back death location form",
        "The default value is 20 ticks, but some servers load the world slowly, 20 ticks is not enough to load the world completely",
        "If the form does not open after player respawn, please try increasing this value"
    )
    @DefaultLong(20)
    fun backDeathLocFormOpenDelayTick(): Long

    @ConfKey("form.warp.enable")
    @ConfComments(
        "启用基岩版传送点表单(/warpgui)", "Enable bedrock warp form (/warpgui)"
    )
    @DefaultBoolean(true)
    fun enableWarpForm(): Boolean

    @ConfKey("form.kit.enable")
    @ConfComments(
        "启用基岩版礼包表单(/kitgui)",
        "只有 CMI / EssentialsX / SunLight 可用",
        "Enable bedrock kit form (/kitgui)",
        "Only CMI / EssentialsX / SunLight is available",
    )
    @DefaultBoolean(true)
    fun enableKitForm(): Boolean

    @ConfKey("auth.register.enable")
    @ConfComments(
        "启用基岩版玩家自动注册功能", "Enable bedrock player automatic register function"
    )
    @DefaultBoolean(false)
    fun enableRegister(): Boolean

    @ConfKey("auth.register.password-length")
    @ConfComments(
        "随机的密码的长度", "The length of random password"
    )
    @DefaultInteger(16)
    fun passwordLength(): Int

    @ConfKey("auth.login.enable")
    @ConfComments(
        "启用基岩版玩家自动登录功能", "Enable bedrock player automatic login function"
    )
    @DefaultBoolean(false)
    fun enableLogin(): Boolean

    @ConfKey("auth.login.command")
    @ConfComments(
        "自动登录命令(控制台发送)",
        "验证插件设置为 other 时会调用此处命令",
        "在此处填写你使用的登录插件的强制登录命令",
        "可用变量: %playerName% 玩家名",
        "Automatic login command (send by console)",
        "When the auth plugin is set to other, this command will be sent",
        "Fill in the force login command for the auth plugin you are using here",
        "Available variable: %playerName% player name"
    )
    @DefaultString("forcelogin %playerName%")
    fun forceLoginCommand(): String

}
