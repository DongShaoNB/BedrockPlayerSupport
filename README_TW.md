<div align="center">
  <h1>BedrockPlayerSupport</h1>
  <p><strong>讓其他插件對基岩版玩家更加友善</strong></p>

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
    <a href="https://github.com/DongShaoNB/BedrockPlayerSupport/blob/master/README.md">簡體中文</a> |
    <strong>正體中文</strong> |
    <a href="https://github.com/DongShaoNB/BedrockPlayerSupport/blob/master/README_EN.md">English</a>
  </p>
</div>

---

## 📖 簡介

**BedrockPlayerSupport** 專為使用 **Geyser 與 Floodgate** 的 Java 版互通伺服器設計。本插件為常用指令與功能提供原生的基岩版表單（GUI）介面，讓習慣觸控操作的基岩版玩家無須手動輸入繁瑣指令，有效縮短 Java 版與基岩版之間的操作體驗落差。

---

## ✨ 功能特色

### 📦 傳送相關

- **`/tpgui`** — 開啟傳送請求表單，直接點選目標玩家發送傳送請求。
- **`/warpgui`** — 開啟傳送點列表表單，快速傳送至指定地標。
- **互動式傳送視窗** — 收到 `/tpa` 或 `/tpahere` 請求時，自動向基岩版玩家彈出表單，可一鍵選擇 **同意 / 拒絕 / 忽略**。

### 🏠 家相關

- **`/homegui`** — 開啟個人家列表表單，快速傳送回家。
- **`/phomegui`** — 瀏覽並造訪其他玩家分享的公共家。

### 💬 訊息相關

- **`/msggui`** — 透過表單介面輕鬆向其他玩家發送私訊。

### 🎁 其他功能

- **`/kitgui`** — 開啟禮包領取表單。
- **自動註冊與登入** — 基岩版玩家進入伺服器時自動以隨機密碼註冊並完成登入。
- **死亡返回提示** — 玩家死亡重生後自動彈出表單，方便快速返回死亡地點。

---

## ⚙️ 環境需求與安裝

### 🔗 前置需求

- **伺服器端**：支援 Paper 及其分支 / Folia 及其分支。
- **前置插件**：需安裝 [Floodgate](https://geysermc.org/download#floodgate)。

> [!IMPORTANT]
>
> - 使用 **Geyser** 時，請確認設定檔中的 `auth-type` 已設為 `floodgate`。
> - 若搭配代理伺服器（如 **Velocity** 或 **BungeeCord**），請在代理端的 Floodgate 設定檔中將 `send-floodgate-data` 設為 `true`。

### 📥 安裝步驟

1. 下載插件 `.jar` 檔案並放入伺服器的 `plugins` 資料夾。
2. 重新啟動伺服器以產生預設設定檔。
3. 設定檔內均附有詳盡註解，可依需求自行調整。

---

## 📚 文件與社群

- 📖 **線上文件**：[docs.bps.dsnb.cc](https://docs.bps.dsnb.cc) — 表單預覽圖、插件相容性列表、指令與權限說明。
- 🌐 **翻譯協助**：[Crowdin 翻譯平台](https://crowdin.com/project/mcbps) — 歡迎參與插件的在地化翻譯。
- 💬 **Discord**：[加入我們的 Discord 伺服器](https://discord.gg/bnpzsmPz26)
- 💬 **QQ 群**：`159323818`

歡迎加入社群回饋問題、提出功能建議或一同參與開發！

---

## 🙏 鳴謝

特別感謝 [JetBrains](https://jb.gg/OpenSourceSupport) 提供的開源授權支援。

<p align="left">
  <img src="https://resources.jetbrains.com/storage/products/company/brand/logos/jb_beam.svg" alt="JetBrains Logo" width="150">
</p>
