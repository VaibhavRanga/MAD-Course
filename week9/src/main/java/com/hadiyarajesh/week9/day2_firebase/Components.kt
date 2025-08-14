package com.hadiyarajesh.week9.day2_firebase

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.hadiyarajesh.week9.day2_firebase.authentication.AuthUiState

@Composable
fun EmailPasswordAndButton(
    modifier: Modifier = Modifier,
    email: String,
    password: String,
    isEmailError: Boolean = false,
    isPasswordError: Boolean = false,
    actionButtonText: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onActionButtonClick: () -> Unit
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = { onEmailChange(it) },
            label = { Text("Email") },
            placeholder = { Text("Enter your email") },
            singleLine = true,
            maxLines = 1,
            isError = isEmailError,
            supportingText = {
                if (isEmailError) {
                    Text(
                        text = "Please enter a valid email.",
                        color = MaterialTheme.colorScheme.error
                    )
                } else null
            },
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = { onPasswordChange(it) },
            label = { Text("Password") },
            placeholder = { Text("Enter your password") },
            singleLine = true,
            maxLines = 1,
            isError = isPasswordError,
            supportingText = {
                if (isPasswordError) {
                    Text(
                        text = "Please enter a valid password with minimum of 8 characters.",
                        color = MaterialTheme.colorScheme.error
                    )
                } else null
            },
            shape = RoundedCornerShape(16.dp),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onActionButtonClick) {
            Text(actionButtonText)
        }
    }
}

@Composable
fun AuthUiStateView(
    modifier: Modifier = Modifier,
    authResult: AuthUiState
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (authResult) {
            is AuthUiState.Error -> {
                Text(text = authResult.message)
            }

            AuthUiState.Loading -> {
                CircularProgressIndicator()
            }

            AuthUiState.Success -> {}

            AuthUiState.Initial -> {}
        }
    }
}
