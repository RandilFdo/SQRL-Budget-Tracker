[![Latest Release](https://img.shields.io/github/v/release/RandilFdo/SQRL-Budget-Tracker)](https://github.com/RandilFdo/SQRL-Budget-Tracker/releases)
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![GitHub Repo stars](https://img.shields.io/github/stars/RandilFdo/SQRL-Budget-Tracker?style=social)](https://github.com/RandilFdo/SQRL-Budget-Tracker/stargazers)
[![Fork SQRL Budget Tracker](https://img.shields.io/github/forks/RandilFdo/SQRL-Budget-Tracker?logo=github&style=social)](https://github.com/RandilFdo/SQRL-Budget-Tracker/fork)

# [SQRL Budget Tracker: money manager](https://github.com/RandilFdo/SQRL-Budget-Tracker)

|                                                                                                            |                                                                                                            |                                                                                                            |                                                                                                            |
|:----------------------------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------------------:|
| ![1](https://user-images.githubusercontent.com/5564499/189540998-4d6cdcd3-ab4d-40f7-85d4-c82fe8a017d1.png) | ![2](https://user-images.githubusercontent.com/5564499/189541011-1ebbd8b6-50fe-432a-91e2-59206efe99ce.png) | ![3](https://user-images.githubusercontent.com/5564499/189541023-35e7f163-d639-4466-9a91-c56890d5a28e.png) | ![4](https://user-images.githubusercontent.com/5564499/189541027-d352314c-fd5c-43eb-82ad-4aba14c7b0fa.png) |
| ![5](https://user-images.githubusercontent.com/5564499/189541030-1a0d7948-33af-420b-b126-936d0211c93f.png) | ![6](https://user-images.githubusercontent.com/5564499/189541035-621c4511-5ec7-4d3f-b08e-925d8da95472.png) | ![7](https://user-images.githubusercontent.com/5564499/189541127-7adf5bfa-0652-461c-80f1-076b7179eb6c.png) | ![8](https://user-images.githubusercontent.com/5564499/189541040-7cab633e-be4c-40b2-a2c6-890a15edf805.png) |

SQRL Budget Tracker is a free and open source **money management android app**. It's written using **100% Kotlin and Jetpack Compose**. It's designed to help you keep track of your personal finances with ease.

Think of SQRL Budget Tracker as a manual expense tracker that tries to replace the good old spreadsheet for managing your finances.

**Do you know? Ask yourself.**

1) How much money do I have in total?

2) How much did I spend this month and what did I spend it on?

3) How much can I spend and still meet my financial goals?

A money management app can help you answer these questions.

SQRL Budget Tracker features a beautiful user interface and experience, simplicity, and extensive customization options. Built on the foundation of Ivy Wallet, SQRL adds new features like **voice input for transactions** and continues active development.

## 🎤 Voice Input Feature (NEW!)

One of the standout features of SQRL Budget Tracker is the **Voice Input** capability for transactions. Simply speak your transaction details, and the app will automatically parse and fill in the information.

### How It Works
- Tap the microphone button on the transaction screen
- Say your transaction: *"I spent 25 dollars on lunch today"*
- Watch as the app automatically fills: Amount ($25), Date (Today), Category (Food), and Description

📚 [Learn more about Voice Input →](feature/edit-transaction/VOICE_INPUT_README.md)

> To support this free open source project, please give it a star. ⭐
> This means a lot to us. Thank you so much! [![GitHub Repo stars](https://img.shields.io/github/stars/RandilFdo/SQRL-Budget-Tracker?style=social)](https://github.com/RandilFdo/SQRL-Budget-Tracker/stargazers)

## Project Requirements

- Java 17+
- The **latest stable** Android Studio (for easy install use [JetBrains Toolbox](https://www.jetbrains.com/toolbox-app/))

### Initialize the project

**1. Fork and clone the repo**

Instructions in [CONTRIBUTING.md](./CONTRIBUTING.md).

### Need help?

Open an issue or discussion on GitHub and we'll be happy to help!

## Learning Materials

SQRL Budget Tracker is a great place to code and learn. Check out great learning materials (books, articles, videos) in **[docs/resources 📚](docs/resources/)**.

Make sure to check out our short **[Developer Guidelines 🏗️](docs/Guidelines.md)** to learn more about the technical side of SQRL Budget Tracker.

## Tech Stack

### Core

- 100% [Kotlin](https://kotlinlang.org/)
- 100% [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Material3 design](https://m3.material.io/) (UI components)
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) (structured concurrency)
- [Kotlin Flow](https://kotlinlang.org/docs/flow.html) (reactive data stream)
- [Hilt](https://dagger.dev/hilt/) (DI)
- [ArrowKt](https://arrow-kt.io/) (functional programming)


### Testing
- [JUnit4](https://github.com/junit-team/junit4) (test framework, compatible with Android)
- [Kotest](https://kotest.io/) (unit test assertions)
- [Paparazzi](https://github.com/cashapp/paparazzi) (screenshot testing)

### Local Persistence
- [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) (key-value storage)
- [Room DB](https://developer.android.com/training/data-storage/room) (SQLite ORM)

### Networking
- [Ktor client](https://ktor.io/docs/getting-started-ktor-client.html) (HTTP client)
- [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization) (JSON serialization)

### Build & CI
- [Gradle KTS](https://docs.gradle.org/current/userguide/kotlin_dsl.html) (Kotlin DSL)
- [Gradle convention plugins](https://docs.gradle.org/current/samples/sample_convention_plugins.html) (build logic)
- [Gradle version catalogs](https://developer.android.com/build/migrate-to-catalogs) (dependencies versions)
- [GitHub Actions](https://github.com/RandilFdo/SQRL-Budget-Tracker/actions) (CI/CD)

### Other
- [Firebase Crashlytics](https://firebase.google.com/products/crashlytics) (stability monitoring)
- [Timber](https://github.com/JakeWharton/timber) (logging)
- [Detekt](https://github.com/detekt/detekt) (linter)
- [Ktlint](https://github.com/pinterest/ktlint) (linter)
- [Slack's compose-lints](https://slackhq.github.io/compose-lints/) (linter)

## Features

### 💰 Core Finance Tracking
- **📊 Transaction Management**: Track income and expenses effortlessly
- **🎤 Voice Input**: Speak your transactions instead of typing (NEW!)
- **💳 Multiple Accounts**: Manage checking, savings, credit cards, and more
- **🏷️ Categories**: Organize transactions with customizable categories
- **🔄 Recurring Payments**: Set up automatic tracking for regular transactions
- **📅 Planned Payments**: Schedule and track future payments

### 📈 Budgeting & Analysis
- **💵 Budget Management**: Set and monitor spending limits
- **📊 Reports & Charts**: Visualize spending patterns with beautiful charts
- **🔍 Search & Filter**: Find transactions quickly with advanced search
- **💱 Multi-Currency**: Support for multiple currencies with exchange rates
- **📉 Balance Tracking**: Real-time balance updates across all accounts

### 🎨 User Experience
- **🌈 Material Design 3**: Modern, beautiful, and intuitive interface
- **🌙 Dark Mode**: Easy on the eyes with AMOLED support
- **🎨 Customization**: Personalize categories with colors and icons
- **📲 Widgets**: Quick access from your home screen
- **📤 Import/Export**: CSV import/export for data portability
- **🏆 Loan Tracking**: Manage loans and track payments

## Contribute

**Want to contribute?** See **[CONTRIBUTING.md](/CONTRIBUTING.md)** [![Fork SQRL Budget Tracker](https://img.shields.io/github/forks/RandilFdo/SQRL-Budget-Tracker?logo=github&style=social)](https://github.com/RandilFdo/SQRL-Budget-Tracker/fork)

### Contributors Wall:

<a href="https://github.com/RandilFdo/SQRL-Budget-Tracker/graphs/contributors">
  <img alt="contributors graph" src="https://contrib.rocks/image?repo=RandilFdo/SQRL-Budget-Tracker" />
</a>
<br>
<br>

_Note: It may take up to 24 hours for the [contrib.rocks](https://contrib.rocks/preview?repo=RandilFdo%2FSQRL-Budget-Tracker) plugin to update._ 

**P.S.** You'll also be recognized in a special "Contributors" section. We salute you! 👏

## Creative Contributors

Special recognition for individuals who have contributed to SQRL Budget Tracker in meaningful ways.

### Contributors Wall:

<div align="center">
  <a href="https://github.com/RandilFdo">
    <img src="https://github.com/RandilFdo.png" width="100px;" alt="Randil Fernando"/><br>
    <strong>Randil Fernando</strong><br>
    <small>Creator and lead developer of SQRL Budget Tracker. Implemented the voice input feature and continues active development.</small>
  </a>
</div>

<br>

<!-- Future contributors will be added here -->
<!-- 
<div align="center">
  <a href="GITHUB_URL">
    <img src="GITHUB_AVATAR_URL" width="100px;" alt="NAME"/><br>
    <strong>USERNAME</strong><br>
    <small>CONTRIBUTION_DESCRIPTION</small>
  </a>
</div>
-->

## 🙏 Acknowledgments

This project is built upon the foundation of [Ivy Wallet](https://github.com/Ivy-Apps/ivy-wallet), an excellent open-source money manager. We're grateful to the original developers and contributors for their amazing work.

**Key enhancements in SQRL:**
- ✨ Voice input for transactions
- 🎨 Enhanced UI with SQRL branding
- 🐿️ Themed around the SQRL mascot
- 🚀 Ongoing active development
- 🔧 Custom features and improvements

## 📜 License

This project is licensed under the **GNU General Public License v3.0** - see the [LICENSE](LICENSE) file for details.

```
SQRL Budget Tracker - Personal Finance Management
Copyright (C) 2025  Randil Fernando

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
```

---

<div align="center">

**Made with ❤️ and Kotlin**

*Saving one transaction at a time* 🐿️💰

**Randil Fernando** | [@RandilFdo](https://github.com/RandilFdo)

</div>
