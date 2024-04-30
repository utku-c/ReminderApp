package com.biitutku.reminderapplication.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.biitutku.reminderapplication.R


val caveatFontFamily = FontFamily(
    Font(R.font.caveat_bold, FontWeight.Bold),
    Font(R.font.caveat_regular, FontWeight.Normal),
    Font(R.font.caveat_semibold, FontWeight.SemiBold),
    Font(R.font.caveat_medium, FontWeight.Medium),
)

val flowerFontFamily = FontFamily(
    Font(R.font.indieflower_regular, FontWeight.Normal),
)

val appTypography = Typography(
    titleSmall = TextStyle(
        fontFamily = flowerFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = flowerFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        letterSpacing = 0.3.sp
    ),
    titleLarge = TextStyle(
        fontFamily = flowerFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 38.sp,
        letterSpacing = 1.sp
    ),
    bodySmall = TextStyle(
        fontFamily = flowerFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = flowerFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = flowerFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = flowerFontFamily,
    ),
    labelMedium = TextStyle(
        fontFamily = flowerFontFamily,
    ),
    labelSmall = TextStyle(
        fontFamily = flowerFontFamily,
    ),
    headlineLarge = TextStyle(
        fontFamily = flowerFontFamily,
    ),
    headlineMedium = TextStyle(
        fontFamily = flowerFontFamily,
    ),
    headlineSmall = TextStyle(
        fontFamily = flowerFontFamily,
    )
)