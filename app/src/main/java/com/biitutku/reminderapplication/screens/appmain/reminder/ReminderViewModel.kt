package com.biitutku.reminderapplication.screens.appmain.reminder

import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.biitutku.reminderapplication.di.MainNotificationCompatBuilder
import com.biitutku.reminderapplication.di.SecondNotificationCompatBuilder
import com.biitutku.reminderapplication.domain.usecases.app_entry.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.notify
import javax.inject.Inject

@HiltViewModel
class ReminderViewModel @Inject constructor(
   @MainNotificationCompatBuilder
   private val notificationBuilder: NotificationCompat.Builder,
   @SecondNotificationCompatBuilder
   private val notificationBuilder2: NotificationCompat.Builder,
   private val notificationManager: NotificationManagerCompat
) : ViewModel() {


   fun showSimpleNotification() {
      notificationManager.notify(1, notificationBuilder.build())
   }

   fun cancelNotification() {
      notificationManager.cancel(1)
   }

   fun showProgress() {
      val max = 10
      var progress = 0
      viewModelScope.launch {
         while (progress != max) {
            delay(1000)
            progress += 1
            notificationManager.notify(
               3,
               notificationBuilder2
                  .setContentText("Indiriliyor")
                  .setContentTitle("${progress}/${max}")
                  .setProgress(max, progress, false).build()
            )
         }
         notificationManager.notify(
            3,
            notificationBuilder
               .setContentTitle("Tamamlandı")
               .setContentText("")
               .setContentIntent(null)
               .clearActions()
               .setProgress(0,0,false)
               .build()

         )
      }
   }
}