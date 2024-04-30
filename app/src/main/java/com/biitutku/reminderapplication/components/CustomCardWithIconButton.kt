package com.biitutku.reminderapplication.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.biitutku.reminderapplication.ui.theme.color
import com.biitutku.reminderapplication.ui.theme.spacing


@Composable
fun CustomCardWithIconButton(
    labelText: String,
    leadingIcon: ImageVector,
    trailingIcon: () -> Unit,
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = "leading icon",
                    tint = MaterialTheme.color.barColor,
                    modifier = Modifier.size(34.dp),
                )
                Spacer(modifier = Modifier.width(30.dp))
                Box {
                    Text(
                        text = labelText,
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 26.sp),
                    )
                }
            }
            IconButton(
                onClick = trailingIcon
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    "",
                    tint = MaterialTheme.color.barColor
                )
            }
        }
        Spacer(modifier = Modifier.padding(MaterialTheme.spacing.extraSmall))
    }
}