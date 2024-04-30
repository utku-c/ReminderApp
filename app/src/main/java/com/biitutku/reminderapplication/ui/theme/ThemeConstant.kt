package com.biitutku.reminderapplication.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Spacing(
    val default: Dp = 0.dp,
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 32.dp,
    val extraLarge: Dp = 64.dp
)

data class Elevation(
    val default: Dp = 0.dp,
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 32.dp,
    val extraLarge: Dp = 64.dp
)


data class Padding(
    val default0: Dp = 0.dp,
    val extraSmall2: Dp = 2.dp,
    val extraSmall4: Dp = 4.dp,
    val extraSmall6: Dp = 6.dp,
    val small8: Dp = 8.dp,
    val small10: Dp = 10.dp,
    val small12: Dp = 12.dp,
    val medium16: Dp = 16.dp,
    val medium20: Dp = 20.dp,
    val medium24: Dp = 24.dp,
    val large32: Dp = 32.dp,
    val large40: Dp = 40.dp,
    val large50: Dp = 50.dp,
    val extraLarge60: Dp = 60.dp,
    val extraLarge64: Dp = 64.dp,
    val extraLarge70: Dp = 70.dp,
    val extraLarge80: Dp = 80.dp
)

data class AppColor(
    val bodyTextColor: Color = Color(0xFF04009A),
    val titleTextColor: Color = Color(0xFF04009A),
    val barColor: Color = Color(0xFF2196F3),
    val contentColor: Color = Color(0xFF77ACF1),
    val backgroundColor: Color = Color(0xFFFFFFFF),
    val colorWhite: Color = Color(0xFFFFFFFF),
)

val LocalColor = compositionLocalOf {
    AppColor()
}

val LocalSpacing = compositionLocalOf {
    Spacing()
}

val LocalElevation = compositionLocalOf {
    Elevation()
}

val LocalPadding = compositionLocalOf {
    Padding()
}


val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current


val MaterialTheme.elevation: Elevation
    @Composable
    @ReadOnlyComposable
    get() = LocalElevation.current

val MaterialTheme.padding: Padding
    @Composable
    @ReadOnlyComposable
    get() = LocalPadding.current

val MaterialTheme.color: AppColor
    @Composable
    @ReadOnlyComposable
    get() = LocalColor.current






