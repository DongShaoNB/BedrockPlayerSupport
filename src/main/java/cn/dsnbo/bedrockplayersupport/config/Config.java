package cn.dsnbo.bedrockplayersupport.config;

import space.arim.dazzleconf.annote.ConfComments;
import space.arim.dazzleconf.annote.ConfDefault;
import space.arim.dazzleconf.annote.ConfHeader;
import space.arim.dazzleconf.annote.ConfKey;

/**
 * @author DongShaoNB
 */
@ConfHeader({" -----------------------------------------------------------",
        " BedrockPlayerSupport|Made by DongShaoNB",
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
    boolean checkUpdate();

    @ConfKey("plugin.logging-support-plugin-status")
    @ConfComments({
            "启动服务器时输出支持的插件的运行状态"
    })
    @ConfDefault.DefaultBoolean(true)
    boolean loggingSupportPluginStatus();

    @ConfKey("plugin.basic")
    @ConfComments({
            "填写正在使用的基础插件",
            "可选值: auto/cmi/essentialsx/huskhomes/none",
            "auto为自动检测并填写 none为没有基础插件"
    })
    @ConfDefault.DefaultString("auto")
    String basicPlugin();

    @ConfKey("plugin.auth")
    @ConfComments({
            "填写正在使用的登录插件",
            "可选值: auto/authme/catseedlogin/nexauth/other/none",
            "auto为自动检测并填写 other为不在可选值内 none为没有登录插件",
            "需要自动注册/登录功能且使用的登录插件不在可选值内 请填写other"
    })
    @ConfDefault.DefaultString("auto")
    String authPlugin();

    @ConfKey("form.teleport.enable")
    @ConfComments({
            "启用基岩版TP表单(/tpgui)"
    })
    @ConfDefault.DefaultBoolean(true)
    boolean enableTeleportForm();

    @ConfKey("form.msg.enable")
    @ConfComments({
            "启用基岩版MSG表单(/msggui)"
    })
    @ConfDefault.DefaultBoolean(true)
    boolean enableMsgForm();

    @ConfKey("form.home.enable")
    @ConfComments({
            "启用基岩版HOME表单(/homegui)"
    })
    @ConfDefault.DefaultBoolean(true)
    boolean enableHomeForm();

    @ConfKey("form.back.enable")
    @ConfComments({
            "启用基岩版BACK表单(玩家死亡复活后自动弹出)"
    })
    @ConfDefault.DefaultBoolean(true)
    boolean enableBackForm();

    @ConfKey("auth.register.enable")
    @ConfComments({"启用基岩版玩家注册功能"})
    @ConfDefault.DefaultBoolean(false)
    boolean enableRegister();

    @ConfKey("auth.register.password-length")
    @ConfComments({"随机的密码的长度"})
    @ConfDefault.DefaultInteger(16)
    int passwordLength();

    @ConfKey("auth.register.force_command")
    @ConfComments({
            "自动注册命令(代替玩家发送)",
            "安全插件设置为other时会调用此处命令",
            "在此处填写你使用的安全插件的强制注册命令",
            "可用变量: %password% 随机的密码"
    })
    @ConfDefault.DefaultString("register %password% %password%")
    String forceRegisterCommand();

    @ConfKey("auth.register.message")
    @ConfComments({
            "注册成功后发送给玩家的信息",
            "可用变量: %password% 随机的密码"
    })
    @ConfDefault.DefaultString("&a检测到你是基岩版玩家, 已自动注册! 密码为 %password%, 使用 /changepassword 命令修改密码")
    String registerMessage();

    @ConfKey("auth.login.enable")
    @ConfComments({"启用基岩版玩家登录功能"})
    @ConfDefault.DefaultBoolean(false)
    boolean enableLogin();

    @ConfKey("auth.login.command")
    @ConfComments({"自动登录命令(控制台发送)",
            "登录插件设置为other时会调用此处命令",
            "在此处填写你使用的登录插件的强制登录命令",
            "可用变量: %playerName% 玩家名"})
    @ConfDefault.DefaultString("forcelogin %playerName%")
    String forceLoginCommand();

    @ConfKey("auth.login.message")
    @ConfComments({"登录成功后发送给玩家的信息"})
    @ConfDefault.DefaultString("&a检测到你是基岩版玩家, 已自动登录!")
    String loginMessage();

}
