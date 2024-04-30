package com.biitutku.reminderapplication.screens.appmain.home


import com.biitutku.reminderapplication.data.model.WaterRecord

data class HomePageState(
   var waterRecordList: List<WaterRecord> = emptyList(),
   var waterRecordListWithMonth: List<WaterRecord> = emptyList(),
   var waterRecordListWithDay: List<WaterRecord> = emptyList(),
)
