package com.example.destinyapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.example.destinyapp.R

/**
 * Configuración de la fuente Onest de Google Fonts para Destiny.
 */
val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val OnestFont = GoogleFont("Onest")

val OnestFontFamily = FontFamily(
    Font(googleFont = OnestFont, fontProvider = provider, weight = FontWeight.Thin),
    Font(googleFont = OnestFont, fontProvider = provider, weight = FontWeight.Light),
    Font(googleFont = OnestFont, fontProvider = provider, weight = FontWeight.Medium),
    Font(googleFont = OnestFont, fontProvider = provider, weight = FontWeight.Normal),
    Font(googleFont = OnestFont, fontProvider = provider, weight = FontWeight.SemiBold),
    Font(googleFont = OnestFont, fontProvider = provider, weight = FontWeight.Bold),
    Font(googleFont = OnestFont, fontProvider = provider, weight = FontWeight.Black)
)

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = OnestFontFamily,
        fontWeight = FontWeight.Black,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    ),
    headlineLarge = TextStyle(
        fontFamily = OnestFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = OnestFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = OnestFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    labelMedium = TextStyle(
        fontFamily = OnestFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)
