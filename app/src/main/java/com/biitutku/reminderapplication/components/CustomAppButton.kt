package com.biitutku.reminderapplication.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.biitutku.reminderapplication.ui.theme.color
import com.biitutku.reminderapplication.ui.theme.padding

@Composable
fun CustomAppButton(
    buttonTitle: String,
    modifier: Modifier = Modifier,
    enable: Boolean = true,
    onButtonClick: () -> Unit,
) {
    Button(
        shape = RoundedCornerShape(
            MaterialTheme.padding.medium16
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.color.barColor,
            disabledContainerColor = Color.Gray,
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(
                MaterialTheme.padding.extraLarge64
            )),
        enabled = enable,
        onClick = onButtonClick,
    ) {
        Text(
            buttonTitle,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White
        )
    }
}