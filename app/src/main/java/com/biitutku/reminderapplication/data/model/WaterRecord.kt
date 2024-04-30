package com.biitutku.reminderapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WaterRecord(
   @PrimaryKey(autoGenerate = true) val id: Int = 0,
   val waterType: String,
   var description: String? = null,
   val capacity: Int,
   val waterImage: Int,
   var date: String,
   var time: String
)
