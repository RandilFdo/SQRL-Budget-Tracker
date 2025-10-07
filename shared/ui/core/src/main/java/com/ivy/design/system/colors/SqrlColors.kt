package com.ivy.design.system.colors

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
object SqrlColors {
    // Base colors
    val White = Color(0xFFFAFAFA)
    val OffWhite = Color(0xFFF8F9FA)
    val LightGray = Color(0xFFE9ECEF)
    val Gray = Color(0xFF6C757D)
    val DarkGray = Color(0xFF495057)
    val Black = Color(0xFF212529)
    
    // Primary brand colors (Orange-based like Duolingo)
    val Orange = ColorShades(
        extraLight = Color(0xFFFFE5CC),
        light = Color(0xFFFFCC99),
        kindaLight = Color(0xFFFFB366),
        primary = Color(0xFFFF9500), // Main brand orange
        kindaDark = Color(0xFFE6850E),
        dark = Color(0xFFCC7700),
        extraDark = Color(0xFFB36600),
    )
    
    // Secondary colors (Duolingo-inspired)
    val Teal = ColorShades(
        extraLight = Color(0xFFCCF2F2),
        light = Color(0xFF99E6E6),
        kindaLight = Color(0xFF66D9D9),
        primary = Color(0xFF00CCCC), // Bright teal
        kindaDark = Color(0xFF00B3B3),
        dark = Color(0xFF009999),
        extraDark = Color(0xFF008080),
    )
    
    val Yellow = ColorShades(
        extraLight = Color(0xFFFFF2CC),
        light = Color(0xFFFFE699),
        kindaLight = Color(0xFFFFD966),
        primary = Color(0xFFFFCC00), // Bright yellow
        kindaDark = Color(0xFFE6B800),
        dark = Color(0xFFCC9900),
        extraDark = Color(0xFFB38600),
    )
    
    val Purple = ColorShades(
        extraLight = Color(0xFFE6CCFF),
        light = Color(0xFFCC99FF),
        kindaLight = Color(0xFFB366FF),
        primary = Color(0xFF9933FF), // Bright purple
        kindaDark = Color(0xFF8829E6),
        dark = Color(0xFF7722CC),
        extraDark = Color(0xFF661BB3),
    )
    
    val LightBlue = ColorShades(
        extraLight = Color(0xFFCCE6FF),
        light = Color(0xFF99CCFF),
        kindaLight = Color(0xFF66B3FF),
        primary = Color(0xFF3399FF), // Light blue
        kindaDark = Color(0xFF2D88E6),
        dark = Color(0xFF2677CC),
        extraDark = Color(0xFF2066B3),
    )
    
    // Status colors
    val Green = ColorShades(
        extraLight = Color(0xFFCCF2CC),
        light = Color(0xFF99E699),
        kindaLight = Color(0xFF66D966),
        primary = Color(0xFF33CC33), // Success green
        kindaDark = Color(0xFF2DB82D),
        dark = Color(0xFF26A326),
        extraDark = Color(0xFF208F20),
    )
    
    val Red = ColorShades(
        extraLight = Color(0xFFFFCCCC),
        light = Color(0xFFFF9999),
        kindaLight = Color(0xFFFF6666),
        primary = Color(0xFFFF3333), // Error red
        kindaDark = Color(0xFFE62D2D),
        dark = Color(0xFFCC2626),
        extraDark = Color(0xFFB32020),
    )
    
    // Gradient colors for buttons and cards
    val OrangeYellowGradient = listOf(Orange.primary, Yellow.primary)
    val TealBlueGradient = listOf(Teal.primary, LightBlue.primary)
    val PurplePinkGradient = listOf(Purple.primary, Color(0xFFFF69B4))
    val GreenTealGradient = listOf(Green.primary, Teal.primary)
}
