package com.biitutku.reminderapplication.domain.usecases.app_entry

import com.biitutku.reminderapplication.domain.manager.LocalUserManager

class SaveUserCurrentWeight(
   private val localUserManager: LocalUserManager
) {
   suspend operator fun invoke(currentWeight: Int){
      localUserManager.saveUserCurrentWeight(currentWeight)
   }
}