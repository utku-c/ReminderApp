package com.biitutku.reminderapplication.screens.appmain.setting.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.text.isDigitsOnly
import com.biitutku.reminderapplication.R
import com.biitutku.reminderapplication.ui.theme.color
import com.biitutku.reminderapplication.ui.theme.padding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectTimeDialog(
   title:String,
   setShowDialog: (Boolean) -> Unit,
   setValue: (String) -> Unit
) {



   var selectedHour by remember { mutableIntStateOf(0) }
   var selectedMinute by remember { mutableIntStateOf(0) }
   val timeState = rememberTimePickerState(
      initialHour = selectedHour,
      initialMinute = selectedMinute
   )

   Dialog(
      onDismissRequest = {
         setShowDialog(false)
      }
   ) {
      Surface(
         shape = RoundedCornerShape(16.dp),
         color = Color.White,
         modifier = Modifier
            .fillMaxWidth()
            .height(600.dp)
      ) {
         Box(
            contentAlignment = Alignment.Center,
         ) {
            Column(
               modifier = Modifier
                  .padding(
                     horizontal = 20.dp,
                  )
                  .fillMaxHeight(),
               verticalArrangement = Arrangement.Center
            ) {
               Row(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.SpaceBetween,
                  verticalAlignment = Alignment.CenterVertically
               ) {
                  Text(
                     text =title,
                     style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 24.sp
                     ),
                     color = MaterialTheme.color.barColor
                  )
                  Image(
                     painter = painterResource(
                        id = R.drawable.icon_close
                     ),
                     contentDescription = "",
                     modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                        .clickable { setShowDialog(false) }
                  )
               }

               Column(
                  horizontalAlignment = Alignment.CenterHorizontally
               ) {
                  Spacer(modifier = Modifier.height(20.dp))
                  TimePicker(state = timeState)
                  Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {

                     Button(
                        onClick = {
                           selectedHour = timeState.hour
                           selectedMinute = timeState.minute

                           if (selectedHour <= 0 || selectedHour > 24 || selectedMinute < 0 || selectedMinute > 60) {
                              return@Button
                           }
                           else{
                              setValue("$selectedHour:$selectedMinute")
                              setShowDialog(false)
                           }

                        },
                        shape = RoundedCornerShape(
                           MaterialTheme.padding.medium16
                        ),
                        colors = ButtonDefaults.buttonColors(
                           containerColor = MaterialTheme.color.barColor,
                           disabledContainerColor = Color.Red,
                        ),
                        modifier = Modifier
                           .fillMaxWidth()
                           .height(50.dp)
                           .clip(RoundedCornerShape(MaterialTheme.padding.large32)),
                     ) {
                        Text(
                           text = "KAYDET",
                           style = MaterialTheme.typography.bodyLarge,
                           color = Color.White
                        )
                     }
                  }
               }
            }
         }
      }
   }
}