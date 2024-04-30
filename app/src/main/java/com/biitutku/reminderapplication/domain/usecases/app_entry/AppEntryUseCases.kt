package com.biitutku.reminderapplication.domain.usecases.app_entry

data class AppEntryUseCases(
   val readAppEntry: ReadAppEntry,
   val saveAppEntry: SaveAppEntry,
   val readFirstOpening: ReadFirstOpening,
   val saveFirstOpening: SaveFirstOpening,
   val saveUserInfo: SaveUserInfo,
   val readUserInfo: ReadUserInfo,
   val saveUserCurrentTarget: SaveUserCurrentTarget,
   val readUserCurrentTarget: ReadUserCurrentTarget,
   val saveUserCurrentWeight: SaveUserCurrentWeight,
   val readUserCurrentWeight: ReadUserCurrentWeight,
   val saveUserGender: SaveUserGender,
   val readUserGender: ReadUserGender,
   val saveTimeToSleep: SaveTimeToSleep,
   val readTimeToSleep: ReadTimeToSleep,
   val saveTimeToWakeUp: SaveTimeToWakeUp,
   val readTimeToWakeUp: ReadTimeToWakeUp
)
