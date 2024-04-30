package com.biitutku.reminderapplication.domain.usecases.water_record

import androidx.compose.runtime.collectAsState
import com.biitutku.reminderapplication.data.local.WaterRecordDao
import com.biitutku.reminderapplication.data.model.WaterRecord
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class SelectWaterRecordWithMonth(
   private val waterRecordDao: WaterRecordDao
) {
   operator fun invoke(month:String): Flow<List<WaterRecord>?> {

/*
      val waterRecordList: List<WaterRecord> = waterRecordDao.getWaterRecordWithMonth(month)

      val dateGrouping = waterRecordList.groupBy {
         it.date
      }
      val sortedDateGrouping = dateGrouping.entries.sortedBy { LocalDate.parse(it.key) }
*/

      return waterRecordDao.getWaterRecordWithMonth(month)
   }
}
