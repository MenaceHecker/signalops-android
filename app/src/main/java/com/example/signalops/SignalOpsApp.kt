package com.example.signalops


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.example.signalops.app.auth.AuthNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            MaterialTheme {
                Surface {
                    AuthNavGraph(
                        navController = navController,
                        onAuthSuccess = {
                            // TODO: navigate to Home graph later
                            // e.g. navController.navigate("home") { popUpTo("login") { inclusive = true } }
                        }
                    )
                }
            }
        }
    }
}
