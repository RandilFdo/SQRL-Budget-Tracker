# 🐿️ SQRL Budget Tracker

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![Made with Kotlin](https://img.shields.io/badge/Kotlin-100%25-purple.svg)](https://kotlinlang.org/)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-100%25-blue.svg)](https://developer.android.com/jetpack/compose)

**SQRL Budget Tracker** is a free and open-source money management Android app built entirely with **Kotlin** and **Jetpack Compose**. Track your expenses, manage budgets, and take control of your personal finances with an intuitive and modern interface.

> 🎯 **Mission**: Replace spreadsheets with a beautiful, simple, and powerful mobile finance tracker.

---

## ✨ Features

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

---

## 🎤 Voice Input Feature (NEW!)

One of the standout features of SQRL Budget Tracker is the **Voice Input** capability for transactions. Simply speak your transaction details, and the app will automatically parse and fill in the information.

### How It Works
1. Tap the microphone button on the transaction screen
2. Say your transaction: *"I spent 25 dollars on lunch today"*
3. Watch as the app automatically fills:
   - **Amount**: $25
   - **Date**: Today
   - **Category**: Food
   - **Description**: "lunch"

### Supported Voice Patterns
- **Amounts**: "500 dollars", "25.50", "2000 euros"
- **Dates**: "today", "yesterday", "tomorrow", "Monday"
- **Categories**: Recognizes keywords like "food", "transport", "rent", "shopping", etc.

📚 [Learn more about Voice Input →](feature/edit-transaction/VOICE_INPUT_README.md)

---

## 📱 Screenshots

| Home Screen | Transactions | Voice Input | Reports |
|:----------:|:-----------:|:-----------:|:-------:|
| *Coming soon* | *Coming soon* | *Coming soon* | *Coming soon* |

---

## 🚀 Getting Started

### Prerequisites
- **Java 17** or higher
- **Android Studio** (Latest stable version recommended)
  - [Download via JetBrains Toolbox](https://www.jetbrains.com/toolbox-app/)

### Installation

#### Option 1: Build from Source
```bash
# Clone the repository
git clone https://github.com/RandilFdo/SQRL-Budget-Tracker.git
cd SQRL-Budget-Tracker

# Open in Android Studio
# Build → Make Project
# Run → Run 'app'
```

#### Option 2: Download APK
Check the [Releases](https://github.com/RandilFdo/SQRL-Budget-Tracker/releases) page for the latest APK.

---

## 🛠️ Tech Stack

### Core Technologies
- **[Kotlin](https://kotlinlang.org/)** - 100% Kotlin
- **[Jetpack Compose](https://developer.android.com/jetpack/compose)** - Modern UI toolkit
- **[Material Design 3](https://m3.material.io/)** - Beautiful UI components
- **[Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)** - Structured concurrency
- **[Kotlin Flow](https://kotlinlang.org/docs/flow.html)** - Reactive data streams
- **[Hilt](https://dagger.dev/hilt/)** - Dependency injection
- **[ArrowKt](https://arrow-kt.io/)** - Functional programming

### Data & Persistence
- **[Room Database](https://developer.android.com/training/data-storage/room)** - SQLite ORM
- **[DataStore](https://developer.android.com/topic/libraries/architecture/datastore)** - Key-value storage

### Testing
- **[JUnit4](https://github.com/junit-team/junit4)** - Test framework
- **[Kotest](https://kotest.io/)** - Assertions library
- **[Paparazzi](https://github.com/cashapp/paparazzi)** - Screenshot testing

### Networking & Serialization
- **[Ktor Client](https://ktor.io/)** - HTTP client for exchange rates
- **[Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization)** - JSON parsing

### Quality & CI/CD
- **[Gradle KTS](https://docs.gradle.org/current/userguide/kotlin_dsl.html)** - Build system
- **[Detekt](https://github.com/detekt/detekt)** - Code analysis
- **[Ktlint](https://github.com/pinterest/ktlint)** - Code formatting
- **[GitHub Actions](https://github.com/features/actions)** - Continuous integration

### Other
- **[Firebase Crashlytics](https://firebase.google.com/products/crashlytics)** - Crash reporting
- **[Timber](https://github.com/JakeWharton/timber)** - Logging

---

## 🏗️ Architecture

SQRL Budget Tracker follows modern Android development best practices:

- **Clean Architecture** - Separation of concerns with clear boundaries
- **MVVM Pattern** - Model-View-ViewModel for UI logic
- **Modularization** - Feature-based module organization
- **Unidirectional Data Flow** - Predictable state management
- **Repository Pattern** - Abstract data sources

📚 [Read the Architecture Guidelines →](docs/Guidelines.md)

---

## 🤝 Contributing

We welcome contributions! Whether it's bug fixes, new features, or improvements to documentation.

### How to Contribute
1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/amazing-feature`)
3. **Commit** your changes (`git commit -m 'Add amazing feature'`)
4. **Push** to the branch (`git push origin feature/amazing-feature`)
5. **Open** a Pull Request

📋 [Read the Contributing Guidelines →](CONTRIBUTING.md)

### Development Setup
```bash
# Clone your fork
git clone https://github.com/YOUR_USERNAME/SQRL-Budget-Tracker.git

# Add upstream remote
git remote add upstream https://github.com/RandilFdo/SQRL-Budget-Tracker.git

# Create a branch
git checkout -b feature/my-feature

# Make changes and commit
git add .
git commit -m "Description of changes"

# Push and create PR
git push origin feature/my-feature
```

---

## 📖 Documentation

- **[Build Guide](BUILD_GUIDE.md)** - Detailed build instructions
- **[Architecture Guidelines](docs/Guidelines.md)** - Technical architecture
- **[Voice Input Documentation](feature/edit-transaction/VOICE_INPUT_README.md)** - Voice feature details
- **[Contributing Guide](CONTRIBUTING.md)** - How to contribute

---

## 🐛 Bug Reports & Feature Requests

Found a bug or have a feature idea? We'd love to hear from you!

- **[Report a Bug](https://github.com/RandilFdo/SQRL-Budget-Tracker/issues/new?labels=bug)**
- **[Request a Feature](https://github.com/RandilFdo/SQRL-Budget-Tracker/issues/new?labels=enhancement)**

---

## 📋 Roadmap

### Upcoming Features
- [ ] 📱 Google Play Store release
- [ ] 🌐 Multi-language support
- [ ] 📊 Advanced analytics dashboard
- [ ] 🔔 Smart spending notifications
- [ ] 🤖 AI-powered expense categorization
- [ ] ☁️ Cloud sync and backup
- [ ] 👥 Shared budgets and accounts
- [ ] 📸 Receipt scanning with OCR
- [ ] 📈 Investment tracking
- [ ] 🎯 Financial goals and milestones

Have suggestions? [Open an issue](https://github.com/RandilFdo/SQRL-Budget-Tracker/issues/new?labels=enhancement) to let us know!

---

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

## 🙏 Acknowledgments

This project is built upon the foundation of [Ivy Wallet](https://github.com/Ivy-Apps/ivy-wallet), an excellent open-source money manager. We're grateful to the original developers and contributors for their work.

**Key differences from Ivy Wallet:**
- ✨ Added voice input for transactions
- 🎨 Enhanced UI with SQRL branding
- 🐿️ Themed around the SQRL mascot
- 🚀 Ongoing active development
- 🔧 Custom features and improvements

---

## ⭐ Show Your Support

If you find SQRL Budget Tracker helpful, please consider:
- ⭐ **Starring** the repository
- 🐛 **Reporting bugs** you encounter
- 💡 **Suggesting features** you'd like to see
- 🤝 **Contributing** code or documentation
- 📢 **Sharing** with friends and family

---

## 📧 Contact

**Randil Fernando**
- GitHub: [@RandilFdo](https://github.com/RandilFdo)
- Email: randilfernando829@gmail.com

---

<div align="center">

**Made with ❤️ and Kotlin**

*Saving one transaction at a time* 🐿️💰

[⬆ Back to Top](#-sqrl-budget-tracker)

</div>
