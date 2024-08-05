<p align="center" style="font-size:38px">BedrockPlayerSupport</p>
<p align="center"><b>让其他插件对基岩版玩家更加友好</b></p>
<p align="center">
    <img alt="GitHub Repo stars" src="https://img.shields.io/github/stars/DongShaoNB/BedrockPlayerSupport">
    <img alt="GitHub Downloads (all assets, all releases)" src="https://img.shields.io/github/downloads/DongShaoNB/BedrockPlayerSupport/total">
    <img alt="GitHub Release" src="https://img.shields.io/github/v/release/DongShaoNB/BedrockPlayerSupport">
    <a title="Crowdin" target="_blank" href="https://crowdin.com/project/mcbps"><img src="https://badges.crowdin.net/mcbps/localized.svg"></a>
    <img alt="GitHub License" src="https://img.shields.io/github/license/DongShaoNB/BedrockPlayerSupport">
    <br>
    <img alt="bStats Players" src="https://img.shields.io/bstats/players/17107">
    <img alt="bStats Servers" src="https://img.shields.io/bstats/servers/17107">
	<br>
    <a href="https://github.com/DongShaoNB/BedrockPlayerSupport/blob/master/README.md">简体中文</a>
     | 
    <a href="https://github.com/DongShaoNB/BedrockPlayerSupport/blob/master/README_TW.md">正體中文</a>
     | 
    <a href="https://github.com/DongShaoNB/BedrockPlayerSupport/blob/master/README_EN.md">English</a></p>

------------------------------

## 功能

 1. 基岩版玩家收到 `tpa/tpahere` 申请时弹出表单选择同意/拒绝/忽略
 2. 基岩版玩家使用指令 `/tpgui` 弹出表单选择要 tpa (tpahere) 的玩家 
 3. 基岩版玩家进服注册时自动使用随机密码注册
 4. 基岩版玩家进服登录时直接跳过玩家登录
 5. 基岩版玩家使用指令 `/msggui` 弹出表单发送信息
 6. 基岩版玩家使用指令 `/homegui` 弹出表单传送回家
 7. 基岩版玩家重生后弹出返回死亡点表单
 8. 基岩版玩家使用指令 `/warpgui` 弹出表单传送到传送点

## 前置

[floodgate][floodgate-download]

## 安装

直接将插件放进 `plugins` 文件夹，修改配置文件即可  

**Tip: Geyser 需要按照 [floodgate文档][floodgate-setup] 设置 `auth-type` 为 `floodgate`**  
**如果你使用了代理服务器(BungeeCord/Velocity)，你还需要将代理服务器的 floodgate 配置文件中的 `send-floodgate-data` 设置为 `true`**

### 链接

- [在线文档][docs]
- [Crowdin][crowdin]

## 社区

QQ群: `159323818`

Discord: [https://discord.gg/bnpzsmPz26][discord]

## 鸣谢
鸣谢 [JetBrains 开源许可][jetbrains-oss]  
![JetBrains Logo (Main) logo](https://resources.jetbrains.com/storage/products/company/brand/logos/jb_beam.svg)

[floodgate-setup]: https://wiki.geysermc.org/floodgate/setup/
[floodgate-download]: https://geysermc.org/download#floodgate
[docs]: https://docs.bps.dsnb.cc
[crowdin]: https://zh.crowdin.com/project/mcbps
[discord]: https://discord.gg/bnpzsmPz26
[jetbrains-oss]: https://jb.gg/OpenSourceSupport
