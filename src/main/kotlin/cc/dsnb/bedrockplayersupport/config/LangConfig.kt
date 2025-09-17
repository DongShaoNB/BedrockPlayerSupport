package cc.dsnb.bedrockplayersupport.config

import space.arim.dazzleconf.annote.ConfComments
import space.arim.dazzleconf.annote.ConfDefault.DefaultString
import space.arim.dazzleconf.annote.ConfHeader
import space.arim.dazzleconf.annote.ConfKey

/**
 * @author DongShaoNB
 */
@ConfHeader(
    " -------------------------------------------------------------",
    " BedrockPlayerSupport Language File | Made by DongShaoNB",
    " Crowdin: https://crowdin.com/project/mcbps",
    " MiniMessage: https://docs.advntr.dev/minimessage/format.html",
    " GitHub: https://github.com/DongShaoNB/BedrockPlayerSupport",
    " -------------------------------------------------------------",
    " 语言文件默认简体中文。如果需要其他语言，请访问 Crowdin",
    " 我们使用 MiniMessage 格式，请查看 MiniMessage 格式文档修改语言",
    " The language file default to Chinese Simplified. If you need another language, please visit Crowdin",
    " We use MiniMessage format, Please refer to the MiniMessage format document to modify the language file"
)
interface LangConfig {

    @ConfKey("form.teleport.title")
    @ConfComments(
        "传送表单的标题", "Title of the teleport form"
    )
    @DefaultString("<gold>传送表单")
    fun teleportFormTitle(): String

    @ConfKey("form.teleport.choose-type")
    @ConfComments(
        "传送表单的选择传送类型提示语", "Text of choose teleport type in teleport form"
    )
    @DefaultString("<green>请选择传送类型")
    fun teleportFormChooseTypeText(): String

    @ConfKey("form.teleport.choose-player")
    @ConfComments(
        "传送表单的选择传送玩家提示语", "Text of choose teleport player in teleport form"
    )
    @DefaultString("<green>请选择要传送的玩家")
    fun teleportFormChoosePlayerText(): String

    @ConfKey("form.received-teleport.tpa.title")
    @ConfComments(
        "玩家请求传送到你的表单的标题", "Title of the player requests to teleport to you form"
    )
    @DefaultString("<green>玩家请求传送到你的位置 <white>(TPA)")
    fun receivedTpaFormTitle(): String

    @ConfKey("form.received-teleport.tpa.text")
    @ConfComments(
        "玩家请求传送到你的表单的描述文本",
        "可用变量: %requesterName% 请求者名字",
        "Description text of the player requests to teleport to you form",
        "Available variable: %requesterName% requester name"
    )
    @DefaultString("玩家 %requesterName% 请求传送到你的位置")
    fun receivedTpaFormText(): String

    @ConfKey("form.received-teleport.tpahere.title")
    @ConfComments(
        "玩家请求你传送到他的表单的标题", "Title of the player requests you to teleport to him form"
    )
    @DefaultString("<green>玩家请求你传送到他的位置 <white>(TPAHERE)")
    fun receivedTpaHereFormTitle(): String

    @ConfKey("form.received-teleport.tpahere.text")
    @ConfComments(
        "玩家请求你传送到他的表单的描述文本",
        "可用变量: %requesterName% 请求者名字",
        "Description text of the player requests you to teleport to him form",
        "Available variable: %requesterName% requester name"
    )
    @DefaultString("玩家 %requesterName% 请求你传送到他的位置")
    fun receivedTpaHereFormText(): String

    @ConfKey("form.received-teleport.accept")
    @ConfComments(
        "传送表单的 '接受' 按钮文本", "Text of the 'Accept' button in teleport form"
    )
    @DefaultString("<green>同意")
    fun receivedTpFormAcceptButton(): String

    @ConfKey("form.received-teleport.deny")
    @ConfComments(
        "传送表单的 '拒绝' 按钮文本", "Text of the 'Deny' button in teleport form"
    )
    @DefaultString("<red>拒绝")
    fun receivedTpFormDenyButton(): String

    @ConfKey("form.msg.title")
    @ConfComments(
        "消息表单的标题", "Title of the msg form"
    )
    @DefaultString("<gold>消息表单")
    fun msgFormTitle(): String

    @ConfKey("form.msg.choose-player")
    @ConfComments(
        "消息表单的选择接收消息玩家提示语", "Text of choose receive message player in msg form"
    )
    @DefaultString("<green>请选择接收消息的玩家")
    fun msgFormChoosePlayerText(): String

    @ConfKey("form.msg.input-message")
    @ConfComments(
        "消息表单的输入消息提示语", "Text of input message in msg form"
    )
    @DefaultString("<green>请填写要发送的消息")
    fun msgFormInputMessageText(): String

    @ConfKey("form.home.title")
    @ConfComments(
        "家表单的标题", "Title of the home form"
    )
    @DefaultString("<gold>家")
    fun homeFormTitle(): String

    @ConfKey("form.home.sethome-button")
    @ConfComments(
        "家表单的 '设置家' 按钮文本", "Text of the 'SetHome' button in home form"
    )
    @DefaultString("<green>设置家")
    fun homeFormSetHomeButton(): String

    @ConfKey("form.home.delhome-button")
    @ConfComments(
        "家表单的 '删除家' 按钮文本", "Text of the 'DelHome' button in home form"
    )
    @DefaultString("<red>删除家")
    fun homeFormDelHomeButton(): String

    @ConfKey("form.home.gohome-button")
    @ConfComments(
        "家表单的 '回家' 按钮文本", "Text of the 'GoHome' button in home form"
    )
    @DefaultString("<gold>回家")
    fun homeFormGoHomeButton(): String

