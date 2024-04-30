package com.biitutku.reminderapplication.domain.usecases.water_record

import com.biitutku.reminderapplication.data.local.WaterRecordDao
import com.biitutku.reminderapplication.data.model.WaterRecord
import kotlinx.coroutines.flow.Flow

class SelectWaterRecord(
   private val waterRecordDao: WaterRecordDao
) {
   operator fun invoke(): Flow<List<WaterRecord>> {
      return waterRecordDao.getWaterRecord()
   }
}