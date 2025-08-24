<div align="center">
  <font size="6"><b>BedrockPlayerSupport</b></font><br>
  <font size="4"><b>讓其他插件對基岩版玩家更加友善</b></font>
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
  <a href="https://github.com/DongShaoNB/BedrockPlayerSupport/blob/master/README.md">簡體中文</a> |
  <a href="https://github.com/DongShaoNB/BedrockPlayerSupport/blob/master/README_TW.md">正體中文</a> |
  <a href="https://github.com/DongShaoNB/BedrockPlayerSupport/blob/master/README_EN.md">English</a>
</p>

---

## 📖 介紹

**BedrockPlayerSupport** 是一款專為 Bukkit/Spigot 伺服器設計的插件，旨在提升 Minecraft Java 版伺服器中基岩版玩家的體驗，為常用指令和功能提供基於 GUI 的介面，使習慣於觸控和表單介面的基岩版玩家能夠更加直觀地進行互動，從而彌合 Java 版與基岩版之間的操作差異。

---

## ✨ 功能特性

### 📦 傳送相關

- `/tpgui`：開啟傳送請求 GUI，選擇要傳送的玩家。
- `/warpgui`：開啟傳送點 GUI，選擇目標傳送點進行傳送。
- 收到 `tpa/tpahere` 請求時，基岩版玩家將看到表單選擇 同意 / 拒絕 / 忽略。

### 🏠 家相關

- `/homegui`：開啟個人家列表 GUI，快速傳送回家。
- `/phomegui`：開啟公共家列表 GUI，訪問其他玩家的公共家。

### 💬 訊息相關

- `/msggui`：開啟訊息發送 GUI，方便與其他玩家交流。

### 🎁 其他功能

- `/kitgui`：開啟禮包領取 GUI。
- 基岩版玩家進服時自動使用隨機密碼註冊並自動登入。
- 玩家死亡後自動彈出返回死亡點的表單。

---

## ⚙️ 安裝與設定

### 📥 安裝步驟

1. 下載插件並放入伺服器的 `plugins` 資料夾。
2. 重啟伺服器以產生預設設定檔。
3. 設定檔內註解十分完整，根據需要修改設定檔。

### 🔗 前置依賴

- 伺服器端：支援 Paper 及其分支 / Folia 及其分支。
- 插件：需安裝 [Floodgate](https://geysermc.org/download#floodgate)。

> **注意：** 使用 Geyser 時，請確保在其設定檔中將 `auth-type` 設定為 `floodgate`。
>
> 如果使用代理伺服器（如 BungeeCord 或 Velocity），請在代理伺服器的 Floodgate 設定檔中將 `send-floodgate-data` 設定為 `true`。

---

## 📚 文件與支援

- 📖 [線上文件](https://docs.bps.dsnb.cc)：表單圖片，插件相容性表格，指令和權限。
- 🌐 [Crowdin 翻譯平台](https://zh.crowdin.com/project/mcbps)：參與插件的在地化翻譯工作。

---

## 👥 社群與交流

- 💬 QQ 群：`159323818`
- 💬 Discord： [加入我們的 Discord 伺服器](https://discord.gg/bnpzsmPz26)

歡迎加入社群，回饋問題、提出建議或參與開發！

---

## 🙏 鳴謝

特別感謝 [JetBrains](https://jb.gg/OpenSourceSupport) 提供的開源授權支援。

<p align="left">
  <img src="https://resources.jetbrains.com/storage/products/company/brand/logos/jb_beam.svg" alt="JetBrains Logo" width="150">
</p>
