package com.biitutku.reminderapplication.screens.appmain.setting

data class SettingState(
   var currentTarget: Int? = 0,
   var currentWeight: Int? = null,
   var gender: String? = null,
   var timeToSleep: String? = null,
   var timeToWakeUp: String? = null
)