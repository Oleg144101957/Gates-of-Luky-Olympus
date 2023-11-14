package ua.hyperbeard.idlepocketplanel.surfaces

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import ua.hyperbeard.idlepocketplanel.R
import ua.hyperbeard.idlepocketplanel.ui.theme.Targets


@Composable
fun First(navigator: NavHostController, target: String){


    val currentActivity = LocalContext.current as Activity
    currentActivity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


    LaunchedEffect(Unit){
        delay(2050)
        navigator.navigate(Targets.SecondScreen.dest)
    }

    val infiniteTransition = rememberInfiniteTransition(label = "")

    // Define an animated value with an infinite repeat
    val offsetY by infiniteTransition.animateFloat(
        initialValue = 0.dp.value, // Start the animation from above the screen
        targetValue = -256.dp.value,   // End the animation below the screen
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )


    Image(
        painter = painterResource(id = R.drawable.startbackground),
        contentDescription = "lback",
        modifier = Modifier
            .fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )

    Box(modifier = Modifier.fillMaxSize()){
        Text(
            text = "Loading...",
            color = Color.Black,
            fontSize = 32.sp,
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = 64.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.pieceofluck1),
            contentDescription = "timeelement",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = offsetY.dp)
        )
    }
}


@Composable
@Preview
fun PrevFirst(){
    val nav = rememberNavController()
    First(navigator = nav, target = "dasdsa")
}