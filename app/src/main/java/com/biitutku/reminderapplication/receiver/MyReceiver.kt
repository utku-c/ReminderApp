package com.biitutku.reminderapplication.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.biitutku.reminderapplication.components.toast.ReminderToastMessage
import com.biitutku.reminderapplication.components.toast.toastTypes.ReminderToastTypeSuccess

class MyReceiver: BroadcastReceiver() {

   override fun onReceive(context: Context?, intent: Intent?) {
      val message = intent?.getStringExtra("MESSAGE")
      if (message != null){
         //ReminderToastMessage(message = message, type = ReminderToastTypeSuccess())
         Toast.makeText(context,message,Toast.LENGTH_LONG).show()
      }
   }
}