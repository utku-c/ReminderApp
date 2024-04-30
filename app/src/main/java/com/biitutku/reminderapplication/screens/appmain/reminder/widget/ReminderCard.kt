package com.biitutku.reminderapplication.screens.appmain.reminder.widget

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.biitutku.reminderapplication.ui.theme.color
import com.biitutku.reminderapplication.ui.theme.padding
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockSelection

@Composable
fun ReminderCard(
    modifier: Modifier,
    alarmClock: String,
    checked: Boolean = false,
    onCheckedChange: ((Boolean) -> Unit)?,
) {



    Column {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(shape = RoundedCornerShape(10.dp)),
        ) {
            Row(
                modifier = modifier
                    .background(MaterialTheme.color.barColor.copy(alpha = 0.1f))
                    .fillMaxSize()
                    .padding(MaterialTheme.padding.medium16),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = alarmClock,
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 36.sp)
                    )
                    Text(text = "pm")
                }
                Switch(
                    checked = checked,
                    onCheckedChange = onCheckedChange,
                    thumbContent = if (checked) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = null,
                                modifier = Modifier.size(SwitchDefaults.IconSize),
                            )
                        }
                    } else {
                        null
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = MaterialTheme.color.barColor,
                        checkedIconColor = MaterialTheme.color.backgroundColor,
                        checkedTrackColor = MaterialTheme.color.barColor.copy(
                            alpha = 0.2f
                        ),
                        uncheckedBorderColor = MaterialTheme.color.barColor.copy(
                            alpha = 0.6f
                        ),
                        uncheckedThumbColor = MaterialTheme.color.barColor.copy(
                            alpha = 0.6f
                        )
                    )

                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
    }

}


/*
*  Otomatik hatırlatıcı
* */

@Composable
fun AutoReminderCard(
    modifier: Modifier,
    checked: Boolean = false,
    onCheckedChange: ((Boolean) -> Unit)?,

    ) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(shape = RoundedCornerShape(10.dp)),
    ) {
        Row(
            modifier = modifier
                .background(MaterialTheme.color.barColor.copy(alpha = 0.1f))
                .fillMaxSize()
                .padding(MaterialTheme.padding.medium16),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "30 Dakika",
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 24.sp)
                )
                Text(text = "Otomatik")
            }
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                thumbContent = if (checked) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = null,
                            modifier = Modifier.size(SwitchDefaults.IconSize),
                        )
                    }
                } else {
                    null
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.color.barColor,
                    checkedIconColor = MaterialTheme.color.backgroundColor,
                    checkedTrackColor = MaterialTheme.color.barColor.copy(
                        alpha = 0.2f
                    ),
                    uncheckedBorderColor = MaterialTheme.color.barColor.copy(
                        alpha = 0.6f
                    ),
                    uncheckedThumbColor = MaterialTheme.color.barColor.copy(
                        alpha = 0.6f
                    )
                )

            )
        }
    }
}


/*

thumbContent = if (checked) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = null,
                            modifier = Modifier.size(SwitchDefaults.IconSize),
                        )
                    }
                } else {
                    null
                }


*/