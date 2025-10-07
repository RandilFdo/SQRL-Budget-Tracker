package com.ivy.transaction

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import androidx.core.content.ContextCompat
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOf
import java.util.Locale

/**
 * Helper class for handling speech recognition using Android's SpeechRecognizer API.
 * Provides a coroutine-based interface for speech recognition with proper error handling.
 */
class SpeechRecognizerHelper(private val context: Context) {

    /**
     * Checks if speech recognition is available on the device.
     */
    fun isSpeechRecognitionAvailable(): Boolean {
        return SpeechRecognizer.isRecognitionAvailable(context)
    }

    /**
     * Checks if the RECORD_AUDIO permission is granted.
     */
    fun hasRecordAudioPermission(): Boolean {
        return PermissionHelper.hasRecordAudioPermission(context)
    }

    /**
     * Starts speech recognition and returns a Flow with the result.
     * 
     * @param language The language code for recognition (default: device default)
     * @return Flow<SpeechRecognitionResult> containing the recognition result
     */
    fun startListening(language: String = Locale.getDefault().language): Flow<SpeechRecognitionResult> {
        if (!isSpeechRecognitionAvailable()) {
            return flowOf(SpeechRecognitionResult.Error("Speech recognition not available"))
        }

        if (!hasRecordAudioPermission()) {
            return flowOf(SpeechRecognitionResult.Error("RECORD_AUDIO permission not granted"))
        }

        return callbackFlow {
            val speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
            
            val recognitionListener = object : RecognitionListener {
                override fun onReadyForSpeech(params: Bundle?) {
                    // Speech recognition is ready
                }

                override fun onBeginningOfSpeech() {
                    // User started speaking
                }

                override fun onRmsChanged(rmsdB: Float) {
                    // Audio level changed
                }

                override fun onBufferReceived(buffer: ByteArray?) {
                    // Audio buffer received
                }

                override fun onEndOfSpeech() {
                    // User stopped speaking
                }

                override fun onError(error: Int) {
                    val errorMessage = when (error) {
                        SpeechRecognizer.ERROR_AUDIO -> "Audio recording error"
                        SpeechRecognizer.ERROR_CLIENT -> "Client side error"
                        SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "Insufficient permissions"
                        SpeechRecognizer.ERROR_NETWORK -> "Network error"
                        SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "Network timeout"
                        SpeechRecognizer.ERROR_NO_MATCH -> "No speech input was recognized"
                        SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "Recognition service busy"
                        SpeechRecognizer.ERROR_SERVER -> "Server sends error status"
                        SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "No speech input"
                        else -> "Unknown error occurred"
                    }
                    
                    trySend(SpeechRecognitionResult.Error(errorMessage))
                    close()
                }

                override fun onResults(results: Bundle?) {
                    val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                    if (!matches.isNullOrEmpty()) {
                        val recognizedText = matches[0]
                        trySend(SpeechRecognitionResult.Success(recognizedText))
                    } else {
                        trySend(SpeechRecognitionResult.Error("No speech input was recognized"))
                    }
                    close()
                }

                override fun onPartialResults(partialResults: Bundle?) {
                    // Partial results received (optional)
                }

                override fun onEvent(eventType: Int, params: Bundle?) {
                    // Additional events (optional)
                }
            }

            speechRecognizer.setRecognitionListener(recognitionListener)

            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                putExtra(RecognizerIntent.EXTRA_LANGUAGE, language)
                putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak your transaction details")
                putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1)
                putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
            }

            speechRecognizer.startListening(intent)

            awaitClose {
                speechRecognizer.destroy()
            }
        }
    }
}

/**
 * Sealed class representing the result of speech recognition.
 */
sealed class SpeechRecognitionResult {
    data class Success(val text: String) : SpeechRecognitionResult()
    data class Error(val message: String) : SpeechRecognitionResult()
}
