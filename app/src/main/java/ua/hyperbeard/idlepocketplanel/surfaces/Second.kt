package ua.hyperbeard.idlepocketplanel.surfaces

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ua.hyperbeard.idlepocketplanel.R
import ua.hyperbeard.idlepocketplanel.ui.theme.Targets


@Composable
fun Second(navigator: NavHostController, target: String){

    val currentActivity = LocalContext.current as Activity
    currentActivity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    val context = LocalContext.current

    Image(
        painter = painterResource(id = R.drawable.gameandmenubackground),
        contentDescription = "game and menu back",
        modifier = Modifier
            .fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )

    Box(modifier = Modifier
        .fillMaxSize()
    ){
        Column(modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 96.dp)
        ) {

            Box {
                Image(
                    painter = painterResource(id = R.drawable.basebackground),
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable {
                            navigator.navigate(Targets.ThirdScreen.dest)
                        }
                )
                
                Text(
                    text = "Play Catch Gold",
                    color = Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            Box {
                Image(
                    painter = painterResource(id = R.drawable.basebackground),
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable {
                            navigator.navigate(Targets.FourthScreen.dest)
                        }
                )

                Text(
                    text = "Play 3 in line",
                    color = Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }


            Box {
                Image(
                    painter = painterResource(id = R.drawable.basebackground),
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable {
                            navigator.navigate(Targets.FifthScreen.dest)
                        }
                )

                Text(
                    text = "Settings",
                    color = Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }


            Box {
                Image(
                    painter = painterResource(id = R.drawable.basebackground),
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable {
                            val intentToThePolicy = Intent(context, PolicyActivity::class.java)
                            intentToThePolicy.putExtra(Targets.MODE, Targets.CLEAR)
                            context.startActivity(intentToThePolicy)
                        }
                )

                Text(
                    text = "Policy",
                    color = Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            Box {
                Image(
                    painter = painterResource(id = R.drawable.basebackground),
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable {

                        }
                )

                Text(
                    text = "Close app",
                    color = Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}