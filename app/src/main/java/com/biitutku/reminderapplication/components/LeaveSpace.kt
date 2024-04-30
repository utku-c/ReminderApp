package com.biitutku.reminderapplication.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.biitutku.reminderapplication.ui.theme.color
/*
* divier'ın altından üstünden boşluk bırakır
* */
@Composable
fun LeaveASpace() {
    Column {
        Spacer(modifier = Modifier.height(20.dp))
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.color.barColor.copy(alpha = 0.2f))
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}