package com.ivy.transaction

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/**
 * Helper class to handle runtime permission requests for voice input functionality.
 */
object PermissionRequestHandler {
    
    const val RECORD_AUDIO_PERMISSION_REQUEST_CODE = 1001
    
    /**
     * Requests the RECORD_AUDIO permission if not already granted.
     */
    fun requestRecordAudioPermission(activity: Activity) {
        if (!PermissionHelper.hasRecordAudioPermission(activity)) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.RECORD_AUDIO),
                RECORD_AUDIO_PERMISSION_REQUEST_CODE
            )
        }
    }
    
    /**
     * Checks if the permission request was granted.
     */
    fun isPermissionGranted(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ): Boolean {
        return requestCode == RECORD_AUDIO_PERMISSION_REQUEST_CODE &&
                permissions.isNotEmpty() &&
                permissions[0] == Manifest.permission.RECORD_AUDIO &&
                grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED
    }
    
    /**
     * Opens the app settings page where users can manually grant permissions.
     */
    fun openAppSettings(context: Context) {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", context.packageName, null)
        }
        context.startActivity(intent)
    }
    
    /**
     * Checks if the user has permanently denied the permission.
     */
    fun isPermissionPermanentlyDenied(activity: Activity): Boolean {
        return !PermissionHelper.hasRecordAudioPermission(activity) &&
                !ActivityCompat.shouldShowRequestPermissionRationale(
                    activity,
                    Manifest.permission.RECORD_AUDIO
                )
    }
}
