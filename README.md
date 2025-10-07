[![Latest Release](https://img.shields.io/github/v/release/RandilFdo/SQRL-Budget-Tracker)](https://github.com/RandilFdo/SQRL-Budget-Tracker/releases)
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![GitHub Repo stars](https://img.shields.io/github/stars/RandilFdo/SQRL-Budget-Tracker?style=social)](https://github.com/RandilFdo/SQRL-Budget-Tracker/stargazers)
[![Fork SQRL Budget Tracker](https://img.shields.io/github/forks/RandilFdo/SQRL-Budget-Tracker?logo=github&style=social)](https://github.com/RandilFdo/SQRL-Budget-Tracker/fork)

# [SQRL Budget Tracker: money manager](https://github.com/RandilFdo/SQRL-Budget-Tracker)

|                                                                                                            |                                                                                                            |                                                                                                            |
|:----------------------------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------------------:|
| ![1](coverimages/Screenshot%202025-09-04%20140401.png) | ![2](coverimages/WhatsApp%20Image%202025-09-04%20at%2013.15.04_2d101216.jpg) | ![3](coverimages/WhatsApp%20Image%202025-09-04%20at%2013.15.04_a39bd6b7.jpg) |
| ![4](coverimages/WhatsApp%20Image%202025-09-04%20at%2013.15.02_482deecc.jpg) | ![5](coverimages/WhatsApp%20Image%202025-09-04%20at%2013.15.00_2393eae9.jpg) | ![6](coverimages/WhatsApp%20Image%202025-09-04%20at%2013.15.01_1bfe2974.jpg) |

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
