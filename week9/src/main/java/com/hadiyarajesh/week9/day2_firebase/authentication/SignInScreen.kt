package com.hadiyarajesh.week9.day2_firebase.authentication

import android.util.Patterns
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hadiyarajesh.week9.day2_firebase.AuthUiStateView
import com.hadiyarajesh.week9.day2_firebase.EmailPasswordAndButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    onSignUpClick: () -> Unit,
    viewModel: AuthenticationViewModel = hiltViewModel()
) {
    val authUiState by viewModel.authUiState.collectAsStateWithLifecycle()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isEmailError by remember { mutableStateOf(false) }
    var isPasswordError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Sign In") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EmailPasswordAndButton(
                    email = email,
                    password = password,
                    actionButtonText = "Sign In",
                    onEmailChange = { email = it },
                    onPasswordChange = { password = it },
                    isEmailError = isEmailError,
                    isPasswordError = isPasswordError,
                    onActionButtonClick = {
                        val isValidEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
                        isEmailError = !isValidEmail

                        val isValidPassword = password.length >= 8
                        isPasswordError = !isValidPassword

                        if (isEmailError && isPasswordError) {
                            return@EmailPasswordAndButton
                        }

                        viewModel.signIn(email, password)
                    }
                )
            }

            AuthUiStateView(
                modifier = Modifier
//                    .weight(1f)
                    .padding(16.dp),
                authResult = authUiState
            )

            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        onSignUpClick()
                    },
                text = "Don't have an account? Sign Up",
                color = Color.Blue,
                textDecoration = TextDecoration.Underline,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}