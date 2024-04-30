package com.biitutku.reminderapplication.components.toast

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color

interface ReminderToastProperty {
    fun getResourceId(): Int

    @Composable
    @ReadOnlyComposable
    fun getBackgroundColor(): Color

    @Composable
    @ReadOnlyComposable
    fun getIconColor(): Color
}
