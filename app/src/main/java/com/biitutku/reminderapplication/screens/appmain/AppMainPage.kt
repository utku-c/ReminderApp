package com.biitutku.reminderapplication.screens.appmain

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.biitutku.reminderapplication.app_object.AnalyticsManager
import com.biitutku.reminderapplication.navigation.Route
import com.biitutku.reminderapplication.screens.appmain.home.HomePage
import com.biitutku.reminderapplication.screens.appmain.home.HomePageViewModel
import com.biitutku.reminderapplication.screens.appmain.reminder.ReminderPage
import com.biitutku.reminderapplication.screens.appmain.setting.SettingPage
import com.biitutku.reminderapplication.screens.appmain.setting.SettingViewModel
import com.biitutku.reminderapplication.ui.theme.appTypography
import com.biitutku.reminderapplication.ui.theme.color
import com.biitutku.reminderapplication.ui.theme.padding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.logEvent
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun AppMainPage(
    navHostController: NavHostController,
    viewModel: SettingViewModel,
) {
    HomeApp(
        modifier = Modifier
            .background(MaterialTheme.color.backgroundColor),
        navHostController = navHostController,
        viewModel = viewModel
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeApp(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: SettingViewModel
) {
    val items = listOf(
        NavItemState(
            title = "Ana menü",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasBadge = false,
            messages = 0
        ),
        NavItemState(
            title = "Hatırlatıcı",
            selectedIcon = Icons.Filled.Notifications,
            unselectedIcon = Icons.Outlined.Notifications,
            hasBadge = false,
            messages = 0
        ),
        NavItemState(
            title = "Ayarlar",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            hasBadge = false,
            messages = 0
        )
    )

    var bottomNavState by rememberSaveable {
        mutableIntStateOf(0)
    }
    val scope = rememberCoroutineScope()


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier
                            .fillMaxWidth()
                            .height(MaterialTheme.padding.large50)
                            .background(MaterialTheme.color.barColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Güçlü kal",
                            style = appTypography.titleLarge.copy(
                                fontSize = 28.sp
                            ),
                            color = MaterialTheme.color.backgroundColor
                        )
                    }

                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            // AnalyticsManager.logScreenView("ReminderApp-AppMainPage")
                           // Firebase.analytics.logEvent("menu_icon_clicked_debugView",null)
                            Firebase.analytics.logEvent("button_clicked_3",null)
                           /* Firebase.analytics.logEvent("button_clicked") {
                                param("button_name", "Menu Iconuna Tıklandı")
                            }*/
                            Firebase.analytics.logEvent("button_clicked_ReminderApp") {
                                param("button_name", "Menu Iconuna Tıklandı")
                            }
                         /*   Firebase.analytics.logEvent("menu_icon_clicked_utku",null)


                            //Firebase.analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW,null)

                            navHostController.addOnDestinationChangedListener { _, destination, _ ->
                                val params = Bundle()
                                params.putString(FirebaseAnalytics.Param.SCREEN_NAME, destination.route)
                                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, params)


                                Log.d("FirebaseLog","${FirebaseAnalytics.Event.SCREEN_VIEW} - ${FirebaseAnalytics.Param.SCREEN_NAME} - ${destination.route}")
                            }
*/
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu icon",
                            tint = MaterialTheme.color.backgroundColor,
                        )
                    }

                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Outlined.FavoriteBorder,
                            contentDescription = "Fav icon",
                            tint = MaterialTheme.color.backgroundColor
                        )

                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.color.barColor,
                    titleContentColor = MaterialTheme.color.titleTextColor
                ),
                modifier = modifier
                    .clip(
                        RoundedCornerShape(
                            bottomEnd = MaterialTheme.padding.large40,
                            bottomStart = MaterialTheme.padding.large40
                        )
                    ),
            )
        },
        bottomBar = {
            NavigationBar(
                modifier
                    .clip(
                        RoundedCornerShape(
                            topStart = MaterialTheme.padding.large32,
                            topEnd = MaterialTheme.padding.large40,
                        )
                    )
                    .height(MaterialTheme.padding.extraLarge80),
                containerColor = MaterialTheme.color.barColor
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = bottomNavState == index,
                        onClick = { bottomNavState = index },
                        icon = {
                            BadgedBox(badge = {
                                if (item.hasBadge) Badge {}
                                if (item.messages != 0) Badge {
                                    Text(
                                        text = "${item.messages}",
                                        style = appTypography.titleSmall
                                    )
                                }
                            }) {
                                Icon(
                                    imageVector = if (bottomNavState == index) item.selectedIcon
                                    else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            }

                        },
                        label = {
                            Text(
                                text = item.title,
                                style = appTypography.titleSmall,

                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.color.barColor,
                            selectedTextColor = MaterialTheme.color.backgroundColor,
                            indicatorColor = MaterialTheme.color.backgroundColor,
                            unselectedTextColor = MaterialTheme.color.backgroundColor,
                            unselectedIconColor = MaterialTheme.color.backgroundColor,
                        )
                    )
                }
            }
        },

        ) { contentPadding ->
        Column(
            modifier
                .padding(contentPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            when (bottomNavState) {
                0 -> {
                    val homeViewModel : HomePageViewModel = hiltViewModel()

                    HomePage(
                        state = homeViewModel.state.value,
                        viewModel = homeViewModel,
                        settingViewModel = viewModel,
                        navHostController = navHostController

                    )
                }

                1 -> {

                    ReminderPage()
                }

                2 -> {
                    SettingPage(
                        navController = navHostController,
                        viewModel = viewModel,
                        event = viewModel::onEvent
                    )
                }
            }
        }
    }

}

data class NavItemState(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasBadge: Boolean,
    val messages: Int
)