package com.biitutku.reminderapplication.domain.usecases.water_record

data class WaterRecordUseCases(
   val upsertWaterRecord: UpsertWaterRecord,
   val deleteWaterRecord: DeleteWaterRecord,
   val selectWaterRecord: SelectWaterRecord,
   val selectWaterRecordWithMonth: SelectWaterRecordWithMonth,
   val selectWaterRecordWithDay: SelectWaterRecordWithDay,
)
