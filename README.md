<div align="center">
  <font size="6"><b>BedrockPlayerSupport</b></font><br>
  <font size="4"><b>让其他插件对基岩版玩家更加友好</b></font>
</div>

<p align="center">
  <img alt="GitHub Repo stars" src="https://img.shields.io/github/stars/DongShaoNB/BedrockPlayerSupport">
    <img alt="GitHub Downloads (all assets, all releases)" src="https://img.shields.io/github/downloads/DongShaoNB/BedrockPlayerSupport/total">
    <img alt="GitHub Release" src="https://img.shields.io/github/v/release/DongShaoNB/BedrockPlayerSupport">
    <a title="Crowdin" target="_blank" href="https://crowdin.com/project/mcbps"><img src="https://badges.crowdin.net/mcbps/localized.svg"></a>
    <img alt="GitHub License" src="https://img.shields.io/github/license/DongShaoNB/BedrockPlayerSupport">
    <br>
    <img src="https://bstats.org/signatures/bukkit/BedrockPlayerSupport.svg">
</p>

<p align="center">
  <a href="https://github.com/DongShaoNB/BedrockPlayerSupport/blob/master/README.md">简体中文</a> |
  <a href="https://github.com/DongShaoNB/BedrockPlayerSupport/blob/master/README_TW.md">正體中文</a> |
  <a href="https://github.com/DongShaoNB/BedrockPlayerSupport/blob/master/README_EN.md">English</a>
</p>

---

## 📖 介绍

**BedrockPlayerSupport** 是一款专为 Bukkit/Spigot 服务器设计的插件，旨在提升 Minecraft Java 版服务器中基岩版玩家的体验，为常用命令和功能提供基于 GUI 的界面，使习惯于触控和表单界面的基岩版玩家能够更加直观地进行交互，从而弥合 Java 版与基岩版之间的操作差异。

---

## ✨ 功能特性

### 📦 传送相关

- `/tpgui`：打开传送请求 GUI，选择要传送的玩家。
- `/warpgui`：打开传送点 GUI，选择目标传送点进行传送。
- 收到 `tpa/tpahere` 请求时，基岩版玩家将看到表单选择 同意 / 拒绝 / 忽略。

### 🏠 家相关

- `/homegui`：打开个人家列表 GUI，快速传送回家。
- `/phomegui`：打开公共家列表 GUI，访问其他玩家的公共家。

### 💬 消息相关

- `/msggui`：打开消息发送 GUI，方便与其他玩家交流。

### 🎁 其他功能

- `/kitgui`：打开礼包领取 GUI。
- 基岩版玩家进服时自动使用随机密码注册并自动登录。
- 玩家死亡后自动弹出返回死亡点的表单。

---

## ⚙️ 安装与配置

### 📥 安装步骤

1. 下载插件并放入服务器的 `plugins` 文件夹。
2. 重启服务器以生成默认配置文件。
3. 配置文件内注释十分完整，根据需要修改配置文件。

### 🔗 前置依赖

- 服务端：支持 Paper 及其分支 / Folia 及其分支。
- 插件：需安装 [Floodgate](https://geysermc.org/download#floodgate)。

> **注意：** 使用 Geyser 时，请确保在其配置文件中将 `auth-type` 设置为 `floodgate`。
>
> 如果使用代理服务器（如 BungeeCord 或 Velocity），请在代理服务器的 Floodgate 配置文件中将 `send-floodgate-data` 设置为 `true`。

---

## 📚 文档与支持

- 📖 [在线文档](https://docs.bps.dsnb.cc)：表单图片，插件兼容性表格，命令和权限。
- 🌐 [Crowdin 翻译平台](https://zh.crowdin.com/project/mcbps)：参与插件的本地化翻译工作。

---

## 👥 社区与交流

- 💬 QQ 群：`159323818`
- 💬 Discord： [加入我们的 Discord 服务器](https://discord.gg/bnpzsmPz26)

欢迎加入社区，反馈问题、提出建议或参与开发！

---

## 🙏 鸣谢

特别感谢 [JetBrains](https://jb.gg/OpenSourceSupport) 提供的开源许可证支持。

<p align="left">
  <img src="https://resources.jetbrains.com/storage/products/company/brand/logos/jb_beam.svg" alt="JetBrains Logo" width="150">
</p>