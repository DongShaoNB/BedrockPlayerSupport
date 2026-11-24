<div align="center">
  <h1>BedrockPlayerSupport</h1>
  <p><strong>让其他插件对基岩版玩家更加友好</strong></p>

  <p>
    <img alt="GitHub Repo stars" src="https://img.shields.io/github/stars/DongShaoNB/BedrockPlayerSupport">
    <img alt="GitHub Downloads" src="https://img.shields.io/github/downloads/DongShaoNB/BedrockPlayerSupport/total">
    <img alt="GitHub Release" src="https://img.shields.io/github/v/release/DongShaoNB/BedrockPlayerSupport">
    <a title="Crowdin" target="_blank" href="https://crowdin.com/project/mcbps"><img alt="Crowdin" src="https://badges.crowdin.net/mcbps/localized.svg"></a>
    <img alt="GitHub License" src="https://img.shields.io/github/license/DongShaoNB/BedrockPlayerSupport">
  </p>

  <p>
    <img alt="bStats" src="https://bstats.org/signatures/bukkit/BedrockPlayerSupport.svg">
  </p>

  <p>
    <strong>简体中文</strong> |
    <a href="https://github.com/DongShaoNB/BedrockPlayerSupport/blob/master/README_TW.md">正體中文</a> |
    <a href="https://github.com/DongShaoNB/BedrockPlayerSupport/blob/master/README_EN.md">English</a>
  </p>
</div>

---

## 📖 简介

**BedrockPlayerSupport** 专为使用 **Geyser & Floodgate** 的 Java 版互通服务器打造。它为常用插件的命令和功能提供原生的基岩版表单（GUI）界面，让习惯触控操作的基岩版玩家无需手动输入复杂指令，轻松弥合 Java 版与基岩版之间的交互体验差异。

---

## ✨ 功能特性

### 📦 传送相关
- **`/tpgui`** — 打开传送请求表单，直接选择目标玩家发送传送请求。
- **`/warpgui`** — 打开传送点列表表单，快速传送至目标地标。
- **交互式传送弹窗** — 收到 `/tpa` 或 `/tpahere` 请求时，自动向基岩版玩家弹出表单，支持一键 **同意 / 拒绝 / 忽略**。

### 🏠 家相关
- **`/homegui`** — 打开个人家列表表单，快速传送回家。
- **`/phomegui`** — 浏览并访问其他玩家分享的公共家。

### 💬 消息相关
- **`/msggui`** — 通过表单快捷向其他玩家发送私信。

### 🎁 其他功能
- **`/kitgui`** — 打开礼包领取表单。
- **自动注册与登录** — 基岩版玩家进服时自动使用随机密码完成注册并自动登录。
- **死亡返回提示** — 玩家死亡重生后自动弹出表单，方便快速返回死亡点。

---

## ⚙️ 环境要求与安装

### 🔗 前置依赖
- **服务端**：支持 Paper 及其分支 / Folia 及其分支。
- **前置插件**：需安装 [Floodgate](https://geysermc.org/download#floodgate)。

> [!IMPORTANT]
> - 使用 **Geyser** 时，请确保其配置文件中的 `auth-type` 已设置为 `floodgate`。
> - 若使用代理端（如 **Velocity** 或 **BungeeCord**），请在代理端的 Floodgate 配置文件中将 `send-floodgate-data` 设置为 `true`。

### 📥 安装步骤
1. 下载插件 `.jar` 文件并放入服务端的 `plugins` 文件夹。
2. 重启服务器以生成默认配置文件。
3. 配置文件内附有完整注释，按需修改即可。

---

## 📚 文档与社区

- 📖 **在线文档**：[docs.bps.dsnb.cc](https://docs.bps.dsnb.cc) — 表单预览图、插件兼容性列表、命令与权限说明。
- 🌐 **翻译贡献**：[Crowdin 翻译平台](https://zh.crowdin.com/project/mcbps) — 欢迎参与插件的本地化翻译。
- 💬 **QQ 群**：`159323818`
- 💬 **Discord**：[加入我们的 Discord 服务器](https://discord.gg/bnpzsmPz26)

欢迎加入社区反馈问题、提出功能建议或参与项目开发！

---

## 🌧️ 服务器推荐（广告）

对当前使用的服务器不满？正在寻找高性价比云服务商？来看看新一代云服务提供商 —— [**雨云**](https://www.rainyun.com/panel_?s=github-bps-readme)！  
支持 **1 元试用 1 天**、**7 天无理由退款**，资质齐全，稳定省心，快来[**点击体验**](https://www.rainyun.com/panel_?s=github-bps-readme)吧！

---

## 🙏 鸣谢

特别感谢 [JetBrains](https://jb.gg/OpenSourceSupport) 提供的开源许可证支持。

<p align="left">
  <img src="https://resources.jetbrains.com/storage/products/company/brand/logos/jb_beam.svg" alt="JetBrains Logo" width="150">
</p>