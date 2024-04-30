package com.biitutku.reminderapplication.domain.usecases.water_record

import com.biitutku.reminderapplication.data.local.WaterRecordDao
import com.biitutku.reminderapplication.data.model.WaterRecord

class DeleteWaterRecord(private val waterRecordDao: WaterRecordDao) {
   suspend operator fun invoke(waterRecord: WaterRecord) {
      waterRecordDao.delete(waterRecord)
   }
}