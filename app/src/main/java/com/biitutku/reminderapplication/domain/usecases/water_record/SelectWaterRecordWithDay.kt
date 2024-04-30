package com.biitutku.reminderapplication.domain.usecases.water_record

import com.biitutku.reminderapplication.data.local.WaterRecordDao
import com.biitutku.reminderapplication.data.model.WaterRecord
import kotlinx.coroutines.flow.Flow

class SelectWaterRecordWithDay(
   private val waterRecordDao: WaterRecordDao
) {
   operator fun invoke(day:String): Flow<List<WaterRecord>?> {
      return waterRecordDao.getWaterRecordWithDay(day)
   }
}
