package com.biitutku.reminderapplication.di

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.biitutku.reminderapplication.MainActivity
import com.biitutku.reminderapplication.R
import com.biitutku.reminderapplication.receiver.MyReceiver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {

   @Singleton
   @Provides
   @MainNotificationCompatBuilder
   fun provideNotificationBuilder(
      @ApplicationContext context: Context
   ): NotificationCompat.Builder {

      val intent = Intent(context, MyReceiver::class.java).apply {
         putExtra("MESSAGE", "Clicked")
      }
      val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
         PendingIntent.FLAG_IMMUTABLE
      } else {
         0
      }
      val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, flag)

      val clickIntent = Intent(context, MainActivity::class.java)
      val clickPendingIntent = PendingIntent.getActivity(
         context, 1, clickIntent, flag
      )
      return NotificationCompat.Builder(context, "Main Channel ID")
         .setContentTitle("Hatırlatıcım")
         .setContentText("Lorem Ipsum is simply dummy text of the printing and typesetting industry.")
         .setSmallIcon(R.drawable.app_icon_foreground)
         .setLargeIcon(
            BitmapFactory.decodeResource(
               context.resources,
               R.drawable.app_icon_foreground
            )
         )
         .setPriority(NotificationCompat.PRIORITY_DEFAULT)
         .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
         .setPublicVersion(
            NotificationCompat.Builder(context, "Main Channel ID")
               .setContentTitle("Hidden")
               .setContentText("Unlock to see the message.")
               .build()
         )
         .addAction(0, "Action", pendingIntent)
         .setContentIntent(clickPendingIntent)
   }



   @Singleton
   @Provides
   @SecondNotificationCompatBuilder
   fun provideSecondNotificationBuilder(
      @ApplicationContext context: Context
   ): NotificationCompat.Builder{
      return NotificationCompat.Builder(context,"Second Channel ID")
         .setSmallIcon(R.drawable.app_icon_foreground)
         .setPriority(NotificationCompat.PRIORITY_LOW)
         .setOngoing(true)
   }

   @Singleton
   @Provides
   fun provideNotificationManager(
      @ApplicationContext context: Context
   ): NotificationManagerCompat {
      val notificationManager = NotificationManagerCompat.from(context)
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
         val channel = NotificationChannel(
            "Main Channel ID",
            "Main Channel",
            NotificationManager.IMPORTANCE_DEFAULT
         )
         val channel2 = NotificationChannel(
            "Second Channel ID",
            "Second Channel",
            NotificationManager.IMPORTANCE_LOW
         )
         notificationManager.createNotificationChannel(channel)
         notificationManager.createNotificationChannel(channel2)
      }
      return notificationManager
   }


}
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainNotificationCompatBuilder

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SecondNotificationCompatBuilder