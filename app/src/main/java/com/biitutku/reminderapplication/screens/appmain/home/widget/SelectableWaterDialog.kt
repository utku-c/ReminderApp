package com.biitutku.reminderapplication.screens.appmain.home.widget


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import androidx.hilt.navigation.compose.hiltViewModel
import com.biitutku.reminderapplication.R
import com.biitutku.reminderapplication.data.model.WaterRecord
import com.biitutku.reminderapplication.screens.appmain.home.Event
import com.biitutku.reminderapplication.screens.appmain.home.HomePageViewModel
import com.biitutku.reminderapplication.ui.theme.color
import com.biitutku.reminderapplication.ui.theme.padding
import com.biitutku.reminderapplication.ui.ui_helper.isSmallScreenHeight
import com.biitutku.reminderapplication.util.DATA_FORMATTER_DATE_YYYY_MM_DD
import com.biitutku.reminderapplication.util.DATA_FORMATTER_TIME_HH_MM
import com.biitutku.reminderapplication.util.formatDateTime
import java.time.LocalDateTime

@Composable
fun SelectableWaterDialog(
   showDialog: Boolean,
   onDismissRequest: () -> Unit,
   onConfirm: () -> Unit,
   content: @Composable () -> Unit,
   viewModel: HomePageViewModel = hiltViewModel()
) {

   var showAnimatedDialog by remember { mutableStateOf(false) }

   val config = LocalConfiguration.current
   val screenWith = (config.screenWidthDp * 0.9).dp
   val screenHeight = (config.screenHeightDp * 0.8).dp

   LaunchedEffect(showDialog) {
      if (showDialog) showAnimatedDialog = true
   }

   if (showAnimatedDialog) {
      Dialog(
         onDismissRequest = onDismissRequest,
         properties = DialogProperties(
            usePlatformDefaultWidth = false
         )
      ) {
         (LocalView.current.parent as? DialogWindowProvider)?.window?.let { window ->
            window.setDimAmount(0f)
            window.setWindowAnimations(-1)
         }

         Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
         ) {
            var animateIn by remember { mutableStateOf(false) }
            LaunchedEffect(Unit) { animateIn = true }
            AnimatedVisibility(
               visible = animateIn && showDialog,
               enter = fadeIn(),
               exit = fadeOut(),
            ) {
               Box(
                  modifier = Modifier
                     .pointerInput(Unit) { detectTapGestures { onDismissRequest() } }
                     .background(Color.Black.copy(alpha = .56f))
                     .fillMaxSize()
               )
            }
            AnimatedVisibility(
               visible = animateIn && showDialog,
               enter = fadeIn(spring(stiffness = Spring.StiffnessHigh)) + scaleIn(
                  initialScale = .8f,
                  animationSpec = spring(
                     dampingRatio = Spring.DampingRatioMediumBouncy,
                     stiffness = Spring.StiffnessMediumLow
                  )
               ),
               exit = slideOutVertically { it / 8 } + fadeOut() + scaleOut(targetScale = .95f)
            ) {
               Box(
                  Modifier
                     .pointerInput(Unit) { detectTapGestures {} }
                     .shadow(8.dp, shape = RoundedCornerShape(16.dp))
                     .width(screenWith)
                     .height(screenHeight)
                     .clip(RoundedCornerShape(16.dp))
                     .background(
                        MaterialTheme.colorScheme.surface,
                     ),
                  contentAlignment = Alignment.TopStart
               ) {
                  ContentDialog(
                     onDismissRequest = onDismissRequest,
                     onConfirm = onConfirm,
                     event = viewModel::onEvent
                  )
               }

               DisposableEffect(Unit) {
                  onDispose {
                     showAnimatedDialog = false
                  }
               }
            }
         }
      }
   }
}

