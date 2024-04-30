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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import com.biitutku.reminderapplication.R
import com.biitutku.reminderapplication.data.model.WaterRecord
import com.biitutku.reminderapplication.ui.theme.color
import com.biitutku.reminderapplication.ui.theme.padding
import com.biitutku.reminderapplication.ui.ui_helper.isSmallScreenHeight
import java.time.LocalDate
import java.time.YearMonth


@Composable
fun MonthlyTargetDialog(
   showDialog: Boolean,
   onDismissRequest: () -> Unit,
   waterRecordList: List<WaterRecord>,
   maxTarget: Int?
) {

   var showAnimatedDialog by remember { mutableStateOf(false) }

   LaunchedEffect(showDialog) {
      if (showDialog) showAnimatedDialog = true

   }

   val config = LocalConfiguration.current
   val screenWith = (config.screenWidthDp * 0.9).dp
   val screenHeight = (config.screenHeightDp * 0.8).dp

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
                     .pointerInput(Unit) { detectTapGestures { } }
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
                     waterRecordList = waterRecordList,
                     maxTarget = maxTarget
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
fun ContentDialog(
   onDismissRequest: () -> Unit,
   waterRecordList: List<WaterRecord>,
   maxTarget: Int?
) {
   val currentMaxValue = remember {
      mutableIntStateOf(value = 0)
   }
   LaunchedEffect(key1 = maxTarget) {
      if (maxTarget != null){
         currentMaxValue.intValue = maxTarget
      } else {
         currentMaxValue.intValue = 0
      }
   }
   Column(
      Modifier
         .background(MaterialTheme.colorScheme.surface)
   ) {
      var graphicVisible by remember { mutableStateOf(false) }

      LaunchedEffect(Unit) { graphicVisible = true }
      /*val columnState = rememberScrollState()*/

      AnimatedVisibility(
         visible = graphicVisible,
         enter = expandVertically(
            animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
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
            .padding(16.dp)
            .weight(if (isSmallScreenHeight()) 4f else 6f)
      ) {
         Column(
            modifier = Modifier
               .weight(if (isSmallScreenHeight()) 4f else 6f)
         ) {
            val yearMonth = YearMonth.now()
            val currentMonthName = yearMonth.month.toString()


            Text(text = "$currentMonthName - ${yearMonth.year}")
            Column(
               modifier = Modifier
                  .padding(16.dp)
                  /*.verticalScroll(columnState)*/
                  .height(if (isSmallScreenHeight()) 300.dp else 420.dp)
            ) {

               val capacityList = mutableMapOf<String, Int>()
               val dateGrouping = waterRecordList.groupBy {
                  it.date
               }
               val sortedDateGrouping = dateGrouping.entries.sortedBy { LocalDate.parse(it.key) }

               sortedDateGrouping.forEach { (date, record) ->
                  var totalCapacity = 0
                  for (i in record) {
                     totalCapacity += i.capacity
                  }

                  capacityList[date] = totalCapacity
               }
               LazyVerticalGrid(
                  columns = GridCells.Fixed(2),
                  verticalArrangement = Arrangement.spacedBy(2.dp),
                  horizontalArrangement = Arrangement.spacedBy(10.dp)
               ) {
                  items(capacityList.entries.toList()) {entry ->
                     Column(
                        modifier = Modifier
                           .fillMaxWidth()
                           .padding(bottom = MaterialTheme.padding.extraSmall4)
                           .background(
                              color = Color.LightGray.copy(alpha = 0.3f),
                              shape = RoundedCornerShape(8.dp)
                           )
                           .height(80.dp)
                     ) {
                        Row(
                           modifier = Modifier.fillMaxWidth(),
                           horizontalArrangement = Arrangement.SpaceAround
                        ) {
                           Image(
                              modifier = Modifier.height(40.dp),
                              painter = painterResource(
                                 id = R.drawable.ic_prize_badge
                              ),
                              contentDescription = "",
                              colorFilter = ColorFilter.tint( if (entry.value >= currentMaxValue.intValue) Color.Green else Color.Red,)
                           )
                           Text(
                              text = entry.key,
                              style = MaterialTheme.typography.titleSmall
                           )
                        }
                        Row (
                           modifier = Modifier.fillMaxWidth(),
                           horizontalArrangement = Arrangement.End
                        ){

                           Text(
                              text = "Kaydedilen ${entry.value}",
                              style = MaterialTheme.typography.titleSmall

                           )
                        }
                     }
                  }
               }
            }
         }
      }

      Row(
         modifier = Modifier
            .height(IntrinsicSize.Min)
            .weight(1f)
      ) {
         Box(modifier = Modifier
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
                  MaterialTheme.colorScheme.onSurface.copy(alpha = .08f),
                  shape = RoundedCornerShape(10.dp)
               )
         )

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
               text = "PAYLAŞ",
               style = MaterialTheme.typography.bodyLarge,
               color = MaterialTheme.color.barColor
            )
         }
      }

   }

}


/*
*
* items(capacityList.size) {
                     if (capacityList[waterRecordList[it].date] != null) {
                        Column(
                           modifier = Modifier
                              .background(
                                 color = if (capacityList[waterRecordList[it].date]!! > 2000) Color.Green else Color.Red,
                                 shape = RoundedCornerShape(20.dp)
                              )

                              .height(120.dp)
                        ) {
                           Row {
                              Image(
                                 modifier = Modifier.height(50.dp),
                                 painter = painterResource(
                                    id = R.drawable.ic_prize_badge
                                 ),
                                 contentDescription = "",
                                 colorFilter = ColorFilter.tint(Color.Green)
                              )
                              Text(text = "ccs")
                           }
                           Text(text = capacityList[waterRecordList[it].date].toString())
                        }

                     }


                  }
               }
* */

/*LazyVerticalGrid(
                  columns = GridCells.Fixed(3),
                  verticalArrangement = Arrangement.spacedBy(10.dp),
                  horizontalArrangement = Arrangement.spacedBy(10.dp)
               ) {

               }*/