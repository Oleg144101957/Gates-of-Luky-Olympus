package ua.hyperbeard.idlepocketplanel.surfaces

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import ua.hyperbeard.idlepocketplanel.R
import ua.hyperbeard.idlepocketplanel.ui.theme.Targets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Fifth(navigator: NavHostController, target: String){
    val currentActivity = LocalContext.current as Activity
    currentActivity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    val context = LocalContext.current
    val sp = context.getSharedPreferences(Targets.SHARED_P, Context.MODE_PRIVATE)
    val name = remember {
        mutableStateOf(sp.getString(Targets.PLAYER_NAME, "user") ?: "user")
    }


    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.gameandmenubackground),
            contentDescription = "background",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        TextField(
            value = name.value,
            onValueChange = {
                name.value = it
                sp.edit().putString(Targets.PLAYER_NAME, it).apply()
            },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .align(Alignment.Center)
        )
    }
}