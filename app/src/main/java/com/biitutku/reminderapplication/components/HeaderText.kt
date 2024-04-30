package com.biitutku.reminderapplication.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.biitutku.reminderapplication.ui.theme.appTypography

@Composable
fun HeaderText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = appTypography.titleLarge,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}