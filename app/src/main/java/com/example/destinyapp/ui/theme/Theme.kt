package com.example.destinyapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.destinyapp.ui.resources.*

private val DarkColorScheme = darkColorScheme(
    primary = DestinyPurple,
    secondary = DestinyBlue,
    tertiary = DestinyOrange,
    background = DestinyDarkGrey,
    surface = DestinyDarkGrey,
    onBackground = Color.White,
    onSurface = Color.White,
    error = DestinyRed,
    outline = DestinyNeutral800,
    surfaceVariant = DestinyNeutral800,
    onSurfaceVariant = DestinyNeutral400
)

private val LightColorScheme = lightColorScheme(
    primary = DestinyPurple,
    secondary = DestinyBlue,
    tertiary = DestinyOrange,
    background = Color.White,
    surface = Color.White,
    onBackground = DestinyNeutral900,
    onSurface = DestinyNeutral900,
    error = DestinyRed,
    outline = DestinyNeutral200,
    surfaceVariant = DestinyNeutral200,
    onSurfaceVariant = DestinyNeutral600
)

@Composable
fun DestinyAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Desactivamos DynamicColor para mantener la identidad visual de Destiny
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
