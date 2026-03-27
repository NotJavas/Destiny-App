package com.example.destinyapp.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.destinyapp.ui.resources.DestinyGradients

@Composable
fun DestinyMarker(
    brush: Brush,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    shapeType: MarkerShape = MarkerShape.Circle,
    animationType: MarkerAnimation = MarkerAnimation.None
) {
    val infiniteTransition = rememberInfiniteTransition(label = "markerAnimation")
    
    val animatedScale by if (animationType == MarkerAnimation.Pulse) {
        infiniteTransition.animateFloat(
            initialValue = 1f,
            targetValue = 1.2f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            ), label = "pulse"
        )
    } else {
        remember { mutableStateOf(1f) }
    }

    val animatedRotation by if (animationType == MarkerAnimation.Rotate) {
        infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(3000, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            ), label = "rotate"
        )
    } else {
        remember { mutableStateOf(0f) }
    }



    val shape = when (shapeType) {
        MarkerShape.Circle -> CircleShape
        MarkerShape.Square -> RoundedCornerShape(8.dp)
        MarkerShape.Diamond -> RoundedCornerShape(4.dp) // Will be rotated 45deg
        MarkerShape.Hexagon -> RoundedCornerShape(12.dp)
    }

    Box(
        modifier = modifier
            .size(size)
            .scale(animatedScale)
            .rotate(if (shapeType == MarkerShape.Diamond) 45f else 0f)
            .rotate(animatedRotation)
            .background(brush, shape)
            .border(2.dp, Color.White.copy(alpha = 0.8f), shape)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .fillMaxSize()
                .rotate(if (shapeType == MarkerShape.Diamond) -45f else 0f) // Compensate rotation for icon
        )
    }
}

enum class MarkerShape {
    Circle, Square, Diamond, Hexagon
}

enum class MarkerAnimation {
    None, Pulse, Rotate, Bounce
}
