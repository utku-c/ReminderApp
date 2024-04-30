package com.biitutku.reminderapplication.screens.appmain.setting

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.biitutku.reminderapplication.domain.usecases.app_entry.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
   private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {
   private val _state = mutableStateOf(SettingState())
   val state: State<SettingState> = _state

   init {
      getUserInfo()
   }

   fun onEvent(event: SettingEvent) {
      when (event) {
         is SettingEvent.SaveUserCurrentTarget -> {
            if (_state.value.currentTarget != null) {
               saveCurrentTarget(_state.value.currentTarget!!)
            }
         }

         is SettingEvent.SaveUserCurrentWeight -> {
            if (_state.value.currentWeight != null) {
               saveCurrentWeight(_state.value.currentWeight!!)
            }
         }

         is SettingEvent.SaveUserGender -> {
            if (_state.value.gender != null) {
               saveCurrentGender(_state.value.gender!!)
            }
         }

         is SettingEvent.SaveUserTimeToSleep -> {
            if (_state.value.timeToSleep != null) {
               saveCurrentTimeToSleep(_state.value.timeToSleep!!)
            }
         }


         is SettingEvent.SaveUserTimeToWakeUp -> {
            if (_state.value.timeToWakeUp != null) {
               saveCurrentTimeToWakeUp(_state.value.timeToWakeUp!!)
            }
         }
         SettingEvent.SaveFirstOpening -> {
            if (_state.value.timeToWakeUp != null) {
               saveCurrentTimeToWakeUp(_state.value.timeToWakeUp!!)
            }
         }

         is SettingEvent.ReadUserCurrentTarget -> {
            readUserCurrentTarget()
         }

         is SettingEvent.ReadUserCurrentWeight -> {
            readUserCurrentWeight()
         }

         is SettingEvent.ReadUserGender -> {
            readUserGender()
         }

         is SettingEvent.ReadUserTimeToSleep -> {
            readUserTimeToSleep()
         }

         is SettingEvent.ReadUserTimeToWakeUp -> {
            readUserTimeToWakeUp()
         }

         is SettingEvent.ReadFirstOpening ->{
            readFirsOpening()
         }
      }
   }

   private fun readFirsOpening() {
      TODO("Not yet implemented")
   }

   fun getUserInfo(){
      readUserGender()
      readUserCurrentTarget()
      readUserCurrentWeight()
      readUserTimeToSleep()
      readUserTimeToWakeUp()
   }

   private fun readUserTimeToWakeUp() {
      appEntryUseCases.readTimeToWakeUp().onEach {
         Log.d("SettingViewModel", it.toString())
         _state.value.timeToWakeUp = it
      }.launchIn(viewModelScope)
   }

   private fun readUserTimeToSleep() {
      appEntryUseCases.readTimeToSleep().onEach {
         Log.d("SettingViewModel", it.toString())
         _state.value.timeToSleep = it
      }.launchIn(viewModelScope)
   }

   private fun readUserGender() {
      appEntryUseCases.readUserGender().onEach {
         Log.d("SettingViewModel", it.toString())
         _state.value.gender = it
      }.launchIn(viewModelScope)
   }

   private fun readUserCurrentWeight() {
      appEntryUseCases.readUserCurrentWeight().onEach {
         Log.d("SettingViewModel", it.toString())
         _state.value.currentWeight = it
      }.launchIn(viewModelScope)
   }

   private fun readUserCurrentTarget() {
      appEntryUseCases.readUserCurrentTarget().onEach {
         Log.d("SettingViewModel", it.toString())
         _state.value.currentTarget = it
      }.launchIn(viewModelScope)
   }

   private fun saveCurrentTimeToWakeUp(timeToWakeUp: String) {
      viewModelScope.launch {
         appEntryUseCases.saveTimeToWakeUp(timeToWakeUp)
      }
   }

   private fun saveCurrentTimeToSleep(timeToSleep: String) {
      viewModelScope.launch {
         appEntryUseCases.saveTimeToSleep(timeToSleep)
      }
   }

   private fun saveCurrentGender(gender: String) {
      viewModelScope.launch {
         appEntryUseCases.saveUserGender(gender)
      }
   }

   private fun saveCurrentWeight(currentWeight: Int) {
      viewModelScope.launch {
         appEntryUseCases.saveUserCurrentWeight(currentWeight)
      }
   }

   private fun saveCurrentTarget(currentTarget: Int) {
      viewModelScope.launch {
         appEntryUseCases.saveUserCurrentTarget(currentTarget)
      }
   }
}