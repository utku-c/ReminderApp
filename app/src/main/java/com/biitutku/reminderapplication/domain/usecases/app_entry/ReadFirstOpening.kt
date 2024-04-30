package com.biitutku.reminderapplication.domain.usecases.app_entry

import com.biitutku.reminderapplication.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadFirstOpening(
   private val localUserManager: LocalUserManager
) {
   operator fun invoke(): Flow<Boolean> {
      return localUserManager.readFirstOpening()
   }
}