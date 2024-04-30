package com.biitutku.reminderapplication.screens.login

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.biitutku.reminderapplication.R
import com.biitutku.reminderapplication.app_object.AnalyticsManager
import com.biitutku.reminderapplication.components.CustomAppButton
import com.biitutku.reminderapplication.components.HeaderText
import com.biitutku.reminderapplication.components.LoginTextField
import com.biitutku.reminderapplication.components.toast.ReminderToastMessage
import com.biitutku.reminderapplication.components.toast.toastTypes.ToastMessageType
import com.biitutku.reminderapplication.navigation.Route
import com.biitutku.reminderapplication.ui.theme.color
import com.biitutku.reminderapplication.ui.theme.padding
import com.biitutku.reminderapplication.ui.theme.spacing
import com.biitutku.reminderapplication.ui.ui_helper.isSmallScreenHeight
import com.biitutku.reminderapplication.util.rememberImeState
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

@Composable
fun LoginScreen(
   navController: NavController,
   viewModel: LoginViewModel,
   event: (LoginEvent) -> Unit,
) {
   val (userName, setUsername) = rememberSaveable {
      mutableStateOf("")
   }
   val (password, setPassword) = rememberSaveable {
      mutableStateOf("")
   }
   val (checked, onCheckedChange) = rememberSaveable {
      mutableStateOf(false)
   }


   val isFieldsEmpty = userName.isNotEmpty() && password.isNotEmpty()
   val context = LocalContext.current
   var checkPasswordVisible by rememberSaveable { mutableStateOf(false) }
   var showToastMessage by rememberSaveable { mutableStateOf(false) }
   val imeState = rememberImeState()
   val scrollState = rememberScrollState()
   val firebaseAnalytics = Firebase.analytics


   LaunchedEffect(key1 = imeState.value) {
      if (imeState.value){
         scrollState.animateScrollTo((scrollState.maxValue * 0.4).toInt(), tween(500))
      }
   }

   LaunchedEffect(key1 = Unit) {
      event(LoginEvent.ReadUserInfo)
      AnalyticsManager.logScreenView("ReminderApp-LoginPage")

      /*Firebase.analytics.logEvent("LoginPage",null)
      //Firebase.analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW,null)
      navController.addOnDestinationChangedListener { _, destination, _ ->
         val params = Bundle()
         params.putString(FirebaseAnalytics.Param.SCREEN_NAME, destination.route)
         params.putString(FirebaseAnalytics.Param.DESTINATION, destination.navigatorName)
         firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, params)


         Log.d("FirebaseLog","${FirebaseAnalytics.Event.SCREEN_VIEW} - ${FirebaseAnalytics.Param.SCREEN_NAME} - ${destination.route}")
      }*/
   }

   DisposableEffect(key1 = showToastMessage) {
      onDispose {
         showToastMessage = false
      }
   }

   Column(
      modifier = Modifier
         .fillMaxSize()
         .padding(
            bottom = MaterialTheme.padding.medium20,
            start = MaterialTheme.padding.medium20,
            end = MaterialTheme.padding.medium20
         )
         .verticalScroll(scrollState),
      verticalArrangement = Arrangement.Center
   ) {
      Column(
         modifier = Modifier
            .align(Alignment.CenterHorizontally),
         horizontalAlignment = Alignment.CenterHorizontally,
      ) {

         Spacer(modifier = Modifier.height(20.dp))
         Row(
            verticalAlignment = Alignment.CenterVertically
         ) {
            Box {
               Image(
                  painter = painterResource(id = R.drawable.app_icon_foreground),
                  contentDescription = "boş",
                  modifier =
                  if (isSmallScreenHeight())
                     Modifier.size(120.dp)
                  else Modifier.size(170.dp),
               )
            }
            Spacer(modifier = Modifier.width(30.dp))
            HeaderText(
               text = "Hoş\nGeldiniz",
            )
         }
      }
      Spacer(modifier = Modifier.height(20.dp))


      if (isSmallScreenHeight()) {
         Spacer(modifier = Modifier.fillMaxSize(0.02f))
      } else {
         Spacer(modifier = Modifier.fillMaxSize(0.07f))
      }
      Column {
         Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
               modifier = Modifier.size(40.dp),
               painter = painterResource(id = R.drawable.ic_soda_cupple),
               contentDescription = "yok")
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = "Günlük su içme hedefinizi belirleyin ve izleyin.")
         }
         Spacer(modifier = Modifier.height(20.dp))

         Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
               modifier = Modifier.size(40.dp),
               painter = painterResource(id = R.drawable.ic_water_bottle_full),
               contentDescription = "yok")
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = "Su tüketim alışkanlıklarınızı günlük, aylık olarak takip edin.")

         }
         Spacer(modifier = Modifier.height(20.dp))

         Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
               modifier = Modifier.size(40.dp),
               painter = painterResource(id = R.drawable.ic_water_cupple),
               contentDescription = "yok"
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = "İstatistikler ve grafikler aracılığıyla ilerlemenizi izleyin.")
         }
         Spacer(modifier = Modifier.height(20.dp))

         Row(
            verticalAlignment = Alignment.CenterVertically
         ) {
            Image(
               modifier = Modifier.size(40.dp),
               painter = painterResource(id = R.drawable.ic_water_bottle_half),
               contentDescription = "yok")
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = "Uygulamamızın keyfini çıkarın ve daha sağlıklı bir yaşam tarzına adım atın!")
         }

         Spacer(modifier = Modifier.height(20.dp))
         CustomAppButton(
            buttonTitle = stringResource(R.string.devam_et),
            onButtonClick = {
               navController.popBackStack()
               navController.navigate(
                  route = Route.AppMainScreen().name
               )

            }
         )
      }

      /*Column(
         modifier = Modifier.imeNestedScroll()
      ) {

         LoginTextField(
            value = userName,
            onValueChange = setUsername,
            labelText = stringResource(R.string.kullanici_ad),
            leadingIcon = Icons.Default.Person,
            modifier = Modifier
               .fillMaxWidth()
               .height(70.dp),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
         )
         Spacer(Modifier.height(MaterialTheme.spacing.medium))
         LoginTextField(
            value = password,
            onValueChange = setPassword,
            labelText = "Şifre",
            leadingIcon = Icons.Default.Lock,
            modifier = Modifier
               .fillMaxWidth()
               .height(70.dp),
            keyboardType = KeyboardType.Password,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            visualTransformation =
            if (checkPasswordVisible) {
               VisualTransformation.None
            } else {
               PasswordVisualTransformation()
            },
            trailingIcon = {
               val image = if (checkPasswordVisible) R.drawable.icon_password_visible
               else R.drawable.icon_password_invisible

               IconButton(
                  onClick = { checkPasswordVisible = !checkPasswordVisible }
               ) {
                  Icon(
                     painter = painterResource(image),
                     "",
                     tint = MaterialTheme.color.barColor
                  )
               }
            }
         )
         Spacer(Modifier.height(MaterialTheme.spacing.medium))
         Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
         ) {
            Row(
               horizontalArrangement = Arrangement.Center,
               verticalAlignment = Alignment.CenterVertically
            ) {
               Checkbox(
                  checked = checked,
                  onCheckedChange = onCheckedChange, colors = CheckboxDefaults.colors(
                     checkedColor = MaterialTheme.color.barColor,

                     )
               )
               Text(stringResource(R.string.beni_hatirla))
            }
            TextButton(
               onClick = {}
            ) {
               Text(
                  text = "Şifremi unuttum!",
                  style = MaterialTheme.typography.bodyMedium
               )
            }
         }
      }
      CustomAppButton(
         buttonTitle = stringResource(R.string.giris_yap),
         //enable = isFieldsEmpty,
         onButtonClick = {
            Log.d("viewModel.Value: ", viewModel.state.value.userName.toString())
            Log.d("viewModel.Value: ", viewModel.state.value.password.toString())

            if (viewModel.state.value.userName == userName && viewModel.state.value.password == password) {
               navController.navigate(
                  route = Route.AppMainScreen().name
               )
            } else {
               showToastMessage = true
            }
         }
      )*/


     /* AlternativeLoginOptions(
         onIconClick = { index ->
            when (index) {
               0 -> {
                  Toast.makeText(context, "Instagram ile giriş", Toast.LENGTH_SHORT).show()
               }

               1 -> {
                  Toast.makeText(context, "Github ile giriş", Toast.LENGTH_SHORT).show()

               }

               2 -> {
                  Toast.makeText(context, "Google ile giriş", Toast.LENGTH_SHORT).show()
               }
            }
         },
         onSignUpClick = {
            navController.navigate(
               Route.SignUpScreen().name
            )
         },
         modifier = Modifier
            .wrapContentSize(align = Alignment.BottomCenter)
      )*/


   }
}


@Composable
fun AlternativeLoginOptions(
   onIconClick: (index: Int) -> Unit,
   onSignUpClick: () -> Unit,
   modifier: Modifier = Modifier,
) {
   val iconList = listOf(
      R.drawable.icon_instagram,
      R.drawable.icon_github,
      R.drawable.icon_google,
   )
   Column(
      modifier = modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
   ) {
      Text("ile oturum aç")
      Row(horizontalArrangement = Arrangement.SpaceEvenly) {
         iconList.forEachIndexed { index, iconResId ->
            Image(
               painter = painterResource(iconResId),
               contentDescription = "alternative Login",
               modifier = Modifier
                  .size(32.dp)
                  .clickable {
                     onIconClick(index)
                  }
                  .clip(CircleShape)
            )
            Spacer(Modifier.width(MaterialTheme.padding.medium16))
         }
      }
      Spacer(Modifier.height(MaterialTheme.spacing.medium))
      Row(
         horizontalArrangement = Arrangement.Center,
         verticalAlignment = Alignment.CenterVertically
      ) {
         Text("Hesabın yok mu?")
         Spacer(Modifier.height(MaterialTheme.spacing.medium))
         TextButton(onClick = onSignUpClick) {
            Text("Kayıt Ol", style = MaterialTheme.typography.bodyMedium)
         }
      }
   }

}
