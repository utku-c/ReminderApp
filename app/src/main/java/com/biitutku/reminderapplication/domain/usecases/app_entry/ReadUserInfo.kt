package com.biitutku.reminderapplication.domain.usecases.app_entry

import com.biitutku.reminderapplication.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadUserInfo(
   private val localUserManager: LocalUserManager
) {
   fun readUsername(): Flow<String?> {
      return localUserManager.readUsername()
   }

   fun readPassword(): Flow<String?> {
      return localUserManager.readPassword()
   }
}
