# Build Guide for Ivy Wallet with Voice Input Feature

This guide will help you build the APK file for Ivy Wallet with the new voice input feature.

## Prerequisites

1. **Android Studio** - Latest version recommended
2. **Android SDK** - API level 28 or higher
3. **Java Development Kit (JDK)** - Version 17
4. **Git** - For version control

## Setup Instructions

### 1. Configure Android SDK

1. Open Android Studio
2. Go to **File > Settings** (or **Android Studio > Preferences** on macOS)
3. Navigate to **Appearance & Behavior > System Settings > Android SDK**
4. Note the **Android SDK Location** path (e.g., `C:\Users\YourUsername\AppData\Local\Android\Sdk`)

### 2. Create local.properties file

1. Copy the `local.properties.template` file to `local.properties`
2. Update the `sdk.dir` path with your actual Android SDK location:

```properties
# For Windows
sdk.dir=C:\\Users\\YourUsername\\AppData\\Local\\Android\\Sdk

# For macOS
sdk.dir=/Users/YourUsername/Library/Android/sdk

# For Linux
sdk.dir=/home/YourUsername/Android/Sdk
```

### 3. Alternative: Set Environment Variable

You can also set the `ANDROID_HOME` environment variable:

**Windows:**
```cmd
set ANDROID_HOME=C:\Users\YourUsername\AppData\Local\Android\Sdk
```

**macOS/Linux:**
```bash
export ANDROID_HOME=/Users/YourUsername/Library/Android/sdk
```

## Building the APK

### Method 1: Using Android Studio (Recommended)

1. Open the project in Android Studio
2. Wait for Gradle sync to complete
3. Go to **Build > Build Bundle(s) / APK(s) > Build APK(s)**
4. The APK will be generated in `app/build/outputs/apk/debug/`

### Method 2: Using Command Line

1. Open terminal/command prompt in the project root directory
2. Run the following commands:

```bash
# Clean the project
./gradlew clean

# Build debug APK
./gradlew assembleDebug

# Build release APK (requires signing configuration)
./gradlew assembleRelease
```

The APK files will be generated in:
- Debug: `app/build/outputs/apk/debug/app-debug.apk`
- Release: `app/build/outputs/apk/release/app-release.apk`

## Voice Input Feature Verification

After building and installing the APK, you can test the voice input feature:

1. **Open the app** and navigate to the Income or Expense page
2. **Look for the microphone button** - it should appear as a circular button with a microphone icon
3. **Tap the microphone button** to start voice recognition
4. **Speak a transaction** like "I spent 25 dollars on lunch today"
5. **Verify auto-fill** - the amount, date, category, and description fields should be populated automatically

## Testing Voice Input Examples

Try these example phrases:

### Income Examples:
- "Received 2000 salary payment today"
- "Got 500 from freelance work yesterday"
- "Earned 1000 bonus today"

### Expense Examples:
- "Spent 25 dollars on lunch today"
- "Paid 50 for gas yesterday"
- "Bought groceries for 80 dollars"
- "Movie tickets cost 15 dollars"

## Troubleshooting

### Build Issues

1. **SDK not found error:**
   - Ensure `local.properties` file exists with correct SDK path
   - Or set `ANDROID_HOME` environment variable

2. **Gradle sync failed:**
   - Check internet connection
   - Try **File > Invalidate Caches and Restart**

3. **Permission errors:**
   - Ensure you have write permissions in the project directory

### Voice Input Issues

1. **Microphone button not visible:**
   - Check if the feature was properly integrated
   - Verify the app was built with the latest changes

2. **Speech recognition not working:**
   - Grant microphone permission when prompted
   - Ensure Google services are available on the device
   - Test in a quiet environment

3. **Parsing not working correctly:**
   - Speak clearly and use supported keywords
   - Check the documentation for supported phrases

## File Locations

The voice input feature files are located in:
- `feature/edit-transaction/src/main/java/com/ivy/transaction/SpeechParser.kt`
- `feature/edit-transaction/src/main/java/com/ivy/transaction/SpeechRecognizerHelper.kt`
- `feature/edit-transaction/src/main/java/com/ivy/transaction/MicrophoneButton.kt`
- `feature/edit-transaction/src/main/java/com/ivy/transaction/EditTransactionViewModel.kt` (modified)
- `feature/edit-transaction/src/main/java/com/ivy/transaction/EditTransactionScreen.kt` (modified)

## Additional Notes

- The voice input feature requires Android API level 28 or higher
- Microphone permission (`RECORD_AUDIO`) is automatically requested
- The feature works offline for speech recognition (using device's built-in capabilities)
- All parsing is done locally without external API calls

## Support

If you encounter any issues:
1. Check the Android logs for error messages
2. Verify all prerequisites are installed
3. Ensure the project structure is intact
4. Try building a clean version of the project
