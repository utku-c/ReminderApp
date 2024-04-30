package com.biitutku.reminderapplication.components.toast.toastTypes

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.biitutku.reminderapplication.R
import com.biitutku.reminderapplication.components.toast.ReminderToastProperty
import com.biitutku.reminderapplication.ui.theme.color

class ReminderToastTypeError : ReminderToastProperty {
    override fun getResourceId(): Int = R.drawable.icon_error

    @Composable
    override fun getBackgroundColor(): Color = Color.Red

    @Composable
    override fun getIconColor(): Color = MaterialTheme.color.colorWhite

}