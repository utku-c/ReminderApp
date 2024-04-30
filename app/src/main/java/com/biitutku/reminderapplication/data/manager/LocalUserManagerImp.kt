package com.biitutku.reminderapplication.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.biitutku.reminderapplication.domain.manager.LocalUserManager
import com.biitutku.reminderapplication.util.Constants
import com.biitutku.reminderapplication.util.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImp(
   private val context: Context
): LocalUserManager {

   override suspend fun saveAppEntry() {
      context.dataStore.edit { settings ->
         settings[PreferencesKeys.APP_ENTRY] = true
      }
   }

   override fun readAppEntry(): Flow<Boolean> {
      return context.dataStore.data.map { preferences ->
         preferences[PreferencesKeys.APP_ENTRY] ?:false
      }
   }

   override suspend fun saveFirstOpening() {
      context.dataStore.edit { settings ->
         settings[PreferencesKeys.APP_FIRST_OPENING] = true
      }
   }

   override fun readFirstOpening(): Flow<Boolean> {
      return context.dataStore.data.map { preferences ->
         preferences[PreferencesKeys.APP_FIRST_OPENING] ?:false
      }
   }

   override suspend fun saveUserInfo(
      username: String,
      password: String
   ) {
      context.dataStore.edit { settings ->
         settings[PreferencesKeys.USERNAME] = username
         settings[PreferencesKeys.PASSWORD] = password
      }
   }

   override fun readUsername(): Flow<String?> {
      return context.dataStore.data.map { preferences ->
         preferences[PreferencesKeys.USERNAME]
      }
   }

   override fun readPassword(): Flow<String?> {
      return context.dataStore.data.map { preferences ->
         preferences[PreferencesKeys.PASSWORD]
      }
   }

   override suspend fun saveUserCurrentTarget(currentTarget: Int) {
      context.dataStore.edit { settings ->
         settings[PreferencesKeys.CURRENT_TARGET] = currentTarget
      }
   }

   override fun readCurrentTarget(): Flow<Int?> {
      return context.dataStore.data.map { preferences ->
         preferences[PreferencesKeys.CURRENT_TARGET]
      }
   }

   override suspend fun saveUserCurrentWeight(currentWeight: Int) {
      context.dataStore.edit { settings ->
         settings[PreferencesKeys.CURRENT_WEIGHT] = currentWeight
      }
   }

   override fun readCurrentWeight(): Flow<Int?> {
      return context.dataStore.data.map { preferences ->
         preferences[PreferencesKeys.CURRENT_WEIGHT]
      }
   }

   override suspend fun saveUserGender(gender: String) {
      context.dataStore.edit { settings ->
         settings[PreferencesKeys.GENDER] = gender
      }
   }

   override fun readGender(): Flow<String?> {
      return context.dataStore.data.map { preferences ->
         preferences[PreferencesKeys.GENDER]
      }
   }

   override suspend fun saveTimeToSleep(timeToSleep: String) {
      context.dataStore.edit { settings ->
         settings[PreferencesKeys.TIME_TO_SLEEP] = timeToSleep
      }
   }

   override fun readTimeToSleep(): Flow<String?> {
      return context.dataStore.data.map { preferences ->
         preferences[PreferencesKeys.TIME_TO_SLEEP]
      }
   }

   override suspend fun saveTimeToWakeUp(timeToWakeUp: String) {
      context.dataStore.edit { settings ->
         settings[PreferencesKeys.TIME_TO_WAKE_UP] = timeToWakeUp
      }
   }

   override fun readTimeToWakeUp(): Flow<String?> {
      return context.dataStore.data.map { preferences ->
         preferences[PreferencesKeys.TIME_TO_WAKE_UP]
      }
   }
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)

private object PreferencesKeys {
   val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)
   val APP_FIRST_OPENING = booleanPreferencesKey(name = Constants.APP_FIRST_OPENING)
   val USERNAME = stringPreferencesKey(name = Constants.USERNAME)
   val PASSWORD = stringPreferencesKey(name = Constants.PASSWORD)
   val CURRENT_TARGET = intPreferencesKey(name = Constants.CURRENT_TARGET)
   val CURRENT_WEIGHT = intPreferencesKey(name = Constants.CURRENT_WEIGHT)
   val GENDER = stringPreferencesKey(name = Constants.GENDER)
   val TIME_TO_SLEEP = stringPreferencesKey(name = Constants.TIME_TO_SLEEP)
   val TIME_TO_WAKE_UP = stringPreferencesKey(name = Constants.TIME_TO_WAKE_UP)
}