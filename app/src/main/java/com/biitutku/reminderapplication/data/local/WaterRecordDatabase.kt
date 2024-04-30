package com.biitutku.reminderapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.biitutku.reminderapplication.data.model.WaterRecord

@Database(entities = [WaterRecord::class], version = 2)
abstract class WaterRecordDatabase: RoomDatabase() {
   abstract val waterRecordDao: WaterRecordDao
}