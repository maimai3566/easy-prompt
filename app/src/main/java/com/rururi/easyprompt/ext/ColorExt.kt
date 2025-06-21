package com.rururi.easyprompt.ext

import androidx.compose.ui.graphics.Color

fun Color.toRgbaString(): String {
    return "rgba(${(red * 255).toInt()}, ${(green * 255).toInt()}, ${(blue * 255).toInt()}, ${(alpha * 255).toInt()})"
}

fun String.toColorOrDefault(default: Color = Color.White): Color {
    return try {
        val match = Regex("""rgba\((\d+), (\d+), (\d+), (\d+)\)""").find(this)
        val (r, g, b, a) = match?.destructured ?: return default
        Color(r.toInt() / 255f, g.toInt() / 255f, b.toInt() / 255f, a.toInt() / 255f)
    } catch (e: Exception) {
        default
    }
}

//Colorを16進数文字列に変換する拡張関数#AARRGGBB
fun Color.toHexString(): String {
    return "#%02X%02X%02X%02X".format(
        (alpha * 255).toInt(),
        (red * 255).toInt(),
        (green * 255).toInt(),
        (blue * 255).toInt(),
    )
}

//fun String.toColorOrDefault(default: Color = Color.White): Color {
//    return try {
//        // 正規表現で #AARRGGBB を分解
//        val match = Regex("""#?([A-Fa-f0-9]{8})""").find(this)
//        val hex = match?.groupValues?.get(1) ?: return default
//        val a = hex.substring(0, 2).toInt(16) / 255f
//        val r = hex.substring(2, 4).toInt(16) / 255f
//        val g = hex.substring(4, 6).toInt(16) / 255f
//        val b = hex.substring(6, 8).toInt(16) / 255f
//
//        Color(r, g, b, a)
//    } catch (e: Exception) {
//        default
//    }
//}
