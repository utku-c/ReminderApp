package com.biitutku.reminderapplication.screens.appmain.setting.widget

import android.widget.Button
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.biitutku.reminderapplication.R
import com.biitutku.reminderapplication.ui.theme.color


@Composable
fun GenderDialog(
    setGender: (String) -> Unit,
    setShowDialog: (Boolean) -> Unit,

    ) {

    val radioOptions = listOf("Kadın", "Erkek", "Belirtmek\nİstemiyorum")
    var selectedOption by remember { mutableStateOf(radioOptions[0]) }


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

        ) {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Daha iyi tavsiyeler için\ncinsiyetinizi belirtiniz!",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 22.sp
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

                        radioOptions.forEach { gender ->
                            Row(
                                modifier = Modifier
                                    .clickable {
                                        selectedOption = gender
                                    }
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = (gender == selectedOption),
                                    onClick = { selectedOption = gender },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = MaterialTheme.color.barColor,
                                        unselectedColor = MaterialTheme.color.barColor.copy(
                                            alpha = 0.3f
                                        ),
                                    )
                                )
                                Text(
                                    text = gender,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.color.barColor,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))

                        Row(
                            modifier = Modifier.height(IntrinsicSize.Min)
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable {
                                        setShowDialog(false)
                                    }
                                    .weight(1f)
                                    .padding(vertical = 20.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "KAPAT",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .padding(vertical = 10.dp)
                                    .width(2.dp)
                                    .fillMaxHeight()
                                    .background(
                                        MaterialTheme.color.barColor.copy(alpha = .2f),
                                        shape = RoundedCornerShape(10.dp)
                                    )
                            )

                            Box(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable {
                                        setGender(selectedOption)
                                        setShowDialog(false)
                                    }
                                    .weight(1f)
                                    .padding(vertical = 20.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "EKLE",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.color.barColor
                                )
                            }
                        }

                    }
                }
            }
        }
    }
}

