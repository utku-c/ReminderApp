package com.biitutku.reminderapplication.screens.onboard

sealed class OnBoardingEvent {
   data object SaveAppEntry: OnBoardingEvent()
}