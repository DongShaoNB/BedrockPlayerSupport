package cn.dsnbo.bedrockplayersupport.config;

import space.arim.dazzleconf.annote.ConfComments;
import space.arim.dazzleconf.annote.ConfDefault;
import space.arim.dazzleconf.annote.ConfHeader;
import space.arim.dazzleconf.annote.ConfKey;
import space.arim.dazzleconf.sorter.AnnotationBasedSorter;

/**
 * @author DongShaoNB
 */
@ConfHeader({
    " -----------------------------------------------------------",
    " BedrockPlayerSupport Config File | Made by DongShaoNB",
    " GitHub: https://github.com/DongShaoNB/BedrockPlayerSupport",
    " Docs: https://dongshaonb.github.io/BedrockPlayerSupport/",
    " -----------------------------------------------------------"
})
public interface Config {

  @ConfKey("plugin.language")
  @ConfComments({
      "语言",
      "Language"
  })
  @ConfDefault.DefaultString("zh_CN")
  @AnnotationBasedSorter.Order(1)
  String language();

  @ConfKey("plugin.check-update")
  @ConfComments({
      "启动服务器时检测更新",
      "Check update when starting the server"
  })
  @ConfDefault.DefaultBoolean(true)
  @AnnotationBasedSorter.Order(10)
  boolean checkUpdate();

  @ConfKey("plugin.logging-support-plugin-status")
  @ConfComments({
      "启动服务器时输出支持的插件的运行状态",
      "Output the running status of supported plugins when starting the server"
  })
  @ConfDefault.DefaultBoolean(true)
  @AnnotationBasedSorter.Order(20)
  boolean loggingSupportPluginStatus();

  @ConfKey("plugin.basic")
  @ConfComments({
      "填写正在使用的基础插件",
      "可选值: auto/cmi/essentialsx/huskhomes/none",
      "auto为自动检测 none为没有基础插件(即关闭功能)",
      "Fill in the basic plugins currently in use",
      "Optional value: auto/cmi/essentialsx/huskhomes/none",
      "Auto is for automatic detection, none for no basic plugin (i.e. disable function)"
  })
  @ConfDefault.DefaultString("auto")
  @AnnotationBasedSorter.Order(30)
  String basicPlugin();

  @ConfKey("plugin.auth")
  @ConfComments({
      "填写正在使用的登录插件",
      "可选值: auto/authme/catseedlogin/nexauth/other/none",
      "auto为自动检测 other为其他登录插件 none为没有登录插件(即关闭功能)",
      "当使用其他登录插件时 无法使用自动注册功能 只能使用自动登录功能",
      "Fill in the login plugin currently in use",
      "Optional value: auto/authme/catseedlogin/nexauth/other/none",
      "Auto is for automatic detection, while other is for other login plugins. None is for no login plugin (i.e. disable function)",
      "When using other login plugins, the automatic registration function cannot be used, and only the automatic login function can be used"
  })
  @ConfDefault.DefaultString("auto")
  @AnnotationBasedSorter.Order(40)
  String authPlugin();

  @ConfKey("form.teleport.enable")
  @ConfComments({
      "启用基岩版TP表单(/tpgui)",
      "Enable bedrock TP form (/tpgui)"
  })
  @ConfDefault.DefaultBoolean(true)
  @AnnotationBasedSorter.Order(50)
  boolean enableTeleportForm();

  @ConfKey("form.teleport.cross-server")
  @ConfComments({
      "启用跨服模式(仅基础插件为Huskhomes时可用)",
      "启用后传送表单支持跨服传送",
      "Enable cross server mode (only available when the basic plugin is Huskhomes)",
      "Enable support cross server teleport of form"
  })
  @ConfDefault.DefaultBoolean(false)
  @AnnotationBasedSorter.Order(51)
  boolean enableCrossServer();

  @ConfKey("form.msg.enable")
  @ConfComments({
      "启用基岩版消息表单(/msggui)",
      "Enable bedrock msg form (/msggui)"
  })
  @ConfDefault.DefaultBoolean(true)
  @AnnotationBasedSorter.Order(60)
  boolean enableMsgForm();

  @ConfKey("form.home.enable")
  @ConfComments({
      "启用基岩版家表单(/homegui)",
      "Enable bedrock home form (/homegui)"
  })
  @ConfDefault.DefaultBoolean(true)
  @AnnotationBasedSorter.Order(70)
  boolean enableHomeForm();

  @ConfKey("form.back.enable")
  @ConfComments({
      "启用基岩版返回死亡点表单(玩家死亡复活后自动弹出)",
      "Enable bedrock back location form (Automatically open after player's death and resurrection)"
  })
  @ConfDefault.DefaultBoolean(true)
  @AnnotationBasedSorter.Order(80)
  boolean enableBackForm();

  @ConfKey("form.back.command")
  @ConfComments({
      "返回死亡点命令",
      "The command of back death location"
  })
  @ConfDefault.DefaultString("/back")
  @AnnotationBasedSorter.Order(81)
  String backDeathLocCommand();

  @ConfKey("auth.register.enable")
  @ConfComments({
      "启用基岩版玩家注册功能",
      "Enable bedrock player register function"
  })
  @ConfDefault.DefaultBoolean(false)
  @AnnotationBasedSorter.Order(90)
  boolean enableRegister();

  @ConfKey("auth.register.password-length")
  @ConfComments({
      "随机的密码的长度",
      "The length of random password"
  })
  @ConfDefault.DefaultInteger(16)
  @AnnotationBasedSorter.Order(100)
  int passwordLength();

  @ConfKey("auth.login.enable")
  @ConfComments({
      "启用基岩版玩家登录功能",
      "Enable bedrock player login function"
  })
  @ConfDefault.DefaultBoolean(false)
  @AnnotationBasedSorter.Order(120)
  boolean enableLogin();

  @ConfKey("auth.login.command")
  @ConfComments({
      "自动登录命令(控制台发送)",
      "登录插件设置为other时会调用此处命令",
      "在此处填写你使用的登录插件的强制登录命令",
      "可用变量: %playerName% 玩家名",
      "Automatic login command (send by console)",
      "When the login plugin is set to other, this command will be sent",
      "Fill in the force login command for the login plugin you are using here",
      "Available variable: %playerName% player name"
  })
  @ConfDefault.DefaultString("forcelogin %playerName%")
  @AnnotationBasedSorter.Order(130)
  String forceLoginCommand();

}
