package cn.dsnbo.bedrockplayersupport.config;

import space.arim.dazzleconf.annote.ConfComments;
import space.arim.dazzleconf.annote.ConfDefault;
import space.arim.dazzleconf.annote.ConfHeader;
import space.arim.dazzleconf.annote.ConfKey;
import space.arim.dazzleconf.sorter.AnnotationBasedSorter;

/**
 * @author DongShaoNB
 */
@ConfHeader({" -----------------------------------------------------------",
        " BedrockPlayerSupport | Made by DongShaoNB",
        " GitHub: https://github.com/DongShaoNB/BedrockPlayerSupport",
        " Docs: https://dongshaonb.github.io/BedrockPlayerSupport/#/",
        " -----------------------------------------------------------"

})
public interface Config {

    @ConfKey("plugin.check-update")
    @ConfComments({
            "启动服务器时检测更新"
    })
    @ConfDefault.DefaultBoolean(true)
    @AnnotationBasedSorter.Order(1)
    boolean checkUpdate();

    @ConfKey("plugin.logging-support-plugin-status")
    @ConfComments({
            "启动服务器时输出支持的插件的运行状态"
    })
    @ConfDefault.DefaultBoolean(true)
    @AnnotationBasedSorter.Order(2)
    boolean loggingSupportPluginStatus();

    @ConfKey("plugin.basic")
    @ConfComments({
            "填写正在使用的基础插件",
            "可选值: auto/cmi/essentialsx/huskhomes/none",
            "auto为自动检测 none为没有基础插件(即关闭功能)"
    })
    @ConfDefault.DefaultString("auto")
    @AnnotationBasedSorter.Order(3)
    String basicPlugin();

    @ConfKey("plugin.auth")
    @ConfComments({
            "填写正在使用的登录插件",
            "可选值: auto/authme/catseedlogin/nexauth/other/none",
            "auto为自动检测 other为其他登录插件 none为没有登录插件(即关闭功能)",
            "当使用其他登录插件时 无法使用自动注册功能 只能使用自动登录功能"
    })
    @ConfDefault.DefaultString("auto")
    @AnnotationBasedSorter.Order(4)
    String authPlugin();

    @ConfKey("form.teleport.enable")
    @ConfComments({
            "启用基岩版TP表单(/tpgui)"
    })
    @ConfDefault.DefaultBoolean(true)
    @AnnotationBasedSorter.Order(5)
    boolean enableTeleportForm();

    @ConfKey("form.msg.enable")
    @ConfComments({
            "启用基岩版MSG表单(/msggui)"
    })
    @ConfDefault.DefaultBoolean(true)
    @AnnotationBasedSorter.Order(6)
    boolean enableMsgForm();

    @ConfKey("form.home.enable")
    @ConfComments({
            "启用基岩版HOME表单(/homegui)"
    })
    @ConfDefault.DefaultBoolean(true)
    @AnnotationBasedSorter.Order(7)
    boolean enableHomeForm();

    @ConfKey("form.back.enable")
    @ConfComments({
            "启用基岩版BACK表单(玩家死亡复活后自动弹出)"
    })
    @ConfDefault.DefaultBoolean(true)
    @AnnotationBasedSorter.Order(8)
    boolean enableBackForm();

    @ConfKey("auth.register.enable")
    @ConfComments({"启用基岩版玩家注册功能"})
    @ConfDefault.DefaultBoolean(false)
    @AnnotationBasedSorter.Order(9)
    boolean enableRegister();

    @ConfKey("auth.register.password-length")
    @ConfComments({"随机的密码的长度"})
    @ConfDefault.DefaultInteger(16)
    @AnnotationBasedSorter.Order(10)
    int passwordLength();

    @ConfKey("auth.register.message")
    @ConfComments({
            "注册成功后发送给玩家的信息",
            "可用变量: %password% 随机的密码"
    })
    @ConfDefault.DefaultString("&a检测到你是基岩版玩家, 已自动注册! 密码为 %password%, 使用 /changepassword 命令修改密码")
    @AnnotationBasedSorter.Order(11)
    String registerMessage();

    @ConfKey("auth.login.enable")
    @ConfComments({"启用基岩版玩家登录功能"})
    @ConfDefault.DefaultBoolean(false)
    @AnnotationBasedSorter.Order(12)
    boolean enableLogin();

    @ConfKey("auth.login.command")
    @ConfComments({"自动登录命令(控制台发送)",
            "登录插件设置为other时会调用此处命令",
            "在此处填写你使用的登录插件的强制登录命令",
            "可用变量: %playerName% 玩家名"})
    @ConfDefault.DefaultString("forcelogin %playerName%")
    @AnnotationBasedSorter.Order(13)
    String forceLoginCommand();

    @ConfKey("auth.login.message")
    @ConfComments({"登录成功后发送给玩家的信息"})
    @ConfDefault.DefaultString("&a检测到你是基岩版玩家, 已自动登录!")
    @AnnotationBasedSorter.Order(14)
    String loginMessage();

}
