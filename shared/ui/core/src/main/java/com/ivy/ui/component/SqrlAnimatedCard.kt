package com.ivy.ui.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.ivy.design.system.colors.SqrlColors

@Composable
fun SqrlAnimatedCard(
    modifier: Modifier = Modifier,
    gradient: List<Color>? = null,
    backgroundColor: Color = SqrlColors.White,
    cornerRadius: Int = 16,
    elevation: Int = 4,
    animateOnAppear: Boolean = true,
    content: @Composable ColumnScope.() -> Unit
) {
    var isVisible by remember { mutableStateOf(!animateOnAppear) }
    
    // Scale animation for card appearance
    val scale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0.8f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "card_scale"
    )
    
    // Alpha animation for fade in
    val alpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(300, easing = EaseInOut),
        label = "card_alpha"
    )
    
    // Trigger animation on first composition
    LaunchedEffect(Unit) {
        if (animateOnAppear) {
            isVisible = true
        }
    }
    
    Card(
        modifier = modifier
            .scale(scale)
            .graphicsLayer { this.alpha = alpha }
            .graphicsLayer {
                shadowElevation = elevation.dp.toPx()
            },
        shape = RoundedCornerShape(cornerRadius.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (gradient != null) Color.Transparent else backgroundColor
        )
    ) {
        if (gradient != null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.linearGradient(gradient),
                        shape = RoundedCornerShape(cornerRadius.dp)
                    )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    content = content
                )
            }
        } else {
            Column(
                modifier = Modifier.padding(16.dp),
                content = content
            )
        }
    }
}

@Composable
fun SqrlStaggeredCardList(
    modifier: Modifier = Modifier,
    items: List<@Composable () -> Unit>,
    staggerDelay: Int = 100
) {
    Column(modifier = modifier) {
        items.forEachIndexed { index, item ->
            var isVisible by remember { mutableStateOf(false) }
            
            // Staggered animation
            val scale by animateFloatAsState(
                targetValue = if (isVisible) 1f else 0.8f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                ),
                label = "staggered_scale_$index"
            )
            
            val alpha by animateFloatAsState(
                targetValue = if (isVisible) 1f else 0f,
                animationSpec = tween(300, easing = EaseInOut),
                label = "staggered_alpha_$index"
            )
            
            // Trigger animation with delay
            LaunchedEffect(Unit) {
                kotlinx.coroutines.delay((index * staggerDelay).toLong())
                isVisible = true
            }
            
            Box(
                modifier = Modifier
                    .scale(scale)
                    .graphicsLayer { this.alpha = alpha }
            ) {
                item()
            }
            
            if (index < items.size - 1) {
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun SqrlGradientCard(
    modifier: Modifier = Modifier,
    gradient: List<Color> = SqrlColors.OrangeYellowGradient,
    cornerRadius: Int = 20,
    content: @Composable ColumnScope.() -> Unit
) {
    SqrlAnimatedCard(
        modifier = modifier,
        gradient = gradient,
        cornerRadius = cornerRadius,
        elevation = 8,
        content = content
    )
}

@Composable
fun SqrlSuccessCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    SqrlGradientCard(
        modifier = modifier,
        gradient = SqrlColors.GreenTealGradient,
        content = content
    )
}

@Composable
fun SqrlInfoCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    SqrlGradientCard(
        modifier = modifier,
        gradient = SqrlColors.TealBlueGradient,
        content = content
    )
}
