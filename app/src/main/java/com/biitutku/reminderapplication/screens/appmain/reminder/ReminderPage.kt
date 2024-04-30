package com.biitutku.reminderapplication.screens.appmain.reminder


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import com.biitutku.reminderapplication.screens.appmain.reminder.widget.AutoReminderCard
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.twotone.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.biitutku.reminderapplication.app_object.AnalyticsManager
import com.biitutku.reminderapplication.components.LeaveASpace
import com.biitutku.reminderapplication.screens.appmain.reminder.widget.ReminderCard
import com.biitutku.reminderapplication.screens.appmain.setting.SettingEvent
import com.biitutku.reminderapplication.screens.appmain.setting.widget.SelectTimeDialog
import com.biitutku.reminderapplication.ui.theme.appTypography
import com.biitutku.reminderapplication.ui.theme.color
import com.biitutku.reminderapplication.ui.theme.padding
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ReminderPage(
    reminderViewModel: ReminderViewModel = hiltViewModel()
) {
    var autoAlarmCheckBox by rememberSaveable {
        mutableStateOf(false)
    }
    var clockHours by rememberSaveable {
        mutableIntStateOf(-1)
    }
    var clockMinute by rememberSaveable {
        mutableIntStateOf(-1)
    }

    val snackbarHostSate = remember{
        SnackbarHostState()
    }

    val addAlarmDialog = rememberSaveable { mutableStateOf(false) }

    val snackbarScope = rememberCoroutineScope()

    val clockState = rememberSheetState()
    /*ClockDialog(
        state = clockState,
        config = ClockConfig(
            is24HourFormat = true
        ),
        selection = ClockSelection.HoursMinutes { hours, minutes ->
            clockHours = hours
            clockMinute = minutes
            Log.d("SelectedDate","clockHours:$clockHours - clockMinute:$clockMinute")
        },
    )*/
    LaunchedEffect(key1 = Unit) {
        AnalyticsManager.logScreenView("ReminderApp-ReminderPage")
    }
    if (addAlarmDialog.value){
        SelectTimeDialog(
            title = "Alarım Ekle",
            setShowDialog = {
                addAlarmDialog.value = it
            },
            setValue = {
            }
        )
    }


    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostSate)
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                containerColor = MaterialTheme.color.barColor,
                shape = RoundedCornerShape(20.dp),
                onClick = reminderViewModel::showSimpleNotification
                /*onClick = {
                    addAlarmDialog.value = true
                 *//*
                 clockState.show()

                    snackbarScope.launch {
                        snackbarHostSate.showSnackbar("Eklendi")
                    }
                 *//*
                }*/
            ) {
                Text(
                    text = "Alarım Ekle",
                    style = appTypography.bodyLarge,
                    color = MaterialTheme.color.backgroundColor,
                )
            }
        }
    ) {

        val state = rememberScrollState()
        Column(
            modifier = Modifier.padding(
                horizontal = MaterialTheme.padding.medium16,
            )
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            AutoReminderCard(
                modifier = Modifier,
                checked = autoAlarmCheckBox,
                onCheckedChange = {
                    autoAlarmCheckBox = !autoAlarmCheckBox
                }
            )
            LeaveASpace()
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(state)
            ) {

                Column {
                    /*Button(onClick = reminderViewModel::showProgress) {
                        Text(text = "İndirme işlemi için tıkla")
                    }*/
                    /*ReminderCard(
                        modifier = Modifier,
                        alarmClock = "10.00",
                        onCheckedChange = null
                    )
                    ReminderCard(
                        modifier = Modifier,
                        alarmClock = "10.30",
                        onCheckedChange = {

                        }
                    )*/
                }
            }

        }
    }
}




