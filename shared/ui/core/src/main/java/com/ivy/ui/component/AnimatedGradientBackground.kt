package com.ivy.ui.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun AnimatedGradientBackground(
    modifier: Modifier = Modifier,
    colors: List<Color> = listOf(
        Color(0xFFFF9500), // Orange (logo color)
        Color(0xFFFF6B35), // Deep Orange
        Color(0xFFFF8C00), // Dark Orange
        Color(0xFFFFA500), // Orange
        Color(0xFFFFB366), // Light Orange
        Color(0xFFFFD700)  // Gold
    )
) {
    val infiniteTransition = rememberInfiniteTransition(label = "gradient_animation")
    
    val animatedFloat by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(15000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )
    
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(6000, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )
    
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.1f,
        targetValue = 0.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )

    Canvas(
        modifier = modifier
            .fillMaxSize()
            .alpha(alpha)
    ) {
        val centerX = size.width / 2f
        val centerY = size.height / 2f
        val radius = kotlin.math.max(size.width, size.height) * scale
        
        // Draw animated gradient circles
        drawCircle(
            brush = Brush.radialGradient(
                colors = colors.take(3),
                center = Offset(centerX, centerY),
                radius = radius * 0.8f
            ),
            radius = radius * 0.8f,
            center = Offset(centerX, centerY)
        )
        
        // Add smaller animated circles
        val angleRad = Math.toRadians(animatedFloat.toDouble())
        val offsetX = cos(angleRad).toFloat() * size.width * 0.2f
        val offsetY = sin(angleRad).toFloat() * size.height * 0.2f
        
        drawCircle(
            brush = Brush.radialGradient(
                colors = colors.drop(2).take(3),
                center = Offset(centerX + offsetX, centerY + offsetY),
                radius = radius * 0.4f
            ),
            radius = radius * 0.4f,
            center = Offset(centerX + offsetX, centerY + offsetY)
        )
        
        drawCircle(
            brush = Brush.radialGradient(
                colors = colors.drop(3).take(3),
                center = Offset(centerX - offsetX, centerY - offsetY),
                radius = radius * 0.3f
            ),
            radius = radius * 0.3f,
            center = Offset(centerX - offsetX, centerY - offsetY)
        )
    }
}

@Composable
fun PulsingGradientOrbs(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "orb_animation")
    
    val pulse1 by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse1"
    )
    
    val pulse2 by infiniteTransition.animateFloat(
        initialValue = 0.7f,
        targetValue = 1.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse2"
    )
    
    val pulse3 by infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue = 1.4f,
        animationSpec = infiniteRepeatable(
            animation = tween(3500, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse3"
    )
    
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.1f,
        targetValue = 0.25f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )

    Canvas(
        modifier = modifier
            .fillMaxSize()
            .alpha(alpha)
    ) {
        val centerX = size.width / 2f
        val centerY = size.height / 2f
        
        // Draw pulsing gradient orbs
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    Color(0xFFFF9500).copy(alpha = 0.4f), // Logo orange
                    Color(0xFFFF6B35).copy(alpha = 0.2f), // Deep orange
                    Color.Transparent
                ),
                center = Offset(centerX * 0.3f, centerY * 0.3f),
                radius = 150f * pulse1
            ),
            radius = 150f * pulse1,
            center = Offset(centerX * 0.3f, centerY * 0.3f)
        )

        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    Color(0xFFFF8C00).copy(alpha = 0.4f), // Dark orange
                    Color(0xFFFFA500).copy(alpha = 0.2f), // Orange
                    Color.Transparent
                ),
                center = Offset(centerX * 1.2f, centerY * 0.8f),
                radius = 120f * pulse2
            ),
            radius = 120f * pulse2,
            center = Offset(centerX * 1.2f, centerY * 0.8f)
        )

        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    Color(0xFFFF6B35).copy(alpha = 0.4f), // Deep orange
                    Color(0xFFFF9500).copy(alpha = 0.2f), // Logo orange
                    Color.Transparent
                ),
                center = Offset(centerX * 0.8f, centerY * 1.2f),
                radius = 100f * pulse3
            ),
            radius = 100f * pulse3,
            center = Offset(centerX * 0.8f, centerY * 1.2f)
        )
    }
}
