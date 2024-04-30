package com.biitutku.reminderapplication.domain.manager

import kotlinx.coroutines.flow.Flow


interface LocalUserManager {
   suspend fun saveAppEntry()
   fun readAppEntry(): Flow<Boolean>

   suspend fun saveFirstOpening()
   fun readFirstOpening(): Flow<Boolean>

   suspend fun saveUserInfo(username: String, password: String)
   fun readUsername(): Flow<String?>
   fun readPassword(): Flow<String?>

   suspend fun saveUserCurrentTarget(currentTarget: Int)
   fun readCurrentTarget(): Flow<Int?>
   suspend fun saveUserCurrentWeight(currentWeight:Int)
   fun readCurrentWeight(): Flow<Int?>
   suspend fun saveUserGender(gender:String)
   fun readGender(): Flow<String?>
   suspend fun saveTimeToSleep(timeToSleep:String)
   fun readTimeToSleep(): Flow<String?>
   suspend fun saveTimeToWakeUp(timeToWakeUp:String)
   fun readTimeToWakeUp(): Flow<String?>
}