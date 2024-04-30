package com.biitutku.reminderapplication.domain.usecases.app_entry

import com.biitutku.reminderapplication.domain.manager.LocalUserManager

class SaveAppEntry(
   private val localUserManager: LocalUserManager
) {

   suspend operator fun invoke(){
      localUserManager.saveAppEntry()
   }
}