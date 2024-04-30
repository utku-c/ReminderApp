/*
package com.biitutku.reminderapplication

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.biitutku.reminderapplication.domain.usecases.app_entry.AppEntryUseCases
import com.biitutku.reminderapplication.screens.nvgraph.Route
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MainViewModel @Inject constructor(
   private val appEntryUseCases: AppEntryUseCases
): ViewModel() {


   private val _splashCondition = mutableStateOf(true)
   val splashCondition: State<Boolean> = _splashCondition

   var startDestination by mutableStateOf(Route.AppStartNavigation.route)
      private set

   init {
      appEntryUseCases.readAppEntry().onEach { shouldStartFromLogin->


         if (shouldStartFromLogin){
            startDestination = Route.LoginScreen.route
         }
         else{
            startDestination =
         }
         delay(3000)
      }.launchIn(viewModelScope)

   }
}*/
