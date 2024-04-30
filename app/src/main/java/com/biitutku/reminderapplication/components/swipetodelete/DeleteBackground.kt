@file:OptIn(ExperimentalMaterial3Api::class)

package com.biitutku.reminderapplication.components.swipetodelete

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DeleteBackground(
   swipeDismissState: DismissState
) {
   val color = if (swipeDismissState.dismissDirection == DismissDirection.EndToStart) {
      Color.Red
   } else Color.Transparent

   Box(
      modifier = Modifier
         .fillMaxWidth()
         .background(color)
         .padding(16.dp),
      contentAlignment = Alignment.CenterEnd
   ) {
      Icon(
         imageVector = Icons.Default.Delete,
         contentDescription = null,
         tint = Color.White
      )
   }
}