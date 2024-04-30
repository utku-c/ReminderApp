package com.biitutku.reminderapplication.screens.appmain.home

import com.biitutku.reminderapplication.data.model.WaterRecord

sealed class Event{

   data class UpsertWaterRecord(val waterRecord: WaterRecord) : Event()
   data class DeleteWaterRecord(val waterRecord: WaterRecord) : Event()

   data object RemoveSideEffect : Event()
}