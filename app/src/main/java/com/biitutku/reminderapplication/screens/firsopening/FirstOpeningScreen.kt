package com.biitutku.reminderapplication.screens.firsopening

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.biitutku.reminderapplication.R
import com.biitutku.reminderapplication.components.CustomAppButton
import com.biitutku.reminderapplication.navigation.Route
import com.biitutku.reminderapplication.screens.appmain.setting.SettingEvent
import com.biitutku.reminderapplication.screens.appmain.setting.SettingViewModel
import com.biitutku.reminderapplication.screens.appmain.setting.widget.DailyGoalDialog
import com.biitutku.reminderapplication.screens.appmain.setting.widget.GenderDialog
import com.biitutku.reminderapplication.screens.appmain.setting.widget.SelectTimeDialog
import com.biitutku.reminderapplication.screens.appmain.setting.widget.WeightDialog
import com.biitutku.reminderapplication.ui.theme.color
import com.biitutku.reminderapplication.ui.theme.padding

@Composable
fun FirstOpeningScreen(
   viewModel: SettingViewModel,
   event: (SettingEvent) -> Unit,
   navController: NavHostController,
) {

   val showDailyGoalDialog = rememberSaveable { mutableStateOf(false) }
   val showGenderDialog = rememberSaveable { mutableStateOf(false) }
   val showWeightDialog = rememberSaveable { mutableStateOf(false) }
   val showTimeToSleep = rememberSaveable { mutableStateOf(false) }
   val showTimeToWakeUp = rememberSaveable { mutableStateOf(false) }

   fun isFieldsNotEmpty(): Boolean{
      var isOkay = false
      if (viewModel.state.value.gender != null && viewModel.state.value.gender!!.isNotEmpty() &&
         viewModel.state.value.currentTarget != null && viewModel.state.value.currentTarget!! > 0 &&
         viewModel.state.value.currentWeight != null && viewModel.state.value.currentWeight!!> 0 &&
         viewModel.state.value.timeToSleep != null && viewModel.state.value.timeToSleep!!.isNotEmpty() &&
         viewModel.state.value.timeToWakeUp != null && viewModel.state.value.timeToWakeUp!!.isNotEmpty()){
         isOkay = true
      }
      return isOkay
   }


   if (showDailyGoalDialog.value) {
      DailyGoalDialog(
         value = "",
         setShowDialog = {
            showDailyGoalDialog.value = it
         },
         setValue = {
            viewModel.state.value.currentTarget = this.toInt()
            event(SettingEvent.SaveUserCurrentTarget)
         }
      )
   }
   if (showGenderDialog.value) {
      GenderDialog(
         setShowDialog = {
            showGenderDialog.value = it
         },
         setGender = {
            viewModel.state.value.gender = it
            event(SettingEvent.SaveUserGender)
         }
      )
   }

   if (showWeightDialog.value) {
      WeightDialog(
         value = "",
         setShowDialog = {
            showWeightDialog.value = it
         },
         setValue = {
            viewModel.state.value.currentWeight = it.toInt()
            event(SettingEvent.SaveUserCurrentWeight)
         }
      )
   }

   if (showTimeToSleep.value){
      SelectTimeDialog(
         title = "Uyuma Vaktinizi Seçin",
         setShowDialog = {
            showTimeToSleep.value = it
         },
         setValue = {
            viewModel.state.value.timeToSleep = it
            event(SettingEvent.SaveUserTimeToSleep)

         }
      )
   }
   if (showTimeToWakeUp.value){
      SelectTimeDialog(
         title = "Uyanma Vaktinizi Seçin",
         setShowDialog = {
            showTimeToWakeUp.value = it
         },
         setValue = {
            viewModel.state.value.timeToWakeUp = it
            event(SettingEvent.SaveUserTimeToWakeUp)

         }
      )
   }
   Column(
      modifier = Modifier.padding(
         horizontal = MaterialTheme.padding.medium24,
         vertical = MaterialTheme.padding.medium16,
      ),
      verticalArrangement = Arrangement.SpaceBetween
   ) {
      Column {
         Text(
            text = "Uygulamaya başlamadan bu bilgileri doldurmalısın 😃",
            style = MaterialTheme.typography.titleLarge.copy(
               fontSize = 28.sp
            ),
            color = MaterialTheme.color.barColor
         )
         Spacer(modifier = Modifier.height(30.dp))

         // Günlük Hedef
         Row(
            modifier = Modifier
               .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
         ) {
            Text(
               text = "Günlük Hedef",
               style = MaterialTheme.typography.titleMedium,
               color = MaterialTheme.color.barColor
            )

            TextButton(
               onClick = {
                  showDailyGoalDialog.value = true
               }
            ) {
               Text(
                  text = if (viewModel.state.value.currentTarget == null) stringResource(R.string.secilmedi) else "${viewModel.state.value.currentTarget.toString()}\nmililitre",
                  style = MaterialTheme.typography.titleMedium.copy(
                     fontSize = 20.sp
                  ),
                  color = MaterialTheme.color.barColor
               )
            }
         }

         // Kilo
         Row(
            modifier = Modifier
               .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
         ) {
            Text(
               text = "Kilonuz",
               style = MaterialTheme.typography.titleMedium,
               color = MaterialTheme.color.barColor
            )

            TextButton(
               onClick = {
                  showWeightDialog.value = true
               }
            ) {
               Text(
                  text = if (viewModel.state.value.currentWeight == null) stringResource(R.string.secilmedi) else "${viewModel.state.value.currentWeight.toString()} kg",
                  style = MaterialTheme.typography.titleMedium.copy(
                     fontSize = 20.sp
                  ),
                  color = MaterialTheme.color.barColor
               )
            }
         }

         // Cinsiyet
         Row(
            modifier = Modifier
               .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
         ) {
            Text(
               text = "Cinsiyet",
               style = MaterialTheme.typography.titleMedium,
               color = MaterialTheme.color.barColor
            )

            TextButton(
               onClick = {
                  showGenderDialog.value = true
               }
            ) {
               Text(
                  text = if (viewModel.state.value.gender == null) stringResource(R.string.secilmedi) else viewModel.state.value.gender.toString(),
                  style = MaterialTheme.typography.titleMedium.copy(
                     fontSize = 20.sp
                  ),
                  color = MaterialTheme.color.barColor
               )
            }
         }

         // Uyanma Vakti
         Row(
            modifier = Modifier
               .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
         ) {
            Text(
               text = stringResource(R.string.uyanma_vakti),
               style = MaterialTheme.typography.titleMedium,
               color = MaterialTheme.color.barColor
            )

            TextButton(
               onClick = {
                  showTimeToWakeUp.value = true
               }
            ) {
               Text(
                  text = if (viewModel.state.value.timeToWakeUp == null) stringResource(R.string.secilmedi) else viewModel.state.value.timeToWakeUp.toString(),
                  style = MaterialTheme.typography.titleMedium.copy(
                     fontSize = 20.sp
                  ),
                  color = MaterialTheme.color.barColor
               )
            }
         }


         // Uyuma Vakti
         Row(
            modifier = Modifier
               .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
         ) {
            Text(
               text = "Uyuma Vakti",
               style = MaterialTheme.typography.titleMedium,
               color = MaterialTheme.color.barColor
            )

            TextButton(
               onClick = {
                  showTimeToSleep.value = true
               }
            ) {
               Text(
                  text = if (viewModel.state.value.timeToSleep == null) stringResource(R.string.secilmedi) else viewModel.state.value.timeToSleep.toString(),
                  style = MaterialTheme.typography.titleMedium.copy(
                     fontSize = 20.sp
                  ),
                  color = MaterialTheme.color.barColor
               )
            }
         }

      }
      CustomAppButton(
         buttonTitle = "Devam Et",
         enable = isFieldsNotEmpty(),
         onButtonClick = {
            navController.popBackStack()
            navController.navigate(Route.LoginScreen().name)
         }
      )

   }
}