package com.biitutku.reminderapplication.screens.splash

import android.os.Bundle
import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.biitutku.reminderapplication.MainActivity
import com.biitutku.reminderapplication.R
import com.biitutku.reminderapplication.app_object.AnalyticsManager
import com.biitutku.reminderapplication.navigation.NavigationViewModel
import com.biitutku.reminderapplication.ui.theme.padding
import com.biitutku.reminderapplication.ui.ui_helper.isSmallScreenHeight
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
   navController: NavHostController,
   context: MainActivity,
   navigationViewModel: NavigationViewModel
) {

   val alpha = remember {
      Animatable(0f)
   }

   val firebaseAnalytics = Firebase.analytics

   LaunchedEffect(key1 = true) {
      /*Firebase.analytics.logEvent("menu_icon_clicked_utku",null)


      //Firebase.analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW,null)

      navController.addOnDestinationChangedListener { _, destination, _ ->
         val params = Bundle()
         params.putString(FirebaseAnalytics.Param.SCREEN_NAME, destination.route)
         firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, params)


         Log.d("FirebaseLog","${FirebaseAnalytics.Event.SCREEN_VIEW} - ${FirebaseAnalytics.Param.SCREEN_NAME} - ${destination.route}")
      }*/
      AnalyticsManager.logScreenView("ReminderApp-SplashScreen")
      alpha.animateTo(
         1f,
         animationSpec = tween(1500)
      )
      navigationViewModel.read()

      delay(2000)
      navController.popBackStack()
      navController.navigate(navigationViewModel.startDestination)
      /*appEntryUseCases.readAppEntry().onEach {
         if (it){
            navController.popBackStack()
            navController.navigate(Route.LoginScreen().name)
         }
         else {
            navController.popBackStack()
            navController.navigate(Route.OnboardScreen().name)

         }
      }*/


   }

   Column(
      modifier = Modifier
         .fillMaxSize()
         .background(if (isSystemInDarkTheme()) Color.DarkGray else Color.White)
         .padding(MaterialTheme.padding.small8),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
   ) {
      LoaderAnimation(
         modifier = Modifier.size(300.dp),
         anim = R.raw.water_alarm
      )
      Spacer(modifier = Modifier.height(25.dp))
      Text(
         text = "Günlük su\niçme\ntakipçisi",
         modifier = Modifier.alpha(alpha.value),
         style = MaterialTheme.typography.bodyLarge.copy(
            fontSize = if (isSmallScreenHeight()) {
               38.sp
            } else {
               54.sp
            }
         ),
         textAlign = TextAlign.Center,

         )
   }
}

@Composable
fun LoaderAnimation(modifier: Modifier, anim: Int) {
   val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(anim))

   LottieAnimation(
      composition = composition, iterations = LottieConstants.IterateForever,
      modifier = modifier
   )
}

/*
private fun onBoardingIsFinished(context: MainActivity): Boolean {
    val sharedPreferences = context.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
    return sharedPreferences.getBoolean("isFinished", false)

}*/
