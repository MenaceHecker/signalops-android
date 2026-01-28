package com.example.signalops

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.signalops.SignalOpsApp
import com.example.signalops.ui.theme.SignalOpsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignalOpsTheme {
                SignalOpsApp()
            }
        }
    }
}