@Composable
private fun ContentDialog(
   onDismissRequest: () -> Unit,
   onConfirm: () -> Unit,
   event: (Event) -> Unit,
) {

   val state = rememberScrollState()
   val selectedItem = rememberSaveable {
      mutableIntStateOf(-1)
   }

   Column(Modifier.background(MaterialTheme.colorScheme.surface)) {

      var graphicVisible by remember {
         mutableStateOf(false)
      }

      LaunchedEffect(Unit) {
         graphicVisible = true
      }

      AnimatedVisibility(
         visible = graphicVisible,
         enter = expandVertically(
            animationSpec = spring(
               stiffness = Spring.StiffnessMediumLow
            ),
            expandFrom = Alignment.CenterVertically,
         )
      ) {
         Box(
            modifier = Modifier
               .fillMaxWidth()
               .height(100.dp)
               .background(
                  brush = Brush.linearGradient(
                     colors = listOf(
                        MaterialTheme.color.bodyTextColor,
                        MaterialTheme.color.barColor,
                     )
                  )
               ),
            contentAlignment = Alignment.Center,
         ) {
            Image(
               painter = painterResource(
                  id = R.drawable.app_icon_foreground
               ),

               contentDescription = "App Icon"
            )
         }
      }

      Column(
         modifier = Modifier
            .weight(if (isSmallScreenHeight()) 4f else 6f)
      ) {
         Column(
            modifier = Modifier
               .padding(16.dp)
               .verticalScroll(state)
               .height(if (isSmallScreenHeight()) 360.dp else 430.dp)
         ) {

            LazyVerticalGrid(
               columns = GridCells.Adaptive(minSize = 120.dp),
               modifier = Modifier.align(Alignment.CenterHorizontally),
               verticalArrangement = Arrangement.spacedBy(10.dp),
               horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
               items(waterRecord.size) {
                  Column(
                     modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                           enabled = true,
                           role = Role.Button,
                           onClick = {

                              if (selectedItem.intValue == it) {
                                 selectedItem.intValue = -1
                              } else {
                                 selectedItem.intValue = it
                              }
                           }
                        )
                        .padding(vertical = MaterialTheme.padding.small8),
                     verticalArrangement = Arrangement.Center,
                     horizontalAlignment = Alignment.CenterHorizontally
                  ) {
                     Column(

                        modifier = Modifier
                           .border(
                              width = 1.dp,
                              shape = RoundedCornerShape(20.dp),
                              color = MaterialTheme.color.barColor,
                           )
                           .background(
                              shape = RoundedCornerShape(20.dp),
                              color = MaterialTheme.color.barColor.copy(
                                 alpha = if (selectedItem.intValue == it) 0.55f else 0.2f
                              )
                           )
                           .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                     ) {
                        Image(
                           painter = painterResource(
                              id = waterRecord[it].waterImage
                           ),
                           contentDescription = waterRecord[it].description,
                           modifier = Modifier
                              .height(70.dp)
                              .padding(bottom = MaterialTheme.padding.small10)
                              .align(Alignment.CenterHorizontally)
                        )

                        Text(
                           text = waterRecord[it].description.toString(),
                           textAlign = TextAlign.Center,
                           modifier = Modifier.fillMaxWidth()
                        )
                     }
                  }
               }
            }

         }
      }
      Row(
         modifier = Modifier.height(IntrinsicSize.Min).weight(1f)
      ) {
         Box(
            modifier = Modifier
               .padding(8.dp)
               .clip(RoundedCornerShape(8.dp))
               .clickable { onDismissRequest() }
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
                  val currentDateTime = LocalDateTime.now()

                  val formattedDate = currentDateTime.formatDateTime(DATA_FORMATTER_DATE_YYYY_MM_DD)
                  val formattedTime = currentDateTime.formatDateTime(DATA_FORMATTER_TIME_HH_MM)

                  if (selectedItem.intValue < 0) {
                     return@clickable
                  } else {
                     val waterRecord = waterRecord[selectedItem.intValue]
                     waterRecord.time = formattedTime
                     waterRecord.date = formattedDate
                     event(Event.UpsertWaterRecord(waterRecord = waterRecord))
                     onConfirm()
                  }
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

val waterRecord = listOf<WaterRecord>(
   WaterRecord(
      waterType = "",
      description = "100 ml \nSu",
      capacity = 100,
      waterImage = R.drawable.ic_water_cupple,
      date = "",
      time = ""
   ),
   WaterRecord(
      waterType = "",
      description = "150 ml \nSu",
      capacity = 150,
      waterImage = R.drawable.ic_water_bottle_half,
      date = "",
      time = ""
   ),
   WaterRecord(
      waterType = "",
      description = "300 ml \nSu",
      capacity = 300,
      waterImage = R.drawable.ic_water_bottle_full,
      date = "",
      time = ""
   ),
   WaterRecord(
      waterType = "",
      description = "100 ml \nLimonlu Su",
      capacity = 100,
      waterImage = R.drawable.ic_soda_cupple,
      date = "",
      time = ""
   )
).toList()
