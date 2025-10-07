package com.ivy.ui.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ivy.design.system.colors.SqrlColors
import kotlinx.coroutines.launch

@Composable
fun SqrlAnimatedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    gradient: List<Color> = SqrlColors.OrangeYellowGradient,
    textColor: Color = Color.White,
    size: SqrlButtonSize = SqrlButtonSize.MEDIUM,
    shape: RoundedCornerShape = RoundedCornerShape(16.dp)
) {
    var isPressed by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    
    // Animation for button press
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessHigh
        ),
        label = "button_scale"
    )
    
    // Glow animation
    val infiniteTransition = rememberInfiniteTransition(label = "glow_animation")
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glow_alpha"
    )
    
    val buttonHeight = when (size) {
        SqrlButtonSize.SMALL -> 40.dp
        SqrlButtonSize.MEDIUM -> 48.dp
        SqrlButtonSize.LARGE -> 56.dp
    }
    
    val textSize = when (size) {
        SqrlButtonSize.SMALL -> 14.sp
        SqrlButtonSize.MEDIUM -> 16.sp
        SqrlButtonSize.LARGE -> 18.sp
    }
    
    Box(
        modifier = modifier
            .scale(scale)
            .graphicsLayer {
                shadowElevation = if (isPressed) 2.dp.toPx() else 8.dp.toPx()
            }
    ) {
        // Glow effect
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(buttonHeight + 8.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            gradient.first().copy(alpha = glowAlpha),
                            Color.Transparent
                        ),
                        radius = 100f
                    ),
                    shape = RoundedCornerShape(20.dp)
                )
        )
        
        // Main button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(buttonHeight)
                .background(
                    brush = Brush.linearGradient(gradient),
                    shape = shape
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    isPressed = true
                    onClick()
                    // Reset pressed state after animation
                    coroutineScope.launch {
                        kotlinx.coroutines.delay(150)
                        isPressed = false
                    }
                }
                .clip(shape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = textColor,
                fontSize = textSize,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun SqrlFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
    gradient: List<Color> = SqrlColors.OrangeYellowGradient
) {
    var isPressed by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    
    // Pulse animation
    val infiniteTransition = rememberInfiniteTransition(label = "fab_pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_scale"
    )
    
    // Press animation
    val pressScale by animateFloatAsState(
        targetValue = if (isPressed) 0.9f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessHigh
        ),
        label = "press_scale"
    )
    
    // Glow effect
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glow_alpha"
    )
    
    Box(
        modifier = modifier
            .scale(pressScale * pulseScale)
    ) {
        // Glow halo
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            gradient.first().copy(alpha = glowAlpha),
                            Color.Transparent
                        ),
                        radius = 40f
                    ),
                    shape = RoundedCornerShape(40.dp)
                )
        )
        
        // Main FAB
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(
                    brush = Brush.radialGradient(gradient),
                    shape = RoundedCornerShape(32.dp)
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    isPressed = true
                    onClick()
                    coroutineScope.launch {
                        kotlinx.coroutines.delay(150)
                        isPressed = false
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            icon()
        }
    }
}

enum class SqrlButtonSize {
    SMALL, MEDIUM, LARGE
}
