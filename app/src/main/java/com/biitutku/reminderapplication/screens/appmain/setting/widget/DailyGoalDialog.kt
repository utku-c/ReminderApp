package com.biitutku.reminderapplication.screens.appmain.setting.widget

import android.widget.Button
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.biitutku.reminderapplication.screens.appmain.setting.SettingEvent
import com.biitutku.reminderapplication.ui.theme.color
import com.biitutku.reminderapplication.ui.theme.padding


// TODO: Burada bakılacak konu mevcut. 
// TODO: Extension

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyGoalDialog(
    value: String,
    setShowDialog: (Boolean) -> Unit,
   /* xxxxx: Button.() -> Unit,*/
    setValue: String.() -> Unit,
) {

    val txtFieldError = remember { mutableStateOf("") }
    val txtField = remember { mutableStateOf(value) }

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
                .height(360.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Günlük\nHedefin!",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 28.sp
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

                    Column {
                        Spacer(modifier = Modifier.height(20.dp))

                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    BorderStroke(
                                        width = 2.dp,
                                        color = MaterialTheme.color.barColor
                                    ),
                                    shape = RoundedCornerShape(50)
                                ),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            leadingIcon = {
                                Image(
                                    painter = painterResource(
                                        id = R.drawable.app_icon_foreground
                                    ),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .width(60.dp)
                                        .height(60.dp)
                                )
                            },
                            placeholder = {
                                Text(
                                    text = "Hedefinizi yazın",
                                    color = Color.LightGray
                                )
                            },
                            value = txtField.value,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next,
                            ),

                            onValueChange = {
                                if (it.isDigitsOnly()) {
                                    txtField.value = it.take(4)
                                }

                            }
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(start = 20.dp),
                            horizontalArrangement = Arrangement.Start,
                        ) {
                            Text(text = txtFieldError.value,
                                color = Color.Red,
                            )
                        }
                        Spacer(modifier = Modifier.height(40.dp))

                        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {

                            Button(
                                onClick = {
                                    if (txtField.value.isEmpty()) {
                                        txtFieldError.value = "Boş olamaz"
                                        return@Button
                                    }
                                    if (txtField.value.length < 4){
                                        txtFieldError.value = "1000-9999 arasında değer seçin"
                                        return@Button
                                    }else{
                                        setValue(txtField.value)
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

