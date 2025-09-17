package com.chelo.todoadj.view.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.unit.dp
import com.chelo.todoadj.ui.theme.Grey
import com.chelo.todoadj.ui.theme.LightGrey

@Composable
fun EmptyViewScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(LightGrey.copy(alpha = 0.1f))
                    .size(56.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .background(Transparent)
                        .size(40.dp)
                        .clip(CircleShape)
                        .padding(8.dp)
                        .border(2.dp, Grey, CircleShape)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text("No hay tareas aun", color = Grey)
            Text("Toca el botón + para añadir tu primera tarea", color = Grey)

        }
    }
}

