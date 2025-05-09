package com.yusuf0080.manajementugas

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.yusuf0080.manajementugas.navigation.SetupNavGraph
import com.yusuf0080.manajementugas.ui.theme.ManajemenTugasTheme
import com.yusuf0080.manajementugas.util.ThemeDataStore

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val themeDataStore = ThemeDataStore(this)
            val isDarkTheme by themeDataStore.themeFlow.collectAsState(initial = isSystemInDarkTheme())

            ManajemenTugasTheme(darkTheme = isDarkTheme) {
                SetupNavGraph()
            }
        }
    }
}

