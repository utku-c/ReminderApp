package com.biitutku.reminderapplication.screens.appmain.setting

sealed class SettingEvent {
   data object SaveUserCurrentTarget : SettingEvent()
   data object SaveUserCurrentWeight : SettingEvent()
   data object SaveFirstOpening : SettingEvent()
   data object SaveUserGender : SettingEvent()
   data object SaveUserTimeToSleep : SettingEvent()
   data object SaveUserTimeToWakeUp : SettingEvent()
   data object ReadUserCurrentTarget : SettingEvent()
   data object ReadUserCurrentWeight : SettingEvent()
   data object ReadFirstOpening : SettingEvent()
   data object ReadUserGender : SettingEvent()
   data object ReadUserTimeToSleep : SettingEvent()
   data object ReadUserTimeToWakeUp : SettingEvent()
}