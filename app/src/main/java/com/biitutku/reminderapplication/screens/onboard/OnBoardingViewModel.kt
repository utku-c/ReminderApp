package com.biitutku.reminderapplication.screens.onboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.biitutku.reminderapplication.domain.usecases.app_entry.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
   private val  appEntryUseCases: AppEntryUseCases
): ViewModel() {

   fun onEvent(event: OnBoardingEvent){

      when(event){
         is OnBoardingEvent.SaveAppEntry -> {
            saveAppEntry()
         }

      }
   }
   private fun saveAppEntry() {
      viewModelScope.launch {
         appEntryUseCases.saveAppEntry()
      }
   }

}