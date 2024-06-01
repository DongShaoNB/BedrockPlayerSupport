package cc.dsnb.bedrockplayersupport.config

import space.arim.dazzleconf.annote.ConfComments
import space.arim.dazzleconf.annote.ConfDefault.DefaultString
import space.arim.dazzleconf.annote.ConfHeader
import space.arim.dazzleconf.annote.ConfKey
import space.arim.dazzleconf.sorter.AnnotationBasedSorter.Order

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
    @Order(10)
    fun teleportFormTitle(): String

    @ConfKey("form.teleport.choose-type")
    @ConfComments(
        "传送表单的选择传送类型提示语", "Text of choose teleport type in teleport form"
    )
    @DefaultString("<green>请选择传送类型")
    @Order(11)
    fun teleportFormChooseTypeText(): String

    @ConfKey("form.teleport.choose-player")
    @ConfComments(
        "传送表单的选择传送玩家提示语", "Text of choose teleport player in teleport form"
    )
    @DefaultString("<green>请选择要传送的玩家")
    @Order(12)
    fun teleportFormChoosePlayerText(): String

    @ConfKey("form.received-teleport.tpa.title")
    @ConfComments(
        "玩家请求传送到你的表单的标题", "Title of the player requests to teleport to you form"
    )
    @DefaultString("<green>玩家请求传送到你的位置 <white>(TPA)")
    @Order(20)
    fun receivedTpaFormTitle(): String

    @ConfKey("form.received-teleport.tpa.text")
    @ConfComments(
        "玩家请求传送到你的表单的描述文本",
        "可用变量: %requesterName% 请求者名字",
        "Description text of the player requests to teleport to you form",
        "Available variable: %requesterName% requester name"
    )
    @DefaultString("玩家 %requesterName% 请求传送到你的位置")
    @Order(21)
    fun receivedTpaFormText(): String

    @ConfKey("form.received-teleport.tpahere.title")
    @ConfComments(
        "玩家请求你传送到他的表单的标题", "Title of the player requests you to teleport to him form"
    )
    @DefaultString("<green>玩家请求你传送到他的位置 <white>(TPAHERE)")
    @Order(22)
    fun receivedTpaHereFormTitle(): String

    @ConfKey("form.received-teleport.tpahere.text")
    @ConfComments(
        "玩家请求你传送到他的表单的描述文本",
        "可用变量: %requesterName% 请求者名字",
        "Description text of the player requests you to teleport to him form",
        "Available variable: %requesterName% requester name"
    )
    @DefaultString("玩家 %requesterName% 请求你传送到他的位置")
    @Order(23)
    fun receivedTpaHereFormText(): String

    @ConfKey("form.received-teleport.accept")
    @ConfComments(
        "传送表单的 '接受' 按钮文本", "Text of the 'Accept' button in teleport form"
    )
    @DefaultString("<green>同意")
    @Order(24)
    fun receivedTpFormAcceptButton(): String

    @ConfKey("form.received-teleport.deny")
    @ConfComments(
        "传送表单的 '拒绝' 按钮文本", "Text of the 'Deny' button in teleport form"
    )
    @DefaultString("<red>拒绝")
    @Order(25)
    fun receivedTpFormDenyButton(): String

    @ConfKey("form.msg.title")
    @ConfComments(
        "消息表单的标题", "Title of the msg form"
    )
    @DefaultString("<gold>消息表单")
    @Order(30)
    fun msgFormTitle(): String

    @ConfKey("form.msg.choose-player")
    @ConfComments(
        "消息表单的选择接收消息玩家提示语", "Text of choose receive message player in msg form"
    )
    @DefaultString("<green>请选择接收消息的玩家")
    @Order(31)
    fun msgFormChoosePlayerText(): String

    @ConfKey("form.msg.input-message")
    @ConfComments(
        "消息表单的输入消息提示语", "Text of input message in msg form"
    )
    @DefaultString("<green>请填写要发送的消息")
    @Order(32)
    fun msgFormInputMessageText(): String

    @ConfKey("form.home.title")
    @ConfComments(
        "家表单的标题", "Title of the home form"
    )
    @DefaultString("<gold>我的家")
    @Order(40)
    fun homeFormTitle(): String

    @ConfKey("form.back.title")
    @ConfComments(
        "返回死亡点表单的标题", "Title of the back death location form"
    )
    @DefaultString("<gold>返回死亡点表单")
    @Order(50)
    fun backFormTitle(): String

    @ConfKey("form.back.text")
    @ConfComments(
        "返回死亡点表单的描述文本", "Text of the back death location form"
    )
    @DefaultString("是否返回上个死亡点")
    @Order(51)
    fun backFormText(): String

    @ConfKey("form.back.button-yes")
    @ConfComments(
        "返回死亡点表单的 '是' 按钮文本", "Text of the 'YES' button in back death location form"
    )
    @DefaultString("<green>是")
    @Order(52)
    fun backFormYesButton(): String

    @ConfKey("form.back.button-no")
    @ConfComments(
        "返回死亡点表单的 '否' 按钮文本", "Text of the 'NO' button in back death location form"
    )
    @DefaultString("<red>否")
    @Order(53)
    fun backFormNoButton(): String

    @ConfKey("message.not-bedrock-player")
    @ConfComments(
        "不是基岩版的玩家使用表单命令的错误提示", "Error of player use form command but is not bedrock player"
    )
    @DefaultString("<red>你不是基岩版玩家!")
    @Order(100)
    fun notBedrockPlayer(): String

    @ConfKey("message.no-other-online-player")
    @ConfComments(
        "玩家使用表单命令但没有其他在线玩家的错误提示", "Error of player use form command but no other online player"
    )
    @DefaultString("<red>当前没有其他玩家在线!")
    @Order(101)
    fun noOtherOnlinePlayer(): String

    @ConfKey("message.register-successfully")
    @ConfComments(
        "自动注册成功后发送给玩家的消息",
        "可用变量: %password% 密码",
        "Text of successful automatic register after send to player",
        "Available variable: %password% password"
    )
    @DefaultString("<green>检测到你是基岩版玩家, 已自动注册! 密码为 %password%, 使用 /changepassword 命令修改密码")
    @Order(102)
    fun registerSuccessfully(): String

    @ConfKey("message.login-successfully")
    @ConfComments(
        "自动登录成功后发送给玩家的消息", "Text of successful automatic login after send to player"
    )
    @DefaultString("<green>检测到你是基岩版玩家, 已自动登录!")
    @Order(103)
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