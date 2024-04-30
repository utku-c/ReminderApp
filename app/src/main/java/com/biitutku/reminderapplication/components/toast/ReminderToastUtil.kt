@file:JvmName("ReminderToastUtilKt")

package com.biitutku.reminderapplication.components.toast

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.lifecycle.setViewTreeViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import com.biitutku.reminderapplication.R
import com.biitutku.reminderapplication.ui.theme.padding

class ReminderToast(context: Context) : Toast(context) {
    @Composable
    fun MakeTest(
        message: String,
        type: ReminderToastProperty,
        padding: PaddingValues,
        contentAlignment: Alignment,
        duration: Int = LENGTH_LONG,

    ) {
        val context = LocalContext.current

        val views = ComposeView(context)


        views.setContent {
            ReminderToastSetView(
                messageTxt = message,
                resourceIcon = type.getResourceId(),
                backgroundColor = type.getBackgroundColor(),
                iconColor = type.getIconColor(),
                padding = padding,
                contentAlignment = contentAlignment
            )
        }
        views.setViewTreeLifecycleOwner(LocalLifecycleOwner.current)
        views.setViewTreeViewModelStoreOwner(LocalViewModelStoreOwner.current)
        views.setViewTreeSavedStateRegistryOwner(LocalSavedStateRegistryOwner.current)
        this.duration = duration
        this.view = views
    }
}


@Composable
private fun ReminderToastSetView(
    messageTxt: String,
    resourceIcon: Int,
    backgroundColor: Color,
    iconColor: Color,
    padding: PaddingValues,
    contentAlignment: Alignment,
    modifier: Modifier = Modifier
) {
    Box(
        modifier =
        modifier
            .fillMaxSize()
            .padding(padding),
        contentAlignment = contentAlignment
    ) {
        Row(
            modifier =
            Modifier
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(MaterialTheme.padding.large50)
                )
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(weight = 0.9f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = resourceIcon),
                    tint = iconColor,
                    contentDescription = ""
                )
                Text(
                    text = messageTxt,
                    color = Color.White,
                    modifier = Modifier.padding(10.dp)
                )
            }
            Row(
                modifier = Modifier.weight(weight = 0.1f),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    modifier =
                    Modifier
                        .size(24.dp),
                    painter =
                    painterResource(
                        id = R.drawable.icon_close
                    ),
                    tint = Color.White,
                    contentDescription = ""
                )
            }
        }
    }
}