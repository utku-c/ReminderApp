package com.biitutku.reminderapplication.screens.appmain.setting

import android.os.Bundle
import android.util.Log

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.DateRange
import androidx.compose.material.icons.twotone.Email
import androidx.compose.material.icons.twotone.Face
import androidx.compose.material.icons.twotone.Info
import androidx.compose.material.icons.twotone.Notifications
import androidx.compose.material.icons.twotone.Share
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material.icons.twotone.Warning
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.biitutku.reminderapplication.app_object.AnalyticsManager
import com.biitutku.reminderapplication.components.CustomCardWithIconButton
import com.biitutku.reminderapplication.components.CustomCardWithTextButton
import com.biitutku.reminderapplication.components.LeaveASpace
import com.biitutku.reminderapplication.screens.appmain.setting.widget.DailyGoalDialog
import com.biitutku.reminderapplication.screens.appmain.setting.widget.GenderDialog
import com.biitutku.reminderapplication.screens.appmain.setting.widget.PolicyAndPrivacy
import com.biitutku.reminderapplication.screens.appmain.setting.widget.SelectTimeDialog
import com.biitutku.reminderapplication.screens.appmain.setting.widget.WeightDialog
import com.biitutku.reminderapplication.ui.theme.padding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

@Composable
fun SettingPage(
   navController: NavController,
   viewModel: SettingViewModel,
   event: (SettingEvent) -> Unit,
) {
   val state = rememberScrollState()
   val showDailyGoalDialog = rememberSaveable { mutableStateOf(false) }
   val showGenderDialog = rememberSaveable { mutableStateOf(false) }
   val showWeightDialog = rememberSaveable { mutableStateOf(false) }
   val showTimeToSleep = rememberSaveable { mutableStateOf(false) }
   val showTimeToWakeUp = rememberSaveable { mutableStateOf(false) }
   val policyAndPrivacyDialog = rememberSaveable { mutableStateOf(false) }



   LaunchedEffect(key1 = Unit) {
      AnalyticsManager.logScreenView("ReminderApp-SettingPage")
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
   if (policyAndPrivacyDialog.value) {
      PolicyAndPrivacy(
         setShowDialog = {
            policyAndPrivacyDialog.value = it
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
      modifier = Modifier
         .padding(
            vertical = MaterialTheme.padding.medium16,
            horizontal = MaterialTheme.padding.large32
         )
         .verticalScroll(state),

      ) {

      CustomCardWithIconButton(
         labelText = "Geçmiş",
         leadingIcon = Icons.TwoTone.DateRange,
         trailingIcon = {
            Log.i("CustomCardWithIconButton", "Geçmiş'e tıklandı")
         }
      )
      CustomCardWithTextButton(
         labelText = "Günlük hedef",
         leadingIcon = Icons.TwoTone.Star,
         trailingText = {
            showDailyGoalDialog.value = true
         },
         valueText = if (viewModel.state.value.currentTarget == null) "Seçilmedi" else "${viewModel.state.value.currentTarget.toString()}\nmililitre"

      )

      // divier'ın altından üstünden boşluk bırakır
      LeaveASpace()

      CustomCardWithTextButton(
         labelText = "Cinsiyet",
         leadingIcon = Icons.TwoTone.Face,
         trailingText = {
            showGenderDialog.value = true
            Log.i("CustomCardWithTextButton", "Günlük hedef'e tıklandı")
         },
         valueText = if (viewModel.state.value.gender == null) "Seçilmedi" else viewModel.state.value.gender.toString()
      )
      CustomCardWithTextButton(
         labelText = "Kilo",
         leadingIcon = Icons.TwoTone.Info,
         trailingText = {
            showWeightDialog.value = true
            Log.i("CustomCardWithTextButton", "Günlük hedef'e tıklandı")
         },
         valueText = if (viewModel.state.value.currentWeight == null) "Seçilmedi" else "${viewModel.state.value.currentWeight.toString()} kg"
      )
      CustomCardWithTextButton(
         labelText = "Uyanma vakti",
         leadingIcon = Icons.TwoTone.Notifications,
         trailingText = {
            showTimeToWakeUp.value = true
            Log.i("CustomCardWithTextButton", "Günlük hedef'e tıklandı")

         },
         valueText = if (viewModel.state.value.timeToWakeUp == null) "Seçilmedi" else viewModel.state.value.timeToWakeUp.toString()

      )
      CustomCardWithTextButton(
         labelText = "Uyuma vakti",
         leadingIcon = Icons.TwoTone.Notifications,
         trailingText = {
            showTimeToSleep.value = true
            Log.i("CustomCardWithTextButton", "Günlük hedef'e tıklandı")
         },
         valueText = if (viewModel.state.value.timeToSleep == null) "Seçilmedi" else viewModel.state.value.timeToSleep.toString()

      )

      // divier'ın altından üstünden boşluk bırakır
      LeaveASpace()

      CustomCardWithIconButton(
         labelText = "Paylaş",
         leadingIcon = Icons.TwoTone.Share,
         trailingIcon = {
            Log.i("CustomCardWithIconButton", "Tıkladnııııııı")
         }
      )
      CustomCardWithIconButton(
         labelText = "Geri Bildirim",
         leadingIcon = Icons.TwoTone.Email,
         trailingIcon = {
            Log.i("CustomCardWithIconButton", "Bize geri bildirim gönder")
         }
      )
      CustomCardWithIconButton(
         labelText = "Politika ve Gizlilik",
         leadingIcon = Icons.TwoTone.Warning,
         trailingIcon = {
            policyAndPrivacyDialog.value = true
         }
      )

   }
}


