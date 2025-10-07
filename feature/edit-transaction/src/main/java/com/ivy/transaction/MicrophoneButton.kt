package com.ivy.transaction

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ivy.design.l0_system.UI
import com.ivy.design.l0_system.style
import com.ivy.legacy.IvyWalletPreview
import com.ivy.ui.R

/**
 * Composable component for the microphone button used for speech recognition.
 * Displays a microphone icon with optional text and handles click events.
 */
@Composable
fun MicrophoneButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    showText: Boolean = true,
    enabled: Boolean = true
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(enabled = enabled) { onClick() }
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(
                    if (enabled) UI.colors.primary else UI.colors.medium
                )
                .clickable(enabled = enabled) { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Mic,
                contentDescription = "Microphone",
                tint = if (enabled) Color.White else UI.colors.pure,
                modifier = Modifier.size(24.dp)
            )
        }
        
        if (showText) {
            Text(
                text = stringResource(R.string.tap_to_speak),
                style = UI.typo.b2.style(
                    color = if (enabled) UI.colors.pure else UI.colors.medium,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.padding(start = 12.dp)
            )
        }
    }
}

@Preview
@Composable
private fun PreviewMicrophoneButton() {
    IvyWalletPreview {
        MicrophoneButton(
            onClick = { },
            showText = true,
            enabled = true
        )
    }
}

@Preview
@Composable
private fun PreviewMicrophoneButtonDisabled() {
    IvyWalletPreview {
        MicrophoneButton(
            onClick = { },
            showText = true,
            enabled = false
        )
    }
}
