package com.biitutku.reminderapplication.screens.onboard

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.biitutku.reminderapplication.MainActivity
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.biitutku.reminderapplication.R
import com.biitutku.reminderapplication.app_object.AnalyticsManager
import com.biitutku.reminderapplication.components.CustomAppButton
import com.biitutku.reminderapplication.navigation.Route
import com.biitutku.reminderapplication.ui.theme.color
import com.biitutku.reminderapplication.ui.theme.padding
import com.biitutku.reminderapplication.ui.theme.spacing
import com.biitutku.reminderapplication.ui.ui_helper.isSmallScreenHeight
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardScreen(
    navController: NavHostController,
    context: MainActivity,
    event: (OnBoardingEvent) -> Unit,
) {
    val animations = listOf(
        R.raw.lottie_intro1,
        R.raw.lottie_intro4,
        R.raw.lottie_intro2,
        R.raw.lottie_intro3
    )
    val titles = listOf(
        "Su iç ve daha iyi yaşa",
        "Su içmek için bir alarm ayarlayın",
        "Su iç ve daha iyi yaşa",
        "Su iç ve daha iyi yaşa"
    )

    val descriptions = listOf(
        "Günlük su içme hedefinizi belirleyin ve izleyin",
        "Su tüketim alışkanlıklarınızı günlük ve aylık olarak takip edin.",
        "Hatırlatmalarla su içme alışkanlığınızı geliştirin.",
        "İstatistikler ve grafikler aracılığıyla ilerlemenizi izleyin"
    )
    val pagerState = rememberPagerState(
        pageCount = animations.size
    )
    val firebaseAnalytics = Firebase.analytics

    LaunchedEffect(key1 = Unit) {
        AnalyticsManager.logScreenView("ReminderApp-OnboardPage")

    }


    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HorizontalPager(
            state = pagerState,
            Modifier.wrapContentSize()
        ) { currentPage ->
            Column(
                Modifier
                    .wrapContentSize()
                    .padding(26.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animations[currentPage]))
                if (isSmallScreenHeight()) {
                    LottieAnimation(
                        composition = composition,
                        iterations = LottieConstants.IterateForever,
                        modifier = Modifier.size(230.dp)
                    )
                } else {
                    LottieAnimation(
                        composition = composition,
                        iterations = LottieConstants.IterateForever,
                        modifier = Modifier.size(320.dp)
                    )
                }
                Text(
                    text = titles[currentPage],
                    textAlign = TextAlign.Center,
                    fontSize = 44.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = descriptions[currentPage],
                    Modifier.padding(top = 45.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp

                )
            }
        }

        PageIndicator(
            pageCount = animations.size,
            currentPage = pagerState.currentPage,
            modifier = Modifier.padding(40.dp)
        )
    }

    ButtonsSection(
        pagerState = pagerState,
        navController = navController,
        context = context,
        event = event
    )

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ButtonsSection(
    pagerState: PagerState,
    navController: NavHostController,
    context: MainActivity,
    event: (OnBoardingEvent) -> Unit,

) {

    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.padding.large32)
    ) {
        if (pagerState.currentPage != 3) {
            Text(
                text = "İlerle",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .clickable {
                        scope.launch {
                            val nextPage = pagerState.currentPage + 1
                            pagerState.scrollToPage(nextPage)
                        }
                    },
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            if (pagerState.currentPage != 0){
                Text(
                    text = "Geri",
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .clickable {
                            scope.launch {
                                val prevPage = pagerState.currentPage - 1
                                if (prevPage >= 0) {
                                    pagerState.scrollToPage(prevPage)
                                }
                            }
                        },
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        } else {
            CustomAppButton(
                buttonTitle ="Hadi Başlayalım..." ,
                onButtonClick = {
                    event(OnBoardingEvent.SaveAppEntry)
                    navController.popBackStack()
                    navController.navigate(Route.FirstOpeningScreen().name)
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter),
            )
        }
    }
}

@Composable
fun PageIndicator(pageCount: Int, currentPage: Int, modifier: Modifier) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        repeat(pageCount) {
            IndicatorSingleDot(isSelected = it == currentPage)
        }


    }
}

@Composable
fun IndicatorSingleDot(isSelected: Boolean) {

    val width = animateDpAsState(targetValue = if (isSelected) 35.dp else 15.dp, label = "")
    Box(
        modifier = Modifier
            .padding(MaterialTheme.padding.default0 + 2.dp)
            .height(MaterialTheme.spacing.medium)
            .width(width.value)
            .clip(CircleShape)
            .background(if (isSelected) MaterialTheme.color.barColor else MaterialTheme.color.barColor.copy(alpha = 0.2f))
    )
}

/*
private fun onBoardingIsFinished(context: MainActivity) {
    val sharedPreferences = context.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putBoolean("isFinished", true)
    editor.apply()
}*/
