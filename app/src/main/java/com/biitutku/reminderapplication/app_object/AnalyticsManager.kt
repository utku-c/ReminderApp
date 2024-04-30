package com.biitutku.reminderapplication.app_object

import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

object AnalyticsManager {

   fun logScreenView(screenName:String){
      val params = Bundle()
      params.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
      params.putString(FirebaseAnalytics.Param.SCREEN_CLASS, screenName)

      Firebase.analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW,params)
      Log.d("FirebaseLog","${FirebaseAnalytics.Event.SCREEN_VIEW} - ${FirebaseAnalytics.Param.SCREEN_NAME} - ${FirebaseAnalytics.Param.SCREEN_CLASS} - ${screenName}" )
   }
}