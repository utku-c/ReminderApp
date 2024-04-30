package com.biitutku.reminderapplication.domain.usecases.app_entry

import com.biitutku.reminderapplication.domain.manager.LocalUserManager

class SaveTimeToWakeUp(
   private val localUserManager: LocalUserManager
) {
   suspend operator fun invoke(timeToWakeUp: String){
      localUserManager.saveTimeToWakeUp(timeToWakeUp)
   }
}