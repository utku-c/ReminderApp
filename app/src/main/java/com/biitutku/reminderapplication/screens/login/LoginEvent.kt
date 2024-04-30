package com.biitutku.reminderapplication.screens.login

sealed class LoginEvent {
   data object ReadUserInfo: LoginEvent()
}

