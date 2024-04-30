package com.biitutku.reminderapplication.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.biitutku.reminderapplication.MainActivity
import com.biitutku.reminderapplication.screens.appmain.AppMainPage
import com.biitutku.reminderapplication.screens.appmain.setting.SettingPage
import com.biitutku.reminderapplication.screens.appmain.setting.SettingViewModel
import com.biitutku.reminderapplication.screens.firsopening.FirstOpeningScreen
import com.biitutku.reminderapplication.screens.login.LoginScreen
import com.biitutku.reminderapplication.screens.login.LoginViewModel
import com.biitutku.reminderapplication.screens.onboard.OnBoardingViewModel
import com.biitutku.reminderapplication.screens.onboard.OnboardScreen
import com.biitutku.reminderapplication.screens.signup.policyandpricacy.PolicyScreen
import com.biitutku.reminderapplication.screens.signup.policyandpricacy.PricacyScreen
import com.biitutku.reminderapplication.screens.signup.SignUpScreen
import com.biitutku.reminderapplication.screens.signup.SignUpViewModel
import com.biitutku.reminderapplication.screens.splash.SplashScreen

sealed class Route {
   data class SplashScreen(val name: String = "Splash") : Route()
   data class OnboardScreen(val name: String = "Onboard") : Route()
   data class AppMainScreen(val name: String = "AppMain") : Route()
   data class LoginScreen(val name: String = "Login") : Route()
   data class FirstOpeningScreen(val name: String = "FirstOpening") : Route()
   data class SignUpScreen(val name: String = "SignUp") : Route()
   data class PrivacyScreen(val name: String = "Privacy") : Route()
   data class PolicyScreen(val name: String = "Policy") : Route()
   data class HomeScreen(val name: String = "HomePage") : Route()
   data class SettingScreen(val name: String = "SettingPage") : Route()
   data class ReminderScreen(val name: String = "ReminderPage") : Route()
}


@Composable
fun AppNavigation(
   navHostController: NavHostController,
   context: MainActivity,
   navigationViewModel: NavigationViewModel = hiltViewModel()
) {

   NavHost(
      navController = navHostController,
      startDestination = "x",
   ) {

      navigation(
         startDestination = navigationViewModel.startDestination,
         route = "x"
      ) {
         composable(Route.SplashScreen().name) {
            SplashScreen(
               navController = navHostController,
               context = context,
               navigationViewModel = navigationViewModel
            )
         }
         composable(Route.OnboardScreen().name) {
            val viewModel: OnBoardingViewModel = hiltViewModel()

            OnboardScreen(
               navController = navHostController,
               context = context,
               event = viewModel::onEvent,
               /*event = {
                   viewModel.onEvent(it)
               }*/
            )
         }
         composable(route = Route.FirstOpeningScreen().name) {
            val viewModel: SettingViewModel = hiltViewModel()
            FirstOpeningScreen(
               navController = navHostController,
               viewModel = viewModel,
               event = viewModel::onEvent
            )
         }
         composable(route = Route.LoginScreen().name) {
            val viewModel: LoginViewModel = hiltViewModel()
            LoginScreen(
               navController = navHostController,
               viewModel = viewModel,
               event = viewModel::onEvent
            )
         }

         composable(route = Route.SignUpScreen().name) {
            val viewModel: SignUpViewModel = hiltViewModel()
            SignUpScreen(
               navController = navHostController,
               viewModel = viewModel,
               event = viewModel::onEvent
            )
         }
         composable(route = Route.PolicyScreen().name) {
            PolicyScreen(navHostController)
         }
         composable(route = Route.PrivacyScreen().name) {
            PricacyScreen(navHostController)
         }
         composable(route = Route.SettingScreen().name) {
            val viewModel: SettingViewModel = hiltViewModel()
            SettingPage(
               navController = navHostController,
               viewModel = viewModel,
               event = viewModel::onEvent
            )
         }
         composable(
            route = Route.AppMainScreen().name,
         ) {
            val viewModel: SettingViewModel = hiltViewModel()
            AppMainPage(
               navHostController = navHostController,
               viewModel=viewModel
            )
         }
      }

   }
}

fun NavController.navigateToSingleTop(route: String) {
   navigate(route) {
      popUpTo(graph.findStartDestination().id) {
         saveState = true
      }
      launchSingleTop = true
      restoreState = true
   }
}