package ua.hyperbeard.idlepocketplanel.surfaces

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ua.hyperbeard.idlepocketplanel.R
import ua.hyperbeard.idlepocketplanel.ui.theme.Targets
import ua.hyperbeard.idlepocketplanel.viewmodels.LuckyViewModel

@Composable
fun Fourth(navigator: NavHostController, target: String){
    val currentActivity = LocalContext.current as Activity
    currentActivity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    EuphoriaScene(navigator)

}


@Composable
fun EuphoriaScene(navigator: NavHostController) {
    //Game

    val luckyViewModel = viewModel<LuckyViewModel>()
    val scores = luckyViewModel.existenceScores.collectAsState()


    LaunchedEffect(Unit){
        luckyViewModel.initialStateOfNewGame()
    }

    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.gameandmenubackground),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Gameplay(luckyViewModel, navigator)
        Scores(scores = scores.value)
    }
}
@Composable
fun BoxScope.Scores(scores: Int){
    Box(modifier = Modifier
        .fillMaxWidth()
        .align(Alignment.TopCenter)){

        Image(
            painter = painterResource(id = R.drawable.basebackground),
            contentDescription = "scores background",
            modifier = Modifier
                .align(Alignment.Center)
        )

        Text(
            text = "$scores scores",
            color = Color.White,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Composable
fun BoxScope.Gameplay(vm: LuckyViewModel, navigator: NavHostController){

    val crazyList = vm.existenceList.collectAsState()
    val existenceLives = vm.existenceLives.collectAsState()
    val existenceTime = vm.existenceTime.collectAsState()
    val context = LocalContext.current
    val sp = context.getSharedPreferences(Targets.SHARED_P, Context.MODE_PRIVATE)
    val username = sp.getString(Targets.PLAYER_NAME, "user")

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .align(Alignment.Center),
        columns = GridCells.Fixed(4),
        content = {
            items(16){
                Image(
                    painter = painterResource(id = if(crazyList.value[it].menu.boolean) crazyList.value[it].frontImage else crazyList.value[it].rearImage),
                    contentDescription = "play",
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            vm.touchElement(it)
                        }
                )
            }
        }
    )

    Text(
        text = "Time: ${existenceTime.value}",
        color = Color.White,
        modifier = Modifier
            .align(Alignment.BottomStart)
            .padding(bottom = 16.dp)
    )

    Text(
        text = "Hello $username",
        color = Color.White,
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 16.dp)

    )

    if (existenceTime.value == 0){
        GameOverScreen("have no time", navigator)
    }


    when (existenceLives.value) {
        2 -> {
            Row(modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 16.dp)

            ) {

                Image(
                    painter = painterResource(id = R.drawable.pieceofluck4),
                    contentDescription = "lives",
                    modifier = Modifier.size(32.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.pieceofluck4),
                    contentDescription = "lives",
                    modifier = Modifier.size(32.dp)
                )
            }
        }
        1 -> {
            Row(modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 16.dp)

            ) {

                Image(
                    painter = painterResource(id = R.drawable.pieceofluck4),
                    contentDescription = "lives",
                    modifier = Modifier.size(32.dp)
                )
            }
        }
        else -> {

            //Game over
            GameOverScreen("You lose", navigator)
        }
    }
}


@Composable
fun GameOverScreen(msg: String, navigator: NavHostController){


    Box(modifier = Modifier
        .fillMaxSize()

    ){
        Text(
            text = "Game Over $msg",
            color = Color.Black,
            modifier = Modifier.align(Alignment.Center)
        )

        Image(
            painter = rememberVectorPainter(image = Icons.Default.Close),
            contentDescription = "Close button",
            colorFilter = ColorFilter.tint(Color.Black),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
                .size(64.dp)
                .clickable {
                    navigator.navigate(Targets.SecondScreen.dest)
                }
        )
    }
}