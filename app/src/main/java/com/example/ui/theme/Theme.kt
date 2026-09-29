package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = JanAushadhiBluePrimary,
    onPrimary = Color.White,
    primaryContainer = JanAushadhiBlueLight,
    onPrimaryContainer = JanAushadhiBlueDark,
    secondary = JanAushadhiCyan,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFE0F7FA),
    onSecondaryContainer = Color(0xFF006064),
    tertiary = JanAushadhiGreen,
    onTertiary = Color.White,
    background = HealthBackground,
    onBackground = HealthTextPrimary,
    surface = HealthSurface,
    onSurface = HealthTextPrimary,
    surfaceVariant = Color(0xFFF1F5F9),
    onSurfaceVariant = HealthTextSecondary,
    outline = HealthCardBorder
)

private val DarkColorScheme = darkColorScheme(
    primary = JanAushadhiBluePrimaryDark,
    onPrimary = Color(0xFF002B4F),
    primaryContainer = JanAushadhiBlueContainerDark,
    onPrimaryContainer = Color(0xFFD1E4FF),
    secondary = Color(0xFF80DEEA),
    onSecondary = Color(0xFF00363A),
    tertiary = Color(0xFF81C784),
    onTertiary = Color(0xFF003314),
    background = HealthBackgroundDark,
    onBackground = HealthTextPrimaryDark,
    surface = HealthSurfaceDark,
    onSurface = HealthTextPrimaryDark,
    surfaceVariant = Color(0xFF1E293B),
    onSurfaceVariant = HealthTextSecondaryDark,
    outline = Color(0xFF334155)
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
