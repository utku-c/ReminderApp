package com.biitutku.reminderapplication.domain.usecases.app_entry

import com.biitutku.reminderapplication.domain.manager.LocalUserManager

class SaveUserGender(
   private val localUserManager: LocalUserManager
) {
   suspend operator fun invoke(gender: String){
      localUserManager.saveUserGender(gender)
   }
}