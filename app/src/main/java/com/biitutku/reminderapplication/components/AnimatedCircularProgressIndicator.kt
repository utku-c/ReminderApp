package com.biitutku.reminderapplication.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.RenderMode
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.biitutku.reminderapplication.R
import com.biitutku.reminderapplication.ui.ui_helper.isSmallScreenHeight

@Composable
fun AnimatedCircularProgressIndicator(
   currentValue: Int,
   maxValue: Int,
   progressBackgroundColor: Color,
   progressIndicatorColor: Color,
   completedColor: Color,
   modifier: Modifier = Modifier
) {
   val stroke = with(LocalDensity.current) {
      Stroke(
         width = 12.dp.toPx(),
         cap = StrokeCap.Round,
         join = StrokeJoin.Round
      )
   }

   Box(
      modifier = modifier,
      contentAlignment = Alignment.Center
   ) {
      ProgressStatus(
         currentValue = currentValue,
         maxValue = maxValue,
         progressBackgroundColor = progressBackgroundColor,
         progressIndicatorColor = progressIndicatorColor,
         completedColor = completedColor
      )

      val animateFloat = remember { Animatable(0f) }
      LaunchedEffect(key1 = currentValue, key2 = animateFloat) {
         animateFloat.animateTo(
            targetValue = currentValue / maxValue.toFloat(),
            animationSpec = tween(durationMillis = 2000, easing = FastOutSlowInEasing)
         )
      }

      Canvas(
         Modifier
            .progressSemantics(currentValue / maxValue.toFloat())
            .size(
               if (isSmallScreenHeight()) 180.dp else 220.dp
            )
      ) {
         /*val backgroundHeight = size.height * (currentValue.toFloat() / maxValue.toFloat())

         // Arka planı çiz
         drawRoundRect(
            color = Color.Green, // Arka plan rengi
            blendMode = BlendMode.Darken,
            topLeft = Offset(0f, size.height - backgroundHeight), // Sol üst köşenin konumu
            size = Size(size.width, backgroundHeight), // Boyut
            cornerRadius = CornerRadius(size.width / 2f) // Yuvarlak köşeler
         )*/

         // Start at 12 O'clock
         val startAngle = 270f
         val sweep: Float = animateFloat.value * 360f
         val diameterOffset = stroke.width / 2

         drawCircle(
            color = progressBackgroundColor,
            style = stroke,
            radius = size.minDimension / 2.0f - diameterOffset
         )
         drawCircularProgressIndicator(startAngle, sweep, progressIndicatorColor, stroke)

         if (currentValue == maxValue) {
            drawCircle(
               color = completedColor,
               style = stroke,
               radius = size.minDimension / 2.0f - diameterOffset
            )
         }
      }
   }
}

@Composable
private fun ProgressStatus(
   currentValue: Int,
   maxValue: Int,
   progressBackgroundColor: Color,
   progressIndicatorColor: Color,
   completedColor: Color,
   modifier: Modifier = Modifier

) {
   var gunlukHedefTamamlandi: Boolean = (currentValue == maxValue || currentValue > maxValue)

   LaunchedEffect(key1 = currentValue) {
      gunlukHedefTamamlandi = (currentValue == maxValue || currentValue > maxValue)
   }

   Column(
      modifier = Modifier
         .fillMaxWidth(),
      horizontalAlignment = Alignment.CenterHorizontally,
   ) {
      if (gunlukHedefTamamlandi) {
         LoaderAnimation(
            modifier = Modifier
               .size(if (isSmallScreenHeight()) 120.dp else 180.dp),
            anim = R.raw.lottie_check,
         )
      } else {
            Box {
               LoaderAnimation(
                  modifier = Modifier
                     .size(if (isSmallScreenHeight()) 120.dp else 220.dp),
                  anim = R.raw.lottie_water_animation,
               )
               Column(
                  modifier = modifier.align(Alignment.BottomCenter).padding(bottom = 30.dp),
                  horizontalAlignment = Alignment.CenterHorizontally
               ) {
                  Row {
                     Text(
                        text = "$currentValue/",
                        style = MaterialTheme.typography.titleLarge.copy(
                           fontSize = if (isSmallScreenHeight()) 18.sp else 22.sp,
                           color = if (gunlukHedefTamamlandi) completedColor else progressIndicatorColor
                        )
                     )
                     Text(
                        modifier = Modifier,
                        text = "$maxValue",
                        style = MaterialTheme.typography.titleLarge.copy(
                           fontSize = if (isSmallScreenHeight()) 18.sp else 22.sp,
                           color = if (gunlukHedefTamamlandi) completedColor else progressIndicatorColor

                        )
                     )
                  }
                  Text(
                     modifier = Modifier,
                     text = "Mililitre",
                     style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = if (isSmallScreenHeight()) 16.sp else 20.sp,
                        color = if (gunlukHedefTamamlandi) completedColor else progressIndicatorColor

                     )
                  )
               }
            }
            /*Row {
               Text(
                  modifier = modifier
                     .align(Alignment.Bottom),
                  text = "$currentValue/",
                  style = MaterialTheme.typography.titleLarge.copy(
                     fontSize = if (isSmallScreenHeight()) 28.sp else 34.sp,
                     color = if (gunlukHedefTamamlandi) completedColor else progressIndicatorColor
                  )
               )
               Text(
                  modifier = Modifier,
                  text = "$maxValue",
                  style = MaterialTheme.typography.titleLarge.copy(
                     fontSize = if (isSmallScreenHeight()) 28.sp else 34.sp,
                     color = progressBackgroundColor
                  )
               )
            }
            Row {
               Text(
                  text = "Mililitre",
                  style = MaterialTheme.typography.titleLarge.copy(
                     fontSize = if (isSmallScreenHeight()) 28.sp else 34.sp,
                     color = progressBackgroundColor
                  )
               )

            }*/

      }
   }
}

private fun DrawScope.drawCircularProgressIndicator(
   startAngle: Float,
   sweep: Float,
   color: Color,
   stroke: Stroke
) {
   // To draw this circle we need a rect with edges that line up with the midpoint of the stroke.
   // To do this we need to remove half the stroke width from the total diameter for both sides.
   val diameterOffset = stroke.width / 2
   val arcDimen = size.width - 2 * diameterOffset
   drawArc(
      color = color,
      startAngle = startAngle,
      sweepAngle = sweep,
      useCenter = false,
      topLeft = Offset(diameterOffset, diameterOffset),
      size = Size(arcDimen, arcDimen),
      style = stroke
   )
}


@Composable
fun LoaderAnimation(
   modifier: Modifier,
   anim: Int,

) {
   val composition by rememberLottieComposition(
      spec = LottieCompositionSpec.RawRes(anim)
   )
   LottieAnimation(
      composition = composition,
      iterations = 4,
      restartOnPlay = false,
      modifier = modifier,
      speed = 0.50f,

   )
}

