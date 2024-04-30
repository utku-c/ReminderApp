package com.biitutku.reminderapplication.screens.signup

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.biitutku.reminderapplication.domain.usecases.app_entry.AppEntryUseCases
import com.biitutku.reminderapplication.screens.login.LoginEvent
import com.biitutku.reminderapplication.screens.login.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
   private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {

   private val _state = mutableStateOf(SignUpState())
   val state: State<SignUpState> = _state


   fun onEvent(event: SignUpEvent) {
      when (event) {
         is SignUpEvent.SaveUserInfo -> {
            if (_state.value.userName != null && _state.value.password != null){
               saveUserInfo(
                  _state.value.userName!!,
                  _state.value.password!!,
               )
            }

         }

      }
   }

   private fun saveUserInfo(userName: String, password: String) {
      viewModelScope.launch {
         appEntryUseCases.saveUserInfo(userName, password)
      }
   }
}

