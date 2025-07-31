package com.example.bite.ui.screens.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bite.ui.components.ActionButton
import com.example.bite.ui.components.InputField
import com.example.bite.viewmodel.AuthViewModel

@Composable
fun RegisterScreen2(
    navController: NavController,
    authViewModel: AuthViewModel,  // Pasa el AuthViewModel
    modifier: Modifier = Modifier
) {
    val dobState = remember { mutableStateOf("") }
    val genderState = remember { mutableStateOf("") }
    val phoneNumberState = remember { mutableStateOf("") }
    val countryState = remember { mutableStateOf("") }
    val stateState = remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF6F7DF))
    ) {
        Box(
            modifier = Modifier
                .offset(x = (-85).dp, y = (-40).dp)
                .size(width = 434.31.dp, height = 226.93.dp)
                .background(Color(0xFF41B9B2))
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 43.dp)
                .align(Alignment.Center)
        ) {
            Text(
                text = "Let's complete your profile...",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            InputField(label = "Date of Birth", value = dobState.value, onValueChange = { dobState.value = it })
            Spacer(modifier = Modifier.height(16.dp))
            InputField(label = "Gender", value = genderState.value, onValueChange = { genderState.value = it })
            Spacer(modifier = Modifier.height(16.dp))
            InputField(label = "Phone number", value = phoneNumberState.value, onValueChange = { phoneNumberState.value = it })
            Spacer(modifier = Modifier.height(16.dp))
            InputField(label = "Country", value = countryState.value, onValueChange = { countryState.value = it })
            Spacer(modifier = Modifier.height(16.dp))
            InputField(label = "State", value = stateState.value, onValueChange = { stateState.value = it })
        }

        ActionButton(
            text = "NEXT",
            onClick = { navController.navigate("register3") },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
        )
    }
}
