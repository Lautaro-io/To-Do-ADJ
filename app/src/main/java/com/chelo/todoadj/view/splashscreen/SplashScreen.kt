package com.chelo.todoadj.view.splashscreen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chelo.todoadj.R
import com.chelo.todoadj.ui.theme.Black
import com.chelo.todoadj.ui.theme.Grey
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navigate: () -> Unit) {
    val scaleIcon = remember { Animatable(0f) }
    val rotate = remember { Animatable(0f) }
    val scaleTitle = remember { Animatable(0f) }
    val scaleText = remember { Animatable(0f) }


    LaunchedEffect(true) {
        launch {
            rotate.animateTo(
                targetValue = 360f,
                animationSpec = tween(
                    durationMillis = 900,
                    easing = LinearEasing
                )
            )
        }
        launch {
            scaleIcon.animateTo(
                targetValue = 1.5f,
                animationSpec = tween(
                    durationMillis = 900,
                    easing = FastOutSlowInEasing
                )
            )
        }

        scaleTitle.animateTo(
            targetValue = 1.5f,
            animationSpec = tween(
                durationMillis = 900,
                easing = FastOutSlowInEasing
            )
        )
        scaleText.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing
            )
        )
        delay(1000L)
        navigate()
    }



    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Surface(
                modifier = Modifier
                    .size(76.dp)
                    .scale(scaleIcon.value)
                    .rotate(rotate.value),
                shape = RoundedCornerShape(16.dp),
                color = Grey,

                ) {
                Icon(
                    painter = painterResource(R.drawable.ic_icon_app),
                    contentDescription = "Icon App",
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxSize(),
                    tint = Black
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "To Do App ADJ",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .scale(scaleTitle.value)
            )
            Text(
                text = "Organiza tu dia de manera eficiente",
                fontSize = 18.sp,
                modifier = Modifier.scale(scaleText.value),
                color = MaterialTheme.colorScheme.secondary
            )


        }

    }
}

