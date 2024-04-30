package com.biitutku.reminderapplication.screens.signup


sealed class SignUpEvent {
   data object SaveUserInfo : SignUpEvent()
}