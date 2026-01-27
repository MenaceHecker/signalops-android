package com.example.signalops.app.auth


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SignupScreen(
    onNavigateBack: () -> Unit,
    onSignupSuccess: () -> Unit,
)
{
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Signup", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = onSignupSuccess,
            modifier = Modifier.fillMaxWidth()
        ) { Text("Create account") }

        Spacer(Modifier.height(8.dp))

        TextButton(onClick = onNavigateBack) {
            Text("Back to login")
        }
    }
}