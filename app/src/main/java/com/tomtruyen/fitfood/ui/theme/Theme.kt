package com.tomtruyen.fitfood.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat

private val DarkColorScheme = lightColorScheme(
    primary = SpanishCrimson,
    onPrimary = White,
    background = RichBlack,
    surface = DarkGunMetal,
    onSurface = White,
)

private val LightColorScheme = lightColorScheme(
    primary = SpanishCrimson,
    onPrimary = White,
    background = RichBlack,
    surface = DarkGunMetal,
    onSurface = White,
)

@Composable
fun FitFoodTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        val currentWindow = (view.context as Activity).window

        SideEffect {
            currentWindow.statusBarColor = colorScheme.background.toArgb()
            currentWindow.navigationBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(currentWindow, view)?.isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}