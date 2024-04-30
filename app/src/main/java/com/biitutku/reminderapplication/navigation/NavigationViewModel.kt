package com.biitutku.reminderapplication.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.biitutku.reminderapplication.domain.usecases.app_entry.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
   private val appEntryUseCases: AppEntryUseCases
): ViewModel() {

   var startDestination by mutableStateOf(Route.SplashScreen().name)

   suspend fun read(){
     appEntryUseCases.readAppEntry().onEach{
        startDestination = if (it){
           Route.LoginScreen().name
        } else{
           Route.OnboardScreen().name
        }
     }.launchIn(viewModelScope)
  }
}