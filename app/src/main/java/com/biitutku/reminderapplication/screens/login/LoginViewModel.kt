package com.biitutku.reminderapplication.screens.login

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.biitutku.reminderapplication.domain.usecases.app_entry.AppEntryUseCases
import com.biitutku.reminderapplication.screens.appmain.home.HomePageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
   private val  appEntryUseCases: AppEntryUseCases
): ViewModel() {

   private val _state = mutableStateOf(LoginState())
   val state: State<LoginState> = _state



   fun onEvent(event: LoginEvent){
      when(event){
         is LoginEvent.ReadUserInfo -> {
            readUserInfo()
         }
      }
   }

   private fun readUserInfo() {
         appEntryUseCases.readUserInfo.readUsername().onEach {
            Log.d("viewModel: ",it.toString())
            _state.value.userName = it
         }.launchIn(viewModelScope)
         appEntryUseCases.readUserInfo.readPassword().onEach {
            Log.d("viewModel: ",it.toString())
            _state.value.password = it
         }.launchIn(viewModelScope)
   }

}