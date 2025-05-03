package com.yusuf0080.manajementugas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.yusuf0080.manajementugas.ui.screen.MainScreen
import com.yusuf0080.manajementugas.ui.theme.ManajemenTugasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ManajemenTugasTheme {
                MainScreen()
            }
        }
    }
}

