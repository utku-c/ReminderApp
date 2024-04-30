package com.biitutku.reminderapplication.domain.usecases.app_entry

import com.biitutku.reminderapplication.domain.manager.LocalUserManager

class SaveUserCurrentTarget(
   private val localUserManager: LocalUserManager
) {
   suspend operator fun invoke(currentTarget: Int){
      localUserManager.saveUserCurrentTarget(currentTarget)
   }
}