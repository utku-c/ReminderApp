package com.biitutku.reminderapplication.screens.signup

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.biitutku.reminderapplication.R
import com.biitutku.reminderapplication.components.HeaderText
import com.biitutku.reminderapplication.components.LoginTextField
import com.biitutku.reminderapplication.navigation.Route
import com.biitutku.reminderapplication.screens.login.LoginEvent
import com.biitutku.reminderapplication.screens.login.LoginViewModel
import com.biitutku.reminderapplication.ui.theme.color
import com.biitutku.reminderapplication.ui.theme.padding
import com.biitutku.reminderapplication.ui.theme.spacing
import com.biitutku.reminderapplication.ui.ui_helper.isSmallScreenHeight

@Composable
fun SignUpScreen(
   navController: NavController,
   viewModel: SignUpViewModel,
   event: (SignUpEvent) -> Unit,
) {
   val (firstName, onFirstNameChange) = rememberSaveable { mutableStateOf("") }
   val (email, onEmailChange) = rememberSaveable { mutableStateOf("") }
   val (password, onPasswordChange) = rememberSaveable { mutableStateOf("") }
   val (confirmPassword, onConfirmPasswordChange) = rememberSaveable { mutableStateOf("") }
   val (agree, onAgreeChange) = rememberSaveable { mutableStateOf(false) }
   val context = LocalContext.current
   var isPasswordSame by remember { mutableStateOf(false) }
   var passwordVisible by rememberSaveable { mutableStateOf(false) }
   var checkPasswordVisible by rememberSaveable { mutableStateOf(false) }
   val isFieldsNotEmpty = firstName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() &&
           confirmPassword.isNotEmpty() && agree

   Column(
      modifier = Modifier
         .fillMaxSize()
         .verticalScroll(rememberScrollState())
         .padding(MaterialTheme.padding.medium16),
      horizontalAlignment = Alignment.CenterHorizontally
   ) {
      AnimatedVisibility(isPasswordSame) {
         Text(
            text = "Password Is not Matching",
            color = MaterialTheme.colorScheme.error,
         )
      }
      HeaderText(
         text = "Kayıt Ol",
         modifier = Modifier
            .padding(vertical = MaterialTheme.spacing.small)
            .align(alignment = Alignment.Start)
      )
      LoginTextField(
         value = firstName,
         onValueChange = onFirstNameChange,
         labelText = "Kullanıcı Adı",
         leadingIcon = Icons.Default.Person,
         keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
         modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
      )
      Spacer(Modifier.height(MaterialTheme.padding.medium16))
      LoginTextField(
         value = email,
         onValueChange = onEmailChange,
         labelText = "E-posta",
         leadingIcon = Icons.Default.Email,
         keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
         modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
      )
      Spacer(Modifier.height(MaterialTheme.padding.medium16))
      LoginTextField(
         value = password,
         onValueChange = onPasswordChange,
         labelText = "Şifre",
         keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
         leadingIcon = Icons.Default.Lock,
         modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
         keyboardType = KeyboardType.Password,
         visualTransformation =
         if (checkPasswordVisible) {
            VisualTransformation.None
         } else {
            PasswordVisualTransformation()
         },
         trailingIcon = {
            val image = if (checkPasswordVisible) R.drawable.icon_password_visible
            else R.drawable.icon_password_invisible

            IconButton(onClick = { checkPasswordVisible = !checkPasswordVisible }) {
               Icon(painter = painterResource(image), "", tint = MaterialTheme.color.barColor)
            }
         }

      )
      Spacer(Modifier.height(MaterialTheme.padding.medium16))
      LoginTextField(
         value = confirmPassword,
         onValueChange = onConfirmPasswordChange,
         labelText = "Şifre Doğrulama",
         leadingIcon = Icons.Default.Lock,
         modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
         keyboardType = KeyboardType.Password,
         keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
         visualTransformation =
         if (passwordVisible) {
            VisualTransformation.None
         } else {
            PasswordVisualTransformation()
         },

         )
      if (isSmallScreenHeight()) {
         Spacer(modifier = Modifier.fillMaxSize(0.05f))
      } else {
         Spacer(modifier = Modifier.fillMaxSize(0.1f))
      }
      Row(
         horizontalArrangement = Arrangement.Center,
         verticalAlignment = Alignment.CenterVertically
      ) {
         val privacyText = "Gizlilik"
         val policyText = "Politika"
         val annotatedString = buildAnnotatedString {

            append(" ")
            withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary, fontFamily = MaterialTheme.typography.bodyLarge.fontFamily)) {
               pushStringAnnotation(tag = privacyText, privacyText)
               append(privacyText)
            }
            append(" ve ")
            withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary, fontFamily = MaterialTheme.typography.bodyLarge.fontFamily)) {
               pushStringAnnotation(tag = policyText, policyText)
               append(policyText)
            }
            append(" 'yı kabul ediyorum")
         }

         Checkbox(agree, onAgreeChange)
         ClickableText(
            annotatedString,
         ) { offset ->
            annotatedString.getStringAnnotations(offset, offset).forEach {
               when (it.tag) {
                  privacyText -> {
                     Toast.makeText(context, "Privacy Text Clicked", Toast.LENGTH_SHORT)
                        .show()
                     navController.navigate(
                        Route.PrivacyScreen().name
                     ) {
                        launchSingleTop = true
                     }
                  }

                  policyText -> {
                     Toast.makeText(context, "Policy Text Clicked", Toast.LENGTH_SHORT)
                        .show()
                     navController.navigate(
                        Route.PolicyScreen().name
                     ) {
                        launchSingleTop = true
                     }
                  }
               }
            }
         }
      }
      Spacer(Modifier.height(MaterialTheme.padding.large32))
      Button(
         onClick = {
            if (password == confirmPassword) {
               viewModel.state.value.userName = firstName
               viewModel.state.value.password = password
               viewModel.state.value.email = email
               event(SignUpEvent.SaveUserInfo)
               navController.popBackStack()
               navController.navigate(Route.LoginScreen().name)


            }
         },
         modifier = Modifier.fillMaxWidth(),
         enabled = isFieldsNotEmpty,
      ) {
         Text("Giriş Yap", style = MaterialTheme.typography.bodyMedium)
      }
      Spacer(Modifier.height(MaterialTheme.padding.medium16))
      val singTx = "Giriş Yap"
      val signInAnnotation = buildAnnotatedString {
         withStyle(SpanStyle(color = MaterialTheme.colorScheme.onBackground, fontFamily = MaterialTheme.typography.bodyLarge.fontFamily)) {
            append("Zaten hesabım var?")
         }
         append("  ")
         withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary, fontFamily = MaterialTheme.typography.bodyLarge.fontFamily)) {
            pushStringAnnotation(singTx, singTx)
            append(singTx)
         }


      }
      ClickableText(
         signInAnnotation,
      ) { offset ->
         signInAnnotation.getStringAnnotations(offset, offset).forEach {
            if (it.tag == singTx) {
               Toast.makeText(context, "Sign in Clicked", Toast.LENGTH_SHORT).show()

            }
         }
      }

   }
}
