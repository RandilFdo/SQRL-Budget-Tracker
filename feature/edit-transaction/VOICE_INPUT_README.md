# Voice Input Feature for Ivy Wallet

This document describes the voice input feature implementation for the Income and Expense pages in Ivy Wallet.

## Overview

The voice input feature allows users to speak their transaction details instead of manually typing them. The system uses Android's built-in SpeechRecognizer API to capture voice input and automatically parses it to extract structured data.

## Components

### 1. SpeechParser.kt
- **Location**: `feature/edit-transaction/src/main/java/com/ivy/transaction/SpeechParser.kt`
- **Purpose**: Parses speech input and extracts structured transaction data
- **Features**:
  - Amount detection using regex patterns
  - Date parsing (relative and absolute formats)
  - Category matching using predefined keywords
  - Description extraction from remaining text

### 2. SpeechRecognizerHelper.kt
- **Location**: `feature/edit-transaction/src/main/java/com/ivy/transaction/SpeechRecognizerHelper.kt`
- **Purpose**: Handles speech recognition using Android's SpeechRecognizer API
- **Features**:
  - Permission checking
  - Coroutine-based Flow interface
  - Comprehensive error handling
  - Language support

### 3. MicrophoneButton.kt
- **Location**: `feature/edit-transaction/src/main/java/com/ivy/transaction/MicrophoneButton.kt`
- **Purpose**: UI component for the microphone button
- **Features**:
  - Material Design 3 styling
  - Accessibility support
  - Enabled/disabled states

### 4. Integration with EditTransactionViewModel
- **Location**: `feature/edit-transaction/src/main/java/com/ivy/transaction/EditTransactionViewModel.kt`
- **Purpose**: Handles speech recognition events and applies parsed data
- **Features**:
  - Speech recognition event handling
  - Auto-fill logic for form fields
  - Error handling with user feedback

## Supported Speech Patterns

### Amount Detection
- Numbers: "500", "2000", "15.50"
- Currency: "500 dollars", "2000 euros", "15.50 pounds"

### Date Detection
- Relative dates: "today", "yesterday", "tomorrow", "Monday", "Tuesday", etc.
- Absolute dates: "25/12/2023", "12-25-2023", "2023-12-25"

### Category Detection
The system recognizes the following category keywords:

#### Food & Dining
- food, restaurant, dining, lunch, dinner, breakfast, coffee, grocery, groceries, supermarket, market

#### Transportation
- transport, transportation, bus, train, taxi, uber, lyft, gas, fuel, parking, metro, subway

#### Housing
- rent, housing, mortgage, utilities, electricity, water, internet, wifi, phone

#### Shopping
- shopping, clothes, clothing, shoes, electronics, amazon, store

#### Entertainment
- entertainment, movie, cinema, theater, game, gaming, netflix, spotify

#### Health
- health, medical, doctor, pharmacy, medicine, hospital

#### Income
- salary, income, wage, bonus, freelance, work, job, payment, refund

### Description
Any remaining text after extracting amount, date, and category information.

## Usage Examples

### Example 1: Complete Transaction
**Speech**: "I spent 25 dollars on lunch today at the restaurant"
**Parsed**:
- Amount: 25.0
- Date: Today's date
- Category: Food
- Description: "at the restaurant"

### Example 2: Income Entry
**Speech**: "Received 2000 salary payment yesterday"
**Parsed**:
- Amount: 2000.0
- Date: Yesterday's date
- Category: Salary
- Description: "payment"

### Example 3: Partial Information
**Speech**: "500 for groceries"
**Parsed**:
- Amount: 500.0
- Date: null (not specified)
- Category: Food
- Description: null

## Error Handling

The system handles various error scenarios:

1. **Speech Recognition Not Available**: Shows "Speech recognition not available on this device"
2. **Permission Denied**: Shows "Microphone permission required for speech recognition"
3. **Recognition Failure**: Shows "Couldn't recognize speech, please try again."
4. **Parsing Failure**: Falls back to inserting raw transcription into description field

## Permissions

The feature requires the following permission:
- `RECORD_AUDIO`: For accessing the microphone

This permission is automatically added to the AndroidManifest.xml.

## Technical Implementation

### Flow Architecture
1. User taps microphone button
2. `StartSpeechRecognition` event is triggered
3. `SpeechRecognizerHelper` starts listening
4. Speech is converted to text
5. `SpeechParser` extracts structured data
6. Form fields are auto-filled with parsed data

### Data Flow
```
User Speech → SpeechRecognizer → Text → SpeechParser → ParsedTransactionData → Form Fields
```

## Future Enhancements

1. **Multi-language Support**: Extend category keywords for different languages
2. **Custom Categories**: Allow users to add custom voice commands for their categories
3. **Voice Training**: Improve recognition accuracy with user-specific training
4. **Offline Support**: Implement offline speech recognition for better privacy
5. **Smart Suggestions**: Learn from user patterns to improve parsing accuracy

## Testing

To test the voice input feature:

1. Ensure microphone permission is granted
2. Open the Income or Expense page
3. Tap the microphone button
4. Speak transaction details clearly
5. Verify that fields are auto-filled correctly

## Troubleshooting

### Common Issues

1. **No Speech Recognition**: Check if Google services are available on the device
2. **Poor Recognition**: Ensure quiet environment and clear speech
3. **Permission Issues**: Grant microphone permission in device settings
4. **Parsing Errors**: Try speaking more clearly or using simpler phrases

### Debug Information

The system logs recognition results and parsing information for debugging purposes. Check the Android logs for detailed information about speech recognition and parsing results.
