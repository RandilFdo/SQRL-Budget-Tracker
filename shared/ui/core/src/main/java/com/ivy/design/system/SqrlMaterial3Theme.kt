package com.ivy.design.system

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.ivy.design.system.colors.SqrlColors

@Composable
fun SqrlMaterial3Theme(
    isTrueBlack: Boolean,
    dark: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (dark) sqrlDarkColorScheme(isTrueBlack) else sqrlLightColorScheme(),
        content = content,
    )
}

private fun sqrlLightColorScheme(): ColorScheme = ColorScheme(
    primary = SqrlColors.Orange.primary,
    onPrimary = SqrlColors.White,
    primaryContainer = SqrlColors.Orange.light,
    onPrimaryContainer = SqrlColors.Black,
    inversePrimary = SqrlColors.Orange.dark,
    
    secondary = SqrlColors.Teal.primary,
    onSecondary = SqrlColors.White,
    secondaryContainer = SqrlColors.Teal.light,
    onSecondaryContainer = SqrlColors.Black,
    
    tertiary = SqrlColors.Purple.primary,
    onTertiary = SqrlColors.White,
    tertiaryContainer = SqrlColors.Purple.light,
    onTertiaryContainer = SqrlColors.Black,

    error = SqrlColors.Red.primary,
    onError = SqrlColors.White,
    errorContainer = SqrlColors.Red.light,
    onErrorContainer = SqrlColors.Black,

    background = SqrlColors.OffWhite,
    onBackground = SqrlColors.Black,
    surface = SqrlColors.White,
    onSurface = SqrlColors.Black,
    surfaceVariant = SqrlColors.LightGray,
    onSurfaceVariant = SqrlColors.Black,
    surfaceTint = SqrlColors.Orange.primary,
    inverseSurface = SqrlColors.DarkGray,
    inverseOnSurface = SqrlColors.White,

    outline = SqrlColors.Gray,
    outlineVariant = SqrlColors.LightGray,
    scrim = SqrlColors.Black.copy(alpha = 0.8f)
)

private fun sqrlDarkColorScheme(isTrueBlack: Boolean): ColorScheme = ColorScheme(
    primary = SqrlColors.Orange.primary,
    onPrimary = SqrlColors.Black,
    primaryContainer = SqrlColors.Orange.dark,
    onPrimaryContainer = SqrlColors.White,
    inversePrimary = SqrlColors.Orange.light,
    
    secondary = SqrlColors.Teal.primary,
    onSecondary = SqrlColors.Black,
    secondaryContainer = SqrlColors.Teal.dark,
    onSecondaryContainer = SqrlColors.White,
    
    tertiary = SqrlColors.Purple.primary,
    onTertiary = SqrlColors.Black,
    tertiaryContainer = SqrlColors.Purple.dark,
    onTertiaryContainer = SqrlColors.White,

    error = SqrlColors.Red.primary,
    onError = SqrlColors.Black,
    errorContainer = SqrlColors.Red.dark,
    onErrorContainer = SqrlColors.White,

    background = if (isTrueBlack) SqrlColors.Black else SqrlColors.DarkGray,
    onBackground = SqrlColors.White,
    surface = if (isTrueBlack) SqrlColors.Black else SqrlColors.DarkGray,
    onSurface = SqrlColors.White,
    surfaceVariant = SqrlColors.Gray,
    onSurfaceVariant = SqrlColors.White,
    surfaceTint = SqrlColors.Orange.primary,
    inverseSurface = SqrlColors.LightGray,
    inverseOnSurface = if (isTrueBlack) SqrlColors.Black else SqrlColors.DarkGray,

    outline = SqrlColors.Gray,
    outlineVariant = SqrlColors.DarkGray,
    scrim = SqrlColors.White.copy(alpha = 0.8f)
)
