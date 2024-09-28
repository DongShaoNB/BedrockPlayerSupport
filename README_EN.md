<p align="center" style="font-size:38px">BedrockPlayerSupport</p>
<p align="center"><b>Make it easier for bedrock player to use other plugins</b></p>
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

## Feature

 1. When bedrock player receive a request for `tpa/tpahere`, open a form to select agree/reject/ignore
 2. Bedrock player use the command `/tpgui` to open a form and select the player who wants tpa (tpahere)
 3. Bedrock player will automatically use a random password to register when entering the server
 4. When bedrock player log in to the server, they can skip login directly
 5. Bedrock player use the command `/msggui` to open a form and send message
 6. Bedrock player use the command `/homegui` to open a form and get home
 7. After bedrock player respawn, a form open to return to the last death location
 8. Bedrock player use the command `/warpgui` to open a form and teleport to a warp
 9. Bedrock player use the command `/kitgui` to open a kit form

## Depend

[floodgate][floodgate-download]

## Install

Simply put the plugin into the `plugins` folder and modify the configuration file    

**Tip: `auth-type` in geyser config need to set to `floodgate` according to [floodgate documentation][floodgate-setup]**  
**If you use proxy server(BungeeCord/Velocity), you need to set `send-floodgate-data` to `true` in proxy server floodgate config**

### Link

- [Docs][docs]
- [Crowdin][crowdin]

## Community

QQ Group: `159323818`

Discord: [https://discord.gg/bnpzsmPz26][discord]

## Thanks
Thanks for [JetBrains: Support for Open-Source Projects][jetbrains-oss]   
![JetBrains Logo (Main) logo](https://resources.jetbrains.com/storage/products/company/brand/logos/jb_beam.svg)

[floodgate-setup]: https://wiki.geysermc.org/floodgate/setup/
[floodgate-download]: https://geysermc.org/download#floodgate
[docs]: https://docs.bps.dsnb.cc
[crowdin]: https://zh.crowdin.com/project/mcbps
[discord]: https://discord.gg/bnpzsmPz26
[jetbrains-oss]: https://jb.gg/OpenSourceSupport
