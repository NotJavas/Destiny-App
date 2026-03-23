package com.example.destinyapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.destinyapp.ui.resources.*
import com.example.destinyapp.ui.theme.DestinyAppTheme

/**
 * COMPONENTE DE LOGO DE MARCA
 */
@Composable
fun DestinyLogo(
    modifier: Modifier = Modifier,
    size: Dp = 120.dp,
    rotation: Float = 12f // Grados de inclinación por defecto
) {
    Box(
        modifier = modifier
            .size(size)
            .background(
                Brush.linearGradient(listOf(DestinyPurple, DestinyBlue)),
                shape = MaterialTheme.shapes.extraLarge
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "D",
            modifier = Modifier
                .offset(x = 2.dp, y = 2.dp)
                .rotate(rotation),
            fontSize = (size.value * 0.6f).sp,
            color = Color.White,
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.Black
        )
    }
}

/**
 * Escala de tamaños profesional vinculada a la tipografía del sistema.
 */
enum class DestinyButtonSize(val height: Dp, val iconSize: Dp) {
    SMALL(32.dp, 16.dp),
    NORMAL(48.dp, 20.dp),
    LARGE(56.dp, 24.dp)
}

/**
 * BOTÓN PRIMARIO (Gradient)
 * Usa la paleta de marca centralizada.
 */
@Composable
fun DestinyGradientButton(
    text: String,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    size: DestinyButtonSize = DestinyButtonSize.NORMAL,
    onClick: () -> Unit
) {
    val gradient = if (enabled) {
        Brush.horizontalGradient(colors = listOf(DestinyPurple, DestinyBlue))
    } else {
        Brush.horizontalGradient(colors = listOf(DestinyNeutral700, DestinyNeutral800))
    }

    Button(
        onClick = onClick,
        enabled = enabled && !isLoading,
        modifier = modifier.height(size.height),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent, 
            disabledContainerColor = Color.Transparent
        ),
        contentPadding = PaddingValues(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(size.iconSize), 
                    color = Color.White, 
                    strokeWidth = 2.dp
                )
            } else {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (icon != null) {
                        Icon(icon, null, tint = Color.White, modifier = Modifier.size(size.iconSize))
                        Spacer(Modifier.width(8.dp))
                    }
                    Text(
                        text = text, 
                        color = Color.White, 
                        style = if (size == DestinyButtonSize.SMALL) MaterialTheme.typography.labelMedium else MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

/**
 * BOTÓN SECUNDARIO (Outline)
 */
@Composable
fun DestinyOutlineButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    size: DestinyButtonSize = DestinyButtonSize.NORMAL,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.height(size.height),
        border = BorderStroke(1.dp, if (enabled) DestinyPurple else DestinyNeutral700),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = DestinyPurple, 
            disabledContentColor = DestinyNeutral600
        )
    ) {
        Text(
            text = text, 
            style = if (size == DestinyButtonSize.SMALL) MaterialTheme.typography.labelMedium else MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

/**
 * BOTÓN MONOCROMÁTICO (Alto Contraste)
 */
@Composable
fun DestinyMonoButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onBackground,
            contentColor = MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(text, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
    }
}

/**
 * BOTÓN CIRCULAR (Icon Only)
 */
@Composable
fun DestinyCircularButton(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    val gradient = Brush.horizontalGradient(colors = listOf(DestinyPurple, DestinyBlue))
    IconButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .size(48.dp)
            .background(
                if (enabled) gradient else Brush.linearGradient(listOf(DestinyNeutral700, DestinyNeutral700)), 
                CircleShape
            )
    ) {
        Icon(icon, contentDescription = null, tint = Color.White)
    }
}

/**
 * BOTÓN DE TEXTO
 */
@Composable
fun DestinyTextButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    TextButton(onClick = onClick, modifier = modifier) {
        Text(text, color = DestinyNeutral600, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
    }
}

/**
 * BOTÓN DESTRUCTIVO
 * Usa los colores especializados definidos en Colors.kt
 */
@Composable
fun DestinyDestructiveButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = DestinyRedContainer, 
            contentColor = DestinyRedContent
        ),
        border = BorderStroke(1.dp, DestinyRedBorder),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(text, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
    }
}

/**
 * BOTÓN DE VIDRIO (Glassmorphism)
 */
@Composable
fun DestinyGlassButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = DestinyGlassWhite, 
            contentColor = Color.White
        ),
        border = BorderStroke(1.dp, DestinyGlassBorder),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(text, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF181818)
@Composable
fun ButtonsPreview() {
    DestinyAppTheme {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Marca", color = Color.White, fontWeight = FontWeight.Bold)
            DestinyLogo(size = 80.dp)

            Text("Principales", color = Color.White, fontWeight = FontWeight.Bold)
            DestinyGradientButton(text = "Gradient Primary", icon = Icons.Default.Star, onClick = {})
            
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                DestinyOutlineButton(text = "Secondary", onClick = {})
                DestinyCircularButton(icon = Icons.Default.Add, onClick = {})
            }

            Text("Estados (Loading/Disabled)", color = Color.White)
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                DestinyGradientButton(text = "Small", size = DestinyButtonSize.SMALL, isLoading = true, onClick = {})
                DestinyGradientButton(text = "Disabled", enabled = false, onClick = {})
            }

            Text("Otros Estilos", color = Color.White, fontWeight = FontWeight.Bold)
            DestinyMonoButton(text = "Contrast Mode", onClick = {})
            DestinyGlassButton(text = "Glassmorphism", onClick = {})
            
            Row(verticalAlignment = Alignment.CenterVertically) {
                DestinyDestructiveButton(text = "Delete", onClick = {})
                DestinyTextButton(text = "Cancel", onClick = {})
            }
        }
    }
}
