package com.biitutku.reminderapplication.components.toast.toastTypes

import com.biitutku.reminderapplication.components.toast.ReminderToastProperty

enum class ToastMessageType(
    val type: ReminderToastProperty
) {
    Success(ReminderToastTypeSuccess()),
    Info(ReminderToastTypeInfo()),
    Warning(ReminderToastTypeWarning()),
    Error(ReminderToastTypeError())
}
