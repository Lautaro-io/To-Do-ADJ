package com.chelo.todoadj

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.chelo.todoadj.ui.theme.ToDoADJTheme
import com.chelo.todoadj.view.navigation.NavigationWrapper
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoADJTheme {
                NavigationWrapper()
            }
        }
    }
}

