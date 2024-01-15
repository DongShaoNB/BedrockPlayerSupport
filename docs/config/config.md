# 配置文件

```
 #  -----------------------------------------------------------
 #  BedrockPlayerSupport|Made by DongShaoNB
 #  GitHub: https://github.com/DongShaoNB/BedrockPlayerSupport
 #  Docs: https://dongshaonb.github.io/BedrockPlayerSupport/#/
 #  -----------------------------------------------------------
plugin:
   # 启动服务器时检测更新
  check-update: true
   # 启动服务器时输出支持的插件的运行状态
  logging-support-plugin-status: true
   # 填写正在使用的基础插件
   # 可选值: auto/cmi/essentialsx/huskhomes/none
   # auto为自动检测 none为没有基础插件(即关闭功能)
  basic: 'auto'
   # 填写正在使用的登录插件
   # 可选值: auto/authme/catseedlogin/nexauth/other/none
   # auto为自动检测 other为其他登录插件 none为没有登录插件(即关闭功能)
   # 当使用其他登录插件时 无法使用自动注册功能 只能使用自动登录功能
  auth: 'auto'

form:
  teleport:
     # 启用基岩版TP表单(/tpgui)
    enable: true

  msg:
     # 启用基岩版MSG表单(/msggui)
    enable: true

  home:
     # 启用基岩版HOME表单(/homegui)
    enable: true

  back:
     # 启用基岩版BACK表单(玩家死亡复活后自动弹出)
    enable: true


auth:
  register:
     # 启用基岩版玩家注册功能
    enable: false
     # 随机的密码的长度
    password-length: 16
     # 注册成功后发送给玩家的信息
     # 可用变量: %password% 随机的密码
    message: '&a检测到你是基岩版玩家, 已自动注册! 密码为 %password%, 使用 /changepassword 命令修改密码'

  login:
     # 启用基岩版玩家登录功能
    enable: false
     # 自动登录命令(控制台发送)
     # 登录插件设置为other时会调用此处命令
     # 在此处填写你使用的登录插件的强制登录命令
     # 可用变量: %playerName% 玩家名
    command: 'forcelogin %playerName%'
     # 登录成功后发送给玩家的信息
    message: '&a检测到你是基岩版玩家, 已自动登录!'
```

**插件自带的配置文件已在每个选项上方写了详细说明 仍有不懂可以添加[QQ群](other/contact.md) @群主**