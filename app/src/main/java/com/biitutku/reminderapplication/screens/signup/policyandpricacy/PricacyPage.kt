package com.biitutku.reminderapplication.screens.signup.policyandpricacy

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.biitutku.reminderapplication.navigation.Route

@Composable
fun PricacyScreen(navController: NavController){
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column {
            Text("Privacy Screen")
            Button(onClick = {
                navController.navigate(
                    Route.SignUpScreen().name
                )
            }) {
                Text("Kapat")
            }
        }
    }
}