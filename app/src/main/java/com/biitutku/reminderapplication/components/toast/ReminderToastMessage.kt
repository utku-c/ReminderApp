package com.biitutku.reminderapplication.components.toast

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp


@Composable
fun ReminderToastMessage(
    message: String,
    duration: Int = Toast.LENGTH_SHORT,
    type: ReminderToastProperty,
    padding: PaddingValues = PaddingValues(0.dp),
    contentAlignment: Alignment = Alignment.TopCenter,


    ) {
    val reminderManuelToast = ReminderToast(LocalContext.current)
    reminderManuelToast.MakeTest(
        message = message,
        duration = duration,
        type = type,
        padding = padding,
        contentAlignment = contentAlignment
    )
    reminderManuelToast.show()
}
