package com.rururi.easyprompt.ext

import androidx.compose.ui.graphics.Color

fun Color.toRgbaString(): String = "rgba(${(red * 255).toInt()}, ${(green * 255).toInt()}, ${(blue * 255).toInt()}, ${(alpha * 255).toInt()})"

fun String.toColorOrDefault(default: Color = Color.White): Color {
    return try {
        val match = Regex("""rgba\((\d+), (\d+), (\d+), (\d+)\)""").find(this)
        val (r, g, b, a) = match?.destructured ?: return default
        Color(r.toInt() / 255f, g.toInt() / 255f, b.toInt() / 255f, a.toInt() / 255f)
    } catch (e: Exception) {
        default
    }
}
