package com.biitutku.reminderapplication.screens.appmain.home


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.biitutku.reminderapplication.R
import com.biitutku.reminderapplication.app_object.AnalyticsManager
import com.biitutku.reminderapplication.components.AnimatedCircularProgressIndicator
import com.biitutku.reminderapplication.components.swipetodelete.SwipeToDeleteContainer
import com.biitutku.reminderapplication.data.model.WaterRecord
import com.biitutku.reminderapplication.screens.appmain.home.widget.MonthlyTargetDialog
import com.biitutku.reminderapplication.screens.appmain.home.widget.SelectableWaterDialog
import com.biitutku.reminderapplication.screens.appmain.setting.SettingViewModel
import com.biitutku.reminderapplication.ui.theme.color
import com.biitutku.reminderapplication.ui.theme.padding
import com.biitutku.reminderapplication.util.FabItem
import com.biitutku.reminderapplication.util.MultiFloatingActionButton
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import java.time.YearMonth
import kotlin.time.Duration.Companion.days


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomePage(
   state: HomePageState,
   viewModel: HomePageViewModel,
   settingViewModel: SettingViewModel,
   navHostController: NavHostController,
   ) {

   var showDialog by remember {
      mutableStateOf(false)
   }
   var showMonthlyTargetDialog by remember {
      mutableStateOf(false)
   }
   val firebaseAnalytics = Firebase.analytics

   LaunchedEffect(key1 = Unit) {
      viewModel.calculateDrinkingWater()
      viewModel.getWaterRecordWithMonth()
      viewModel.getWaterRecordWithDay()
      AnalyticsManager.logScreenView("ReminderApp-HomePage")
   }

   SelectableWaterDialog(
      showDialog = showDialog,
      onDismissRequest = { showDialog = false },
      content = {},
      onConfirm = { showDialog = false }
   )
   MonthlyTargetDialog(
      showDialog = showMonthlyTargetDialog,
      onDismissRequest = { showMonthlyTargetDialog = false },
      waterRecordList = state.waterRecordListWithMonth,
      maxTarget = settingViewModel.state.value.currentTarget
      /*content = {
         Text(text = "d")
      }*/
   )
   Scaffold(
      floatingActionButton = {
         MultiFloatingActionButton(
            fabIcon = painterResource(id = R.drawable.icon_add),
            items = arrayListOf(
               FabItem(
                  icon = painterResource(id = R.drawable.icon_close),
                  label = "Aylık Hedef Takibi"
               ) {
                  showMonthlyTargetDialog = true
               },
               FabItem(
                  icon = painterResource(id = R.drawable.icon_add),
                  label = "Kayıt Ekle"
               ) {
                  showDialog = true
               },
            ),
         )
      }
   ) {
      Column {
         Row(
            modifier = Modifier
               .fillMaxWidth()
               .padding(
                  top = MaterialTheme.padding.medium20,
                  bottom = MaterialTheme.padding.medium20,
               ),
            horizontalArrangement = Arrangement.Start
         ) {

               AnimatedCircularProgressIndicator(
                  currentValue = viewModel.drinkingWater.intValue,
                  maxValue = if(settingViewModel.state.value.currentTarget != null) settingViewModel.state.value.currentTarget!! else 0,
                  progressIndicatorColor = MaterialTheme.color.bodyTextColor,
                  progressBackgroundColor = MaterialTheme.color.barColor,
                  completedColor = MaterialTheme.color.bodyTextColor
               )

         }

         GunlukKayitlarimBaslik(
            settingViewModel = settingViewModel)
         CustomDivider()
         GunlukKayitlarimSu(waterRecord = state.waterRecordListWithDay, viewModel = viewModel)
      }
   }
}


@Composable
private fun GunlukKayitlarimBaslik(
   settingViewModel: SettingViewModel
) {
   Row(
      modifier = Modifier
         .fillMaxWidth()
         .padding(
            start = MaterialTheme.padding.large32,
            end = MaterialTheme.padding.large32,
            bottom = MaterialTheme.padding.medium16,
         ),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
   ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
         Image(
            modifier = Modifier.size(40.dp),
            painter = painterResource(id = R.drawable.ic_prize_badge),
            contentDescription = ""
         )
         Spacer(modifier = Modifier.width(20.dp))
         Text(
            text = "Günlük\nKayıtlarım",
            textAlign = TextAlign.Center
         )
      }
      Spacer(
         modifier = Modifier
            .width(1.dp)
            .height(40.dp)
            .background(MaterialTheme.color.barColor.copy(alpha = 0.3f))
      )
      Text(
         text = "Hedef \n${settingViewModel.state.value.currentTarget} mililitre",
         textAlign = TextAlign.Center,
         color = MaterialTheme.color.barColor,
         fontSize = 24.sp
      )
   }
}

@Composable
fun GunlukKayitlarimSu(
   waterRecord: List<WaterRecord>,
   viewModel: HomePageViewModel,

   ) {
   val yearMonth = YearMonth.now()
   val currentDay = yearMonth.monthValue.days


   LazyColumn(
      modifier = Modifier
         .fillMaxWidth()
         .padding(
            horizontal = MaterialTheme.padding.large32,
            vertical = MaterialTheme.padding.small8
         )
   ) {

      items(
         items = waterRecord,
         key = { it.id }
      ) { kayit ->
         SwipeToDeleteContainer(
            item = kayit,
            onDelete = { waterRecord ->
               viewModel.onEvent(Event.DeleteWaterRecord(waterRecord))
            }
         ) { waterRecord ->
            WaterRecordRow(waterRecord = waterRecord)
         }
      }
   }
}

@Composable
fun WaterRecordRow(
   waterRecord: WaterRecord
) {
   Row(
      modifier = Modifier
         .fillMaxWidth()
         .padding(
            bottom = MaterialTheme.padding.medium20
         ),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
   ) {
      Row {
         Image(
            modifier = Modifier.height(50.dp),
            painter = painterResource(waterRecord.waterImage),
            contentDescription = ""
         )
         Spacer(modifier = Modifier.width(20.dp))
         Text(
            text = waterRecord.description.toString(),
            textAlign = TextAlign.Center
         )
      }

      Row(
         verticalAlignment = Alignment.CenterVertically,
      ) {
         Text(text = waterRecord.time, textAlign = TextAlign.End)
         Spacer(modifier = Modifier.width(60.dp))

      }

   }
}


@Composable
private fun CustomDivider() {
   Row(
      modifier = Modifier
         .padding(
            start = MaterialTheme.padding.large32,
            end = MaterialTheme.padding.large32,
            bottom = MaterialTheme.padding.medium16,
         )

   ) {
      Spacer(
         modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(
               MaterialTheme.color.barColor.copy(
                  alpha = 0.3f
               )
            )
      )
   }
}


@Preview(showBackground = true)
@Composable
fun RecordWaterRow() {
   WaterRecordRow(
      WaterRecord(
         waterType = "TYPE",
         description = "100 ml \nLimonlu Su",
         capacity = 100,
         waterImage = R.drawable.ic_soda_cupple,
         date = "01:01:2018",
         time = "21:21"
      )
   )
}