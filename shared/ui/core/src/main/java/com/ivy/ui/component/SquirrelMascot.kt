package com.ivy.ui.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ivy.ui.R

enum class SquirrelPose {
    IDLE,    // Default pose for dashboard/home
    HI,      // Waving pose for greetings
    CLAP     // Celebrating pose for success actions
}

@Composable
fun SquirrelMascot(
    pose: SquirrelPose = SquirrelPose.IDLE,
    size: Int = 120,
    modifier: Modifier = Modifier,
    animate: Boolean = true,
    onAnimationComplete: (() -> Unit)? = null
) {
    val context = LocalContext.current
    
    // Animation states
    val infiniteTransition = rememberInfiniteTransition(label = "squirrel_animation")
    
    // Bounce animation for idle pose
    val bounceScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        ),
        label = "bounce_scale"
    )
    
    // Pulse animation for success
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_scale"
    )
    
    // Fade in animation when pose changes
    val alpha by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(300, easing = EaseInOut),
        label = "fade_alpha"
    )
    
    // Scale animation for pose changes
    val scale by animateFloatAsState(
        targetValue = 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "scale_animation"
    )
    
    // Get the appropriate image resource
    val imageResource = when (pose) {
        SquirrelPose.IDLE -> R.drawable.squirrel_idle
        SquirrelPose.HI -> R.drawable.squirrel_hi
        SquirrelPose.CLAP -> R.drawable.squirrel_clap
    }
    
    // Apply animations based on pose and animate flag
    val finalScale = when {
        !animate -> 1f
        pose == SquirrelPose.IDLE -> bounceScale
        pose == SquirrelPose.CLAP -> pulseScale
        else -> scale
    }
    
    Box(
        modifier = modifier
            .size(size.dp)
            .graphicsLayer {
                scaleX = finalScale
                scaleY = finalScale
                this.alpha = alpha
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "Squirrel Mascot - ${pose.name}",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
    
    // Trigger completion callback after animation
    LaunchedEffect(pose) {
        if (onAnimationComplete != null) {
            kotlinx.coroutines.delay(500) // Wait for animation to complete
            onAnimationComplete()
        }
    }
}

@Composable
fun AnimatedSquirrelTransition(
    fromPose: SquirrelPose,
    toPose: SquirrelPose,
    size: Int = 120,
    modifier: Modifier = Modifier,
    onTransitionComplete: (() -> Unit)? = null
) {
    var currentPose by remember { mutableStateOf(fromPose) }
    
    LaunchedEffect(toPose) {
        currentPose = toPose
    }
    
    SquirrelMascot(
        pose = currentPose,
        size = size,
        modifier = modifier,
        animate = true,
        onAnimationComplete = onTransitionComplete
    )
}
