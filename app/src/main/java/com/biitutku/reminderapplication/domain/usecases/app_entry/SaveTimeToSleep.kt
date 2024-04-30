package com.biitutku.reminderapplication.domain.usecases.app_entry

import com.biitutku.reminderapplication.domain.manager.LocalUserManager

class SaveTimeToSleep(
   private val localUserManager: LocalUserManager
) {
   suspend operator fun invoke(timeToSleep: String){
      localUserManager.saveTimeToSleep(timeToSleep)
   }
}