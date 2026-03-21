package com.example.destinyapp.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.destinyapp.ui.navigation.Screen
import com.example.destinyapp.ui.resources.*

/**
 * BARRA DE NAVEGACIÓN CON GLASSMORPHISM REAL
 * Implementa micro-interacciones y estética futurista.
 */
@Composable
fun DestinyBottomBar(navController: NavController) {
    val items = listOf(
        Screen.Home,
        Screen.Events,
        Screen.Map,
        Screen.Notifications,
        Screen.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp) // Flotante
            .height(72.dp)
    ) {
        // Efecto de desenfoque y fondo glass (Android 12+)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(24.dp))
                .background(DestinyGlassBlack.copy(alpha = 0.8f))
                .border(1.dp, DestinyGlassBorder, RoundedCornerShape(24.dp))
                .blur(10.dp) // Nota: El blur real en Compose es experimental/Android 12+
        )

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { screen ->
                val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                
                // MICRO-INTERACCIÓN: Animación de escala al seleccionar
                val scale by animateFloatAsState(
                    targetValue = if (isSelected) 1.2f else 1.0f,
                    animationSpec = tween(durationMillis = 300),
                    label = "IconScale"
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .noRippleClickable {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = if (isSelected) screen.filledIcon!! else screen.outlinedIcon!!,
                            contentDescription = screen.title,
                            modifier = Modifier.scale(scale),
                            tint = if (isSelected) DestinyPurple else DestinyNeutral600
                        )
                        if (isSelected) {
                            Spacer(modifier = Modifier.height(4.dp))
                            // Indicador de punto minimalista
                            Box(
                                modifier = Modifier
                                    .size(4.dp)
                                    .background(DestinyPurple, RoundedCornerShape(50))
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * Extensión para evitar el ripple effect clásico de Material y mantener el look limpio.
 * Usa remember para el InteractionSource como buena práctica de Compose.
 */
@Composable
fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier {
    val interactionSource = remember { MutableInteractionSource() }
    return this.then(
        Modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = onClick
        )
    )
}
