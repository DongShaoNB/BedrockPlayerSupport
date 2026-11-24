<div align="center">
  <h1>BedrockPlayerSupport</h1>
  <p><strong>Make it easier for Bedrock players to use Java server plugins</strong></p>

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
    <a href="https://github.com/DongShaoNB/BedrockPlayerSupport/blob/master/README.md">简体中文</a> |
    <a href="https://github.com/DongShaoNB/BedrockPlayerSupport/blob/master/README_TW.md">正體中文</a> |
    <strong>English</strong>
  </p>
</div>

---

## 📖 Introduction

**BedrockPlayerSupport** enhances the experience of Bedrock Edition players on Java crossplay servers running **Geyser & Floodgate**. Instead of typing complex chat commands, Bedrock players get native touch-friendly Bedrock Forms (GUIs) for everyday features—bridging the gap between Java and Bedrock gameplay.

---

## ✨ Features

### 📦 Teleportation

- **`/tpgui`** — Open a form to select a player and send teleport requests.
- **`/warpgui`** — Browse and teleport to server warps through a clean GUI.
- **Interactive TPA Prompts** — Incoming `/tpa` and `/tpahere` requests automatically pop up a form for Bedrock players to **Accept**, **Reject**, or **Ignore**.

### 🏠 Homes

- **`/homegui`** — View and teleport to your personal homes.
- **`/phomegui`** — Browse and visit public homes shared by other players.

### 💬 Messaging

- **`/msggui`** — Send private messages to other players via a simple form interface.

### 🎁 Quality of Life

- **`/kitgui`** — Claim available kits from a form menu.
- **Auto Register & Login** — Automatically registers new Bedrock players with a random password and logs them in seamlessly on join.
- **Death Return Prompt** — Automatically opens a form after respawning so players can quickly return to their death location.

---

## ⚙️ Requirements & Installation

### 🔗 Requirements

- **Server:** Paper (and its forks) or Folia (and its forks).
- **Dependency:** [Floodgate](https://geysermc.org/download#floodgate) is required.

> [!IMPORTANT]
>
> - In your **Geyser** config, make sure `auth-type` is set to `floodgate`.
> - If you run a proxy (such as **Velocity** or **BungeeCord**), set `send-floodgate-data` to `true` in your proxy's Floodgate config.

### 📥 Installation

1. Download the plugin `.jar` and place it into your server's `plugins` folder.
2. Restart the server to generate the default configuration files.
3. Customize the config as needed (detailed comments are included in the file).

---

## 📚 Documentation & Community

- 📖 **Documentation:** [docs.bps.dsnb.cc](https://docs.bps.dsnb.cc) — Form previews, supported plugins list, commands, and permissions.
- 🌐 **Translations:** [Crowdin](https://crowdin.com/project/mcbps) — Help translate the plugin into your language.
- 💬 **Discord:** [Join our Discord server](https://discord.gg/bnpzsmPz26)
- 💬 **QQ Group:** `159323818`

Feedback, feature requests, and contributions are always welcome!

---

## 🚀 Server Hosting _(Affiliate)_

<div align="center">
  <a href="https://realisticnode.com/bps" target="_blank">
    <img src="https://cdn.modrinth.com/data/cached_images/e3096eacdfbee0e214e8fccb389a96d302162e87.png" alt="RealisticNode Hosting - Use code BPS for 10% off">
  </a>
  <p>
    Looking for a server host for your server?<br>
    Order through <a href="https://realisticnode.com/bps" target="_blank"><strong>this affiliate link</strong> for <strong>10% off</strong> while supporting the development of BedrockPlayerSupport!
  </p>
</div>

---

## 🙏 Acknowledgments

Special thanks to [JetBrains](https://jb.gg/OpenSourceSupport) for providing open-source license support.

<p align="left">
  <img src="https://resources.jetbrains.com/storage/products/company/brand/logos/jb_beam.svg" alt="JetBrains Logo" width="150">
</p>
