package com.example.bite.ui.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bite.ui.components.ActionButton
import com.example.bite.ui.components.InputField
import com.example.bite.viewmodel.AuthViewModel

@Composable
fun RegisterScreen1(
    navController: NavController,
    authViewModel: AuthViewModel,  // Pasa el AuthViewModel
    modifier: Modifier = Modifier
) {
    val usernameState = remember { mutableStateOf("") }
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val repeatPasswordState = remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF6F7DF))
    ) {
        // Fondo diagonal
        Box(
            modifier = Modifier
                .offset(x = (-85).dp, y = (-40).dp)
                .size(width = 434.31.dp, height = 226.93.dp)
                .graphicsLayer(rotationZ = -22.88f)
                .background(Color(0xFF41B9B2))
        )

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to BITE!",
                fontSize = 24.sp,
                color = Color.Black
            )

            Image(
                painter = rememberAsyncImagePainter("https://res.cloudinary.com/dlpnivtso/image/upload/v1724879209/handgreet_ejo4mq.png"),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(60.dp),
                contentScale = ContentScale.Fit
            )

            Text(
                text = "Hello, I guess you are new around here.\nYou can start using the application after sign up.",
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 8.dp),
                lineHeight = 20.sp
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 43.dp)
                .align(Alignment.Center)
        ) {
            InputField(label = "Username", value = usernameState.value, onValueChange = { usernameState.value = it })
            Spacer(modifier = Modifier.height(16.dp))
            InputField(label = "Email Address", value = emailState.value, onValueChange = { emailState.value = it })
            Spacer(modifier = Modifier.height(16.dp))
            InputField(label = "Password", value = passwordState.value, onValueChange = { passwordState.value = it }, isPassword = true)
            Spacer(modifier = Modifier.height(16.dp))
            InputField(label = "Repeat Password", value = repeatPasswordState.value, onValueChange = { repeatPasswordState.value = it }, isPassword = true)
        }

        ActionButton(
            text = "NEXT",
            onClick = { navController.navigate("register2") },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
        )
    }
}
