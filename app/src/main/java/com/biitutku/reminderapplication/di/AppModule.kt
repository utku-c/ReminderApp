package com.biitutku.reminderapplication.di

import android.app.Application
import androidx.room.Room
import com.biitutku.reminderapplication.data.local.WaterRecordDao
import com.biitutku.reminderapplication.data.local.WaterRecordDatabase
import com.biitutku.reminderapplication.data.manager.LocalUserManagerImp
import com.biitutku.reminderapplication.domain.manager.LocalUserManager
import com.biitutku.reminderapplication.domain.usecases.app_entry.AppEntryUseCases
import com.biitutku.reminderapplication.domain.usecases.app_entry.ReadAppEntry
import com.biitutku.reminderapplication.domain.usecases.app_entry.ReadFirstOpening
import com.biitutku.reminderapplication.domain.usecases.app_entry.ReadTimeToSleep
import com.biitutku.reminderapplication.domain.usecases.app_entry.ReadTimeToWakeUp
import com.biitutku.reminderapplication.domain.usecases.app_entry.ReadUserCurrentTarget
import com.biitutku.reminderapplication.domain.usecases.app_entry.ReadUserCurrentWeight
import com.biitutku.reminderapplication.domain.usecases.app_entry.ReadUserGender
import com.biitutku.reminderapplication.domain.usecases.app_entry.ReadUserInfo
import com.biitutku.reminderapplication.domain.usecases.app_entry.SaveAppEntry
import com.biitutku.reminderapplication.domain.usecases.app_entry.SaveFirstOpening
import com.biitutku.reminderapplication.domain.usecases.app_entry.SaveTimeToSleep
import com.biitutku.reminderapplication.domain.usecases.app_entry.SaveTimeToWakeUp
import com.biitutku.reminderapplication.domain.usecases.app_entry.SaveUserCurrentTarget
import com.biitutku.reminderapplication.domain.usecases.app_entry.SaveUserCurrentWeight
import com.biitutku.reminderapplication.domain.usecases.app_entry.SaveUserGender
import com.biitutku.reminderapplication.domain.usecases.app_entry.SaveUserInfo
import com.biitutku.reminderapplication.domain.usecases.water_record.DeleteWaterRecord
import com.biitutku.reminderapplication.domain.usecases.water_record.SelectWaterRecord
import com.biitutku.reminderapplication.domain.usecases.water_record.SelectWaterRecordWithDay
import com.biitutku.reminderapplication.domain.usecases.water_record.SelectWaterRecordWithMonth
import com.biitutku.reminderapplication.domain.usecases.water_record.UpsertWaterRecord
import com.biitutku.reminderapplication.domain.usecases.water_record.WaterRecordUseCases
import com.biitutku.reminderapplication.util.Constants.WATER_RECORD_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

   @Provides
   @Singleton
   fun provideLocalUserManager(
      application: Application
   ): LocalUserManager = LocalUserManagerImp(application)


   @Provides
   @Singleton
   fun provideAppEntryUseCases(
      localUserManager: LocalUserManager
   ) = AppEntryUseCases(
      readAppEntry = ReadAppEntry(localUserManager),
      saveAppEntry = SaveAppEntry(localUserManager),

      readFirstOpening = ReadFirstOpening(localUserManager),
      saveFirstOpening = SaveFirstOpening(localUserManager),

      saveUserInfo = SaveUserInfo(localUserManager),
      readUserInfo = ReadUserInfo(localUserManager),
      saveUserCurrentTarget = SaveUserCurrentTarget(localUserManager),
      readUserCurrentTarget = ReadUserCurrentTarget(localUserManager),
      saveUserCurrentWeight = SaveUserCurrentWeight(localUserManager),
      readUserCurrentWeight = ReadUserCurrentWeight(localUserManager),
      saveUserGender = SaveUserGender(localUserManager),
      readUserGender = ReadUserGender(localUserManager),
      saveTimeToSleep = SaveTimeToSleep(localUserManager),
      readTimeToSleep = ReadTimeToSleep(localUserManager),
      saveTimeToWakeUp = SaveTimeToWakeUp(localUserManager),
      readTimeToWakeUp = ReadTimeToWakeUp(localUserManager)
   )

   @Provides
   @Singleton
   fun provideWaterRecordDatabase(
      application: Application
   ): WaterRecordDatabase {
      return Room.databaseBuilder(
         context = application,
         klass = WaterRecordDatabase::class.java,
         name = WATER_RECORD_DATABASE_NAME
      ).fallbackToDestructiveMigration()
         .build()
   }

   @Provides
   @Singleton
   fun provideWaterRecordDao(
      waterRecordDatabase: WaterRecordDatabase
   ): WaterRecordDao = waterRecordDatabase.waterRecordDao

   @Provides
   @Singleton
   fun provideWaterRecordUseCases(
      waterRecordDao: WaterRecordDao
   ): WaterRecordUseCases {
      return  WaterRecordUseCases(
         upsertWaterRecord = UpsertWaterRecord(waterRecordDao),
         deleteWaterRecord = DeleteWaterRecord(waterRecordDao),
         selectWaterRecord = SelectWaterRecord(waterRecordDao),
         selectWaterRecordWithMonth = SelectWaterRecordWithMonth(waterRecordDao),
         selectWaterRecordWithDay = SelectWaterRecordWithDay(waterRecordDao)
      )
   }


}