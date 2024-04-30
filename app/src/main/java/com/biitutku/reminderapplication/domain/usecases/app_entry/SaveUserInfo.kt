package com.biitutku.reminderapplication.domain.usecases.app_entry

import com.biitutku.reminderapplication.domain.manager.LocalUserManager

class SaveUserInfo(
   private val localUserManager: LocalUserManager
) {

   suspend operator fun invoke(username: String, password: String) {
      localUserManager.saveUserInfo(username, password)
   }
}
