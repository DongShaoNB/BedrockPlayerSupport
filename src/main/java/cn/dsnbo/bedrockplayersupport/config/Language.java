package cn.dsnbo.bedrockplayersupport.config;

import space.arim.dazzleconf.annote.ConfComments;
import space.arim.dazzleconf.annote.ConfDefault.DefaultString;
import space.arim.dazzleconf.annote.ConfHeader;
import space.arim.dazzleconf.annote.ConfKey;
import space.arim.dazzleconf.sorter.AnnotationBasedSorter.Order;

/**
 * @author DongShaoNB
 */
@ConfHeader({
    " -----------------------------------------------------------",
    " BedrockPlayerSupport Language File | Made by DongShaoNB",
    " GitHub: https://github.com/DongShaoNB/BedrockPlayerSupport",
    " Crowdin: https://crowdin.com/project/mcbps",
    " MiniMessage: https://docs.advntr.dev/minimessage/format.html",
    " -----------------------------------------------------------",
    " 语言文件默认简体中文。如果需要其他语言，请访问 Crowdin",
    " message部分使用 MiniMessage 格式，请查看 MiniMessage 格式文档修改语言",
    " The language file default to Chinese Simplified. If you need another language, please visit Crowdin",
    " The message section use MiniMessage format. Please refer to the MiniMessage format document to modify the language"
})
public interface Language {

  @ConfKey("form.teleport.title")
  @ConfComments({
      "传送表单的标题",
      "Title of the teleport form"
  })
  @DefaultString("&6&l传送表单")
  @Order(10)
  String teleportFormTitle();

  @ConfKey("form.teleport.choose_type")
  @ConfComments({
      "传送表单的选择传送类型提示语",
      "Text of choose teleport type in teleport form"
  })
  @DefaultString("&a请选择传送类型")
  @Order(11)
  String teleportFormChooseTypeText();

  @ConfKey("form.teleport.choose_player")
  @ConfComments({
      "传送表单的选择传送玩家提示语",
      "Text of choose teleport player in teleport form"
  })
  @DefaultString("&a请选择要传送的玩家")
  @Order(12)
  String teleportFormChoosePlayerText();


  @ConfKey("form.received_teleport.tpa.title")
  @ConfComments({
      "玩家请求传送到你的表单的标题",
      "Title of the player requests to teleport to you form"
  })
  @DefaultString("&6&l玩家请求传送到你的位置 &f(TPA)")
  @Order(20)
  String receivedTpaFormTitle();

  @ConfKey("form.received_teleport.tpa.text")
  @ConfComments({
      "玩家请求传送到你的表单的描述文本",
      "可用变量: %requestorName% 请求者名字",
      "Description text of the player requests to teleport to you form",
      "Available variable: %requesterName% requestor name"
  })
  @DefaultString("玩家 %requesterName% 请求传送到你的位置")
  @Order(21)
  String receivedTpaFormText();

  @ConfKey("form.received_teleport.tpahere.title")
  @ConfComments({
      "玩家请求你传送到他的表单的标题",
      "Title of the player requests you to teleport to him form"
  })
  @DefaultString("&6&l玩家请求你传送到他的位置 &f(TPAHERE)")
  @Order(22)
  String receivedTpaHereFormTitle();

  @ConfKey("form.received_teleport.tpahere.text")
  @ConfComments({
      "玩家请求你传送到他的表单的描述文本",
      "可用变量: %requestorName% 请求者名字",
      "Description text of the player requests you to teleport to him form",
      "Available variable: %requesterName% requestor name"
  })
  @DefaultString("玩家 %requesterName% 请求你传送到他的位置")
  @Order(23)
  String receivedTpaHereFormText();

  @ConfKey("form.received_teleport.accept")
  @ConfComments({
      "传送表单的 '接受' 按钮文本",
      "Text of the 'Accept' button in teleport form"
  })
  @DefaultString("&a同意")
  @Order(24)
  String receivedTpFormAcceptButton();

  @ConfKey("form.received_teleport.deny")
  @ConfComments({
      "传送表单的 '拒绝' 按钮文本",
      "Text of the 'Deny' button in teleport form"
  })
  @DefaultString("&c拒绝")
  @Order(25)
  String receivedTpFormDenyButton();

  @ConfKey("form.msg.title")
  @ConfComments({
      "消息表单的标题",
      "Title of the msg form"
  })
  @DefaultString("&6&l消息表单")
  @Order(30)
  String msgFormTitle();

  @ConfKey("form.msg.choose_player")
  @ConfComments({
      "消息表单的选择接收消息玩家提示语",
      "Text of choose receive message player in msg form"
  })
  @DefaultString("&a请选择接收消息的玩家")
  @Order(31)
  String msgFormChoosePlayerText();

  @ConfKey("form.msg.input_message")
  @ConfComments({
      "消息表单的输入消息提示语",
      "Text of input message in msg form"
  })
  @DefaultString("&a请填写要发送的消息")
  @Order(32)
  String msgFormInputMessageText();

  @ConfKey("form.home.title")
  @ConfComments({
      "家表单的标题",
      "Title of the home form"
  })
  @DefaultString("&6&l我的家")
  @Order(40)
  String homeFormTitle();

  @ConfKey("form.back.title")
  @ConfComments({
      "返回死亡点表单的标题",
      "Title of the back death location form"
  })
  @DefaultString("&6&l返回死亡点表单")
  @Order(50)
  String backFormTitle();

  @ConfKey("form.back.text")
  @ConfComments({
      "返回死亡点表单的描述文本",
      "Description text of the back death location form"
  })
  @DefaultString("是否返回上个死亡点")
  @Order(51)
  String backFormText();

  @ConfKey("form.back.yes")
  @ConfComments({
      "返回死亡点表单的 '是' 按钮文本",
      "Text of the 'Yes' button in back death location form"
  })
  @DefaultString("&a是")
  @Order(52)
  String backFormYesButton();

  @ConfKey("form.back.no")
  @ConfComments({
      "返回死亡点表单的 '否' 按钮文本",
      "Text of the 'No' button in back death location form"
  })
  @DefaultString("&c否")
  @Order(53)
  String backFormNoButton();

  @ConfKey("message.not_bedrock_player")
  @ConfComments({
      "不是基岩版的玩家使用表单命令的错误提示",
      "Error of player use form command but is not bedrock player"
  })
  @DefaultString("<red>你不是基岩版玩家!")
  @Order(100)
  String notBedrockPlayer();

  @ConfKey("message.no_other_online_player")
  @ConfComments({
      "玩家使用表单命令但没有其他在线玩家的错误提示",
      "Error of player use form command but no other online player"
  })
  @DefaultString("<red>当前没有其他玩家在线!")
  @Order(101)
  String noOtherOnlinePlayer();

  @ConfKey("message.register_successfully")
  @ConfComments({
      "自动注册成功后发送给玩家的消息",
      "可用变量: %password% 密码",
      "Text of successful automatic register after send to player",
      "Available variable: %password% password"
  })
  @DefaultString("<green>检测到你是基岩版玩家, 已自动注册! 密码为 %password%, 使用 /changepassword 命令修改密码")
  @Order(102)
  String registerSuccessfully();

  @ConfKey("message.login_successfully")
  @ConfComments({
      "自动登录成功后发送给玩家的消息",
      "Text of successful automatic login after send to player",
  })
  @DefaultString("<green>检测到你是基岩版玩家, 已自动登录!")
  @Order(103)
  String loginSuccessfully();


}