    @ConfKey("form.phome.title")
    @ConfComments(
        "公共家表单的标题",
        "Title of the public home form"
    )
    @DefaultString("<gold>公共家")
    fun publicHomeFormTitle(): String

    @ConfKey("form.sethome.title")
    @ConfComments(
        "设置家表单的标题", "Title of the set home form"
    )
    @DefaultString("<gold>设置家表单")
    fun setHomeFormTitle(): String

    @ConfKey("form.sethome.text")
    @ConfComments(
        "设置家表单的描述文本", "Text of the set home form"
    )
    @DefaultString("<gold>家的名称")
    fun setHomeFormText(): String

    @ConfKey("form.delhome.title")
    @ConfComments(
        "删除家表单的标题", "Title of the delete home form"
    )
    @DefaultString("<gold>删除家表单")
    fun delHomeFormTitle(): String

    @ConfKey("form.gohome.title")
    @ConfComments(
        "回家表单的标题", "Title of the go home form"
    )
    @DefaultString("<gold>回家表单")
    fun goHomeFormTitle(): String

    @ConfKey("form.back.title")
    @ConfComments(
        "返回死亡点表单的标题", "Title of the back death location form"
    )
    @DefaultString("<gold>返回死亡点表单")
    fun backFormTitle(): String

    @ConfKey("form.back.text")
    @ConfComments(
        "返回死亡点表单的描述文本", "Text of the back death location form"
    )
    @DefaultString("是否返回上个死亡点")
    fun backFormText(): String

    @ConfKey("form.back.button-yes")
    @ConfComments(
        "返回死亡点表单的 '是' 按钮文本", "Text of the 'YES' button in back death location form"
    )
    @DefaultString("<green>是")
    fun backFormYesButton(): String

    @ConfKey("form.back.button-no")
    @ConfComments(
        "返回死亡点表单的 '否' 按钮文本", "Text of the 'NO' button in back death location form"
    )
    @DefaultString("<red>否")
    fun backFormNoButton(): String

    @ConfKey("form.warp.title")
    @ConfComments(
        "传送点表单的标题", "Title of the warp form"
    )
    @DefaultString("<gold>传送点表单")
    fun warpFormTitle(): String

    @ConfKey("form.kit.title")
    @ConfComments(
        "礼包表单的标题", "Title of the kit form"
    )
    @DefaultString("<gold>礼包表单")
    fun kitFormTitle(): String

    @ConfKey("form.money.title")
    @ConfComments(
        "经济支付表单的标题", "Title of the money form"
    )
    @DefaultString("<gold>经济支付表单")
    fun moneyFormTitle(): String

    @ConfKey("form.money.choose-player")
    @ConfComments(
        "经济支付表单的选择接收转账玩家提示语", "Text of choose receive transfer player in money form"
    )
    @DefaultString("<green>请选择接收转账的玩家")
    fun moneyFormChoosePlayerText(): String

    @ConfKey("form.money.input-money")
    @ConfComments(
        "经济支付表单的输入转账金额提示语", "Text of input transfer money in money form"
    )
    @DefaultString("<green>请填写要转账的金额")
    fun moneyFormInputAmountText(): String

    @ConfKey("form.points.title")
    @ConfComments(
        "点券支付表单的标题", "Title of the points form"
    )
    @DefaultString("<gold>点券支付表单")
    fun pointsFormTitle(): String

    @ConfKey("form.points.choose-player")
    @ConfComments(
        "点券支付表单的选择接收点券玩家提示语", "Text of choose receive points player in points form"
    )
    @DefaultString("<green>请选择接收点券的玩家")
    fun pointsFormChoosePlayerText(): String

    @ConfKey("form.point.input-points")
    @ConfComments(
        "点券支付表单的输入转账点券数量提示语", "Text of input transfer points in points form"
    )
    @DefaultString("<green>请填写要转账的点券数量")
    fun pointsFormInputAmountText(): String

    @ConfKey("message.not-bedrock-player")
    @ConfComments(
        "不是基岩版的玩家使用表单命令的错误提示", "Error of player use form command but is not bedrock player"
    )
    @DefaultString("<red>你不是基岩版玩家!")
    fun notBedrockPlayer(): String

    @ConfKey("message.no-other-online-player")
    @ConfComments(
        "玩家使用表单命令但没有其他在线玩家的错误提示", "Error of player use form command but no other online player"
    )
    @DefaultString("<red>当前没有其他玩家在线!")
    fun noOtherOnlinePlayer(): String

    @ConfKey("message.register-successfully")
    @ConfComments(
        "自动注册成功后发送给玩家的消息",
        "可用变量: %password% 密码",
        "Text of successful automatic register after send to player",
        "Available variable: %password% password"
    )
    @DefaultString("<green>检测到你是基岩版玩家, 已自动注册! 密码为 %password%, 使用 /changepassword 命令修改密码")
    fun registerSuccessfully(): String

    @ConfKey("message.login-successfully")
    @ConfComments(
        "自动登录成功后发送给玩家的消息", "Text of successful automatic login after send to player"
    )
    @DefaultString("<green>检测到你是基岩版玩家, 已自动登录!")
    fun loginSuccessfully(): String

    @ConfKey("plugin.reload-successfully")
    @ConfComments(
        "插件重载成功后发送给命令发送者的消息",
        "可用变量: %time% 重载耗时(毫秒)",
        "Text of successful reload plugin after send to command sender",
        "Available variable: %time% reload time(millisecond)"
    )
    @DefaultString("<green>插件重载成功, 耗时: %time% 毫秒")
    fun reloadSuccessfully(): String

}