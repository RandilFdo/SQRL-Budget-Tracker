package com.ivy.transaction

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

/**
 * Helper class to manage runtime permissions for voice input functionality.
 */
object PermissionHelper {
    
    const val RECORD_AUDIO_PERMISSION = Manifest.permission.RECORD_AUDIO
    
    /**
     * Checks if the RECORD_AUDIO permission is granted.
     */
    fun hasRecordAudioPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            RECORD_AUDIO_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED
    }
    
    /**
     * Checks if the permission is permanently denied (user selected "Don't ask again").
     */
    fun isPermissionPermanentlyDenied(context: Context): Boolean {
        // This would typically be checked in an Activity or Fragment
        // For now, we'll return false as we'll handle this in the UI layer
        return false
    }
}
