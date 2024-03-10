package com.example.tplogin

import android.media.Image
import android.text.InputType
import android.text.Layout
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.navArgument

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
        composable( route = Screen.LoginScreen.route ) {
            LoginScreen(navController = navController)
        }
        composable(
            route = Screen.HomeScreen.route + "/{name}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                }
            )
        ) { entry->
            HomeScreen(name = entry.arguments?.getString("name"))
        }
    }
}

@Composable
fun LoginScreen( navController: NavController ) {

    var nameInput by remember { mutableStateOf("") }
    var pwInput by remember { mutableStateOf("") }
    var displayError by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 50.dp)
    ) {
        TextField(value = nameInput, onValueChange = {
            nameInput = it
        },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            label = {Text("Name")},
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = pwInput, onValueChange = {
            pwInput = it
        },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            label = {Text("Password")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        if (displayError) {
            Text(
                text = "Invalid username or password",
                color = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick= {
            if( nameInput == "Mohamed" && pwInput == "12345678"  ) {
                navController.navigate(Screen.HomeScreen.withArgs(nameInput))
            }
            else
                displayError = true
        },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Login")
        }
    }
}

@Composable
fun HomeScreen(name: String?) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 50.dp)
    ) {
        Text("Bonjour, $name!", fontSize = 25.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Image takes up half the width
            Image(
                painter = painterResource(id = R.drawable.coffee),
                contentDescription = null,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .fillMaxSize(),
                contentScale =
                androidx.compose.ui.layout.ContentScale.Fit
            )
        }
    }
}