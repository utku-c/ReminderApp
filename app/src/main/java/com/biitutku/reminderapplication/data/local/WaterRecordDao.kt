package com.biitutku.reminderapplication.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.biitutku.reminderapplication.data.model.WaterRecord
import kotlinx.coroutines.flow.Flow
import java.time.Month

@Dao
interface WaterRecordDao {
   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun upsert(waterRecord: WaterRecord)

   @Delete
   suspend fun delete(waterRecord: WaterRecord)

   @Query("SELECT * FROM WaterRecord")
   fun getWaterRecord() : Flow<List<WaterRecord>>

   @Query("SELECT * FROM WaterRecord Where SUBSTR(date, 1, 7) = :month")
   fun getWaterRecordWithMonth(month: String) : Flow<List<WaterRecord>>

   @Query("SELECT * FROM WaterRecord Where SUBSTR(date, 1, 10) = :day")
   fun getWaterRecordWithDay(day: String) : Flow<List<WaterRecord>>

}