package com.biitutku.reminderapplication.screens.appmain.home

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.biitutku.reminderapplication.data.model.WaterRecord
import com.biitutku.reminderapplication.domain.usecases.water_record.WaterRecordUseCases
import com.biitutku.reminderapplication.util.DATA_FORMATTER_DATE_YYYY_MM
import com.biitutku.reminderapplication.util.DATA_FORMATTER_DATE_YYYY_MM_DD
import com.biitutku.reminderapplication.util.formatDateTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
   private val waterRecordUseCases: WaterRecordUseCases,
) : ViewModel() {
   private val _state = mutableStateOf(HomePageState())
   val state: State<HomePageState> = _state


   private var _drinkingWater = mutableIntStateOf(value = 0)
   var drinkingWater = mutableIntStateOf(_drinkingWater.intValue)


   var sideEffect by mutableStateOf<String?>(null)
      private set


   fun calculateDrinkingWater() {
      val currentDateTime = LocalDateTime.now()
      val formattedDate = currentDateTime.formatDateTime(DATA_FORMATTER_DATE_YYYY_MM_DD)

      viewModelScope.launch {
         _drinkingWater.intValue = 0
         val waterRecords = waterRecordUseCases.selectWaterRecordWithDay(formattedDate).firstOrNull()
         waterRecords?.forEach {
            _drinkingWater.intValue += it.capacity
         }
         drinkingWater.intValue = _drinkingWater.intValue
      }
   }

   fun getWaterRecord() {
     waterRecordUseCases.selectWaterRecord().onEach {
        _state.value.waterRecordList = it

     }.launchIn(viewModelScope)
  }

   fun getWaterRecordWithMonth(){
      val currentDateTime = LocalDateTime.now()
      val formattedDate = currentDateTime.formatDateTime(DATA_FORMATTER_DATE_YYYY_MM)
      Log.d("formattedDate",formattedDate)

      waterRecordUseCases.selectWaterRecordWithMonth(formattedDate).onEach {
        if (it != null){
           _state.value.waterRecordListWithMonth = it
        }
      }.launchIn(viewModelScope)

   }


   fun getWaterRecordWithDay(){
      val currentDateTime = LocalDateTime.now()
      val formattedDate = currentDateTime.formatDateTime(DATA_FORMATTER_DATE_YYYY_MM_DD)
      Log.d("formattedDate",formattedDate)

      waterRecordUseCases.selectWaterRecordWithDay(formattedDate).onEach {
         if (it != null){
            _state.value.waterRecordListWithDay = it
         }
      }.launchIn(viewModelScope)
   }

   fun onEvent(event: Event) {
      when (event) {
         is Event.UpsertWaterRecord -> {
            viewModelScope.launch {
               waterRecordUseCases.upsertWaterRecord(waterRecord = event.waterRecord)
               sideEffect = "Kayıt oluştu."
               calculateDrinkingWater()
            }
         }

         is Event.DeleteWaterRecord -> {
            viewModelScope.launch {
               waterRecordUseCases.deleteWaterRecord(waterRecord = event.waterRecord)
               sideEffect = "Kayıt Silindi."
               calculateDrinkingWater()
            }
         }

         is Event.RemoveSideEffect -> {
            sideEffect = null
         }
      }
   }


}