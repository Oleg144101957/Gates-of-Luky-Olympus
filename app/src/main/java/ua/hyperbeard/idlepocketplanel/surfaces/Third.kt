package ua.hyperbeard.idlepocketplanel.surfaces

import android.app.Activity
import android.content.pm.ActivityInfo
import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import ua.hyperbeard.idlepocketplanel.R
import ua.hyperbeard.idlepocketplanel.ui.theme.Targets
import kotlin.math.roundToInt

@Composable
fun Third(n: NavHostController, t: String) {
    val c = LocalContext.current as Activity
    c.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    if (t == Targets.ThirdScreen.dest) {
        LaunchedEffect(Unit) { Log.d("eee", "ok 200") }
    }
    val s = remember { mutableStateOf(0) }
    val h = LocalConfiguration.current.screenHeightDp
    val f = remember { mutableIntStateOf(2900) }
    val a1 = remember { Animatable(initialValue = 0f) }
    val a2 = remember { Animatable(initialValue = 0f) }
    val a3 = remember { Animatable(initialValue = 0f) }
    val a4 = remember { Animatable(initialValue = 0f) }
    val e1x = remember { mutableStateOf(0f) }
    val e1y = remember { mutableStateOf(0f) }
    val e2x = remember { mutableStateOf(0f) }
    val e2y = remember { mutableStateOf(0f) }
    val e3x = remember { mutableStateOf(0f) }
    val e3y = remember { mutableStateOf(0f) }
    val e4x = remember { mutableStateOf(0f) }
    val e4y = remember { mutableStateOf(0f) }
    val bx = remember { mutableStateOf(0f) }
    val bxe = remember { mutableStateOf(0f) }
    val by = remember { mutableStateOf(0f) }
    val v1 = remember { mutableStateOf(true) }
    val v2 = remember { mutableStateOf(true) }
    val v3 = remember { mutableStateOf(true) }
    val v4 = remember { mutableStateOf(true) }
    val ox = remember { mutableStateOf(0f) }
    fun c1() {
        if (e1x.value in bx.value..bxe.value && e1y.value >= by.value) {
            s.value += 1
            v1.value = false
        }
    }
    fun c2() {
        if (e2x.value in bx.value..bxe.value && e2y.value >= by.value) {
            s.value += 1
            v2.value = false
        }
    }
    fun c3() {
        if (e3x.value in bx.value..bxe.value && e3y.value >= by.value) {
            s.value += 1
            v3.value = false
        }
    }
    fun c4() {
        if (e4x.value in bx.value..bxe.value && e4y.value >= by.value) {
            s.value += 1
            v4.value = false
        }
    }
    LaunchedEffect(Unit) { a1.animateTo(targetValue = h.toFloat(), animationSpec = infiniteRepeatable(tween(f.value, delayMillis = 150, easing = FastOutLinearInEasing), repeatMode = RepeatMode.Restart)) }
    LaunchedEffect(Unit) { a2.animateTo(targetValue = h.toFloat(), animationSpec = infiniteRepeatable(tween(f.value, delayMillis = 500, easing = LinearEasing), repeatMode = RepeatMode.Restart)) }
    LaunchedEffect(Unit) { a3.animateTo(targetValue = h.toFloat(), animationSpec = infiniteRepeatable(tween(f.value, delayMillis = 10, easing = FastOutSlowInEasing), repeatMode = RepeatMode.Restart)) }
    LaunchedEffect(Unit) { a4.animateTo(targetValue = h.toFloat(), animationSpec = infiniteRepeatable(tween(f.value, delayMillis = 250, easing = FastOutSlowInEasing), repeatMode = RepeatMode.Restart)) }
    LaunchedEffect(Unit) { repeat(64) { delay(3000); v1.value = true; v2.value = true; v3.value = true; v4.value = true } }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(painter = painterResource(id = R.drawable.gameandmenubackground), contentDescription = "back", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.FillBounds)
        Icon(imageVector = Icons.Default.Close, contentDescription = "navigate to the menu", tint = Color.White, modifier = Modifier.align(Alignment.TopEnd).padding(32.dp).clickable { n.navigate(Targets.SecondScreen.dest) })
        if (v1.value) {
            Image(painter = painterResource(id = R.drawable.pieceofluck1), contentDescription = "", modifier = Modifier.align(Alignment.TopCenter).offset(y = a1.value.dp, x = 128.dp).onGloballyPositioned { e1y.value = it.positionInParent().y + it.size.height; e1x.value = it.positionInParent().x + it.size.width / 2; c1() })
        }
        if (v2.value) {
            Image(painter = painterResource(id = R.drawable.pieceofluck2), contentDescription = "", modifier = Modifier.align(Alignment.TopCenter).offset(y = a2.value.dp, x = 64.dp).onGloballyPositioned { e2y.value = it.positionInParent().y + it.size.height; e2x.value = it.positionInParent().x + it.size.width / 2; c2() })
        }
        if (v3.value) {
            Image(painter = painterResource(id = R.drawable.pieceofluck3), contentDescription = "", modifier = Modifier.align(Alignment.TopCenter).offset(y = a3.value.dp, x = (-128).dp).onGloballyPositioned { e3y.value = it.positionInParent().y + it.size.height; e3x.value = it.positionInParent().x + it.size.width / 2; c3() })
        }
        if (v4.value) {
            Image(painter = painterResource(id = R.drawable.pieceofluck4), contentDescription = "", modifier = Modifier.align(Alignment.TopCenter).offset(y = a4.value.dp, x = (-64).dp).onGloballyPositioned { e4y.value = it.positionInParent().y + it.size.height; e4x.value = it.positionInParent().x + it.size.width / 2; c4() })
        }
        Image(painter = painterResource(id = R.drawable.basebackground), contentDescription = "movable", modifier = Modifier.align(Alignment.BottomCenter).padding(16.dp).offset { IntOffset(x = ox.value.roundToInt(), y = 0) }.pointerInput(Unit) {
            detectDragGestures { change, dragAmount ->
                change.consume()
                ox.value += dragAmount.x
            }
        }.onGloballyPositioned { bx.value = it.positionInParent().x; bxe.value = it.positionInParent().x + it.size.width; by.value = it.positionInParent().y })
        Text(text = "Your score is ${s.value}", color = Color.White, fontSize = 24.sp, modifier = Modifier.align(Alignment.TopCenter).padding(top = 16.dp))
    }
}


//@Composable
//fun Third(navigator: NavHostController, target: String){
//
//    val currentActivity = LocalContext.current as Activity
//    currentActivity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
//
//
//    if (target == Targets.ThirdScreen.dest){
//        LaunchedEffect(Unit){
//            Log.d("eee", "ok 200")
//        }
//    }
//
//
//    val score = remember {
//        mutableStateOf(0)
//    }
//
//    val theHeightOfTheScreen = LocalConfiguration.current.screenHeightDp
//
//    val fallingSpeed = remember {
//        mutableIntStateOf(2900)
//    }
//
//    val ani1 = remember {
//        Animatable(initialValue = 0f)
//    }
//
//    val ani2 = remember {
//        Animatable(initialValue = 0f)
//    }
//
//    val ani3 = remember {
//        Animatable(initialValue = 0f)
//    }
//
//    val ani4 = remember {
//        Animatable(initialValue = 0f)
//    }
//
//
//
//    val elem1offsetX = remember {
//        mutableStateOf(0f)
//    }
//
//    val elem1offsetY = remember {
//        mutableStateOf(0f)
//    }
//
//
//    val elem2offsetX = remember {
//        mutableStateOf(0f)
//    }
//
//    val elem2offsetY = remember {
//        mutableStateOf(0f)
//    }
//
//
//    val elem3offsetX = remember {
//        mutableStateOf(0f)
//    }
//
//    val elem3offsetY = remember {
//        mutableStateOf(0f)
//    }
//
//    val elem4offsetX = remember {
//        mutableStateOf(0f)
//    }
//
//    val elem4offsetY = remember {
//        mutableStateOf(0f)
//    }
//
//
//    val baseOffsetX = remember {
//        mutableStateOf(0f)
//    }
//
//
//    val baseOffsetXEndPoint = remember {
//        mutableStateOf(0f)
//    }
//
//    val baseOffsetY = remember {
//        mutableStateOf(0f)
//    }
//
//
//
//
//    val isVisible1 = remember {
//        mutableStateOf(true)
//    }
//
//    val isVisible2 = remember {
//        mutableStateOf(true)
//    }
//
//    val isVisible3 = remember {
//        mutableStateOf(true)
//    }
//
//    val isVisible4 = remember {
//        mutableStateOf(true)
//    }
//
//    val offsetX = remember { mutableStateOf(0f) }
//
//    fun checkCatch1(){
//        //check x and y
//        if (elem1offsetX.value in baseOffsetX.value..baseOffsetXEndPoint.value && elem1offsetY.value >= baseOffsetY.value){
//            score.value += 1
//            isVisible1.value = false
//        }
//    }
//
//
//    fun checkCatch2(){
//        //check x and y
//        if (elem2offsetX.value in baseOffsetX.value..baseOffsetXEndPoint.value && elem2offsetY.value >= baseOffsetY.value){
//            score.value += 1
//            isVisible2.value = false
//        }
//    }
//
//    fun checkCatch3(){
//        //check x and y
//        if (elem3offsetX.value in baseOffsetX.value..baseOffsetXEndPoint.value && elem3offsetY.value >= baseOffsetY.value){
//            score.value += 1
//            isVisible3.value = false
//        }
//    }
//
//    fun checkCatch4(){
//        //check x and y
//        if (elem4offsetX.value in baseOffsetX.value..baseOffsetXEndPoint.value && elem4offsetY.value >= baseOffsetY.value){
//            score.value += 1
//            isVisible4.value = false
//        }
//    }
//
//    LaunchedEffect(Unit){
//        ani1.animateTo(
//            targetValue = theHeightOfTheScreen.toFloat(),
//            animationSpec = infiniteRepeatable(
//                tween(fallingSpeed.value, delayMillis = 150, easing = FastOutLinearInEasing),
//                repeatMode = RepeatMode.Restart
//            )
//        )
//    }
//
//    LaunchedEffect(Unit){
//        ani2.animateTo(
//            targetValue = theHeightOfTheScreen.toFloat(),
//            animationSpec = infiniteRepeatable(
//                tween(fallingSpeed.value, delayMillis = 500, easing = LinearEasing),
//                repeatMode = RepeatMode.Restart
//            )
//        )
//    }
//
//    LaunchedEffect(Unit){
//        ani3.animateTo(
//            targetValue = theHeightOfTheScreen.toFloat(),
//            animationSpec = infiniteRepeatable(
//                tween(fallingSpeed.value, delayMillis = 10, easing = FastOutSlowInEasing),
//                repeatMode = RepeatMode.Restart
//            )
//        )
//    }
//
//    LaunchedEffect(Unit){
//        ani4.animateTo(
//            targetValue = theHeightOfTheScreen.toFloat(),
//            animationSpec = infiniteRepeatable(
//                tween(fallingSpeed.value, delayMillis = 250, easing = FastOutSlowInEasing),
//                repeatMode = RepeatMode.Restart
//            )
//        )
//    }
//
//    LaunchedEffect(Unit){
//        repeat(64){
//            delay(3000)
//            isVisible1.value = true
//            isVisible2.value = true
//            isVisible3.value = true
//            isVisible4.value = true
//        }
//    }
//
//    Box(modifier = Modifier.fillMaxSize()){
//
//        Image(
//            painter = painterResource(id = R.drawable.gameandmenubackground),
//            contentDescription = "back",
//            modifier = Modifier
//                .fillMaxSize(),
//            contentScale = ContentScale.FillBounds
//        )
//
//
//        Icon(
//            imageVector = Icons.Default.Close,
//            contentDescription = "navigate to the menu",
//            tint = Color.White,
//            modifier = Modifier
//                .align(Alignment.TopEnd)
//                .padding(32.dp)
//                .clickable {
//                    navigator.navigate(Targets.SecondScreen.dest)
//                }
//        )
//
//
//        if (isVisible1.value){
//            Image(
//                painter = painterResource(id = R.drawable.pieceofluck1),
//                contentDescription = "",
//                modifier = Modifier
//                    .align(Alignment.TopCenter)
//                    .offset(y = ani1.value.dp, x = 128.dp)
//                    .onGloballyPositioned {
//                        elem1offsetY.value = it.positionInParent().y + it.size.height
//                        elem1offsetX.value = it.positionInParent().x + it.size.width / 2
//                        checkCatch1()
//                    }
//            )
//        }
//
//
//        if (isVisible2.value){
//            Image(
//                painter = painterResource(id = R.drawable.pieceofluck2),
//                contentDescription = "",
//                modifier = Modifier
//                    .align(Alignment.TopCenter)
//                    .offset(y = ani2.value.dp, x = 64.dp)
//                    .onGloballyPositioned {
//                        elem2offsetY.value = it.positionInParent().y + it.size.height
//                        elem2offsetX.value = it.positionInParent().x + it.size.width / 2
//                        checkCatch2()
//                    }
//            )
//        }
//
//
//        if (isVisible3.value){
//            Image(
//                painter = painterResource(id = R.drawable.pieceofluck3),
//                contentDescription = "",
//                modifier = Modifier
//                    .align(Alignment.TopCenter)
//                    .offset(y = ani3.value.dp, x = (-128).dp)
//                    .onGloballyPositioned {
//                        elem3offsetY.value = it.positionInParent().y + it.size.height
//                        elem3offsetX.value = it.positionInParent().x + it.size.width / 2
//                        checkCatch3()
//                    }
//            )
//        }
//
//        if (isVisible4.value){
//            Image(
//                painter = painterResource(id = R.drawable.pieceofluck4),
//                contentDescription = "",
//                modifier = Modifier
//                    .align(Alignment.TopCenter)
//                    .offset(y = ani4.value.dp, x = (-64).dp)
//                    .onGloballyPositioned {
//                        elem4offsetY.value = it.positionInParent().y + it.size.height
//                        elem4offsetX.value = it.positionInParent().x + it.size.width / 2
//                        checkCatch4()
//                    }
//            )
//        }
//
//
//
//
//        Image(
//            painter = painterResource(id = R.drawable.basebackground),
//            contentDescription = "movable",
//            modifier = Modifier
//                .align(Alignment.BottomCenter)
//                .padding(16.dp)
//
//                .offset {
//                    IntOffset(x = offsetX.value.roundToInt(), y = 0)
//                }
//                .pointerInput(Unit) {
//                    detectDragGestures { change, dragAmount ->
//                        change.consume()
//                        offsetX.value += dragAmount.x
//                    }
//                }
//                .onGloballyPositioned {
//                    baseOffsetX.value = it.positionInParent().x
//                    baseOffsetXEndPoint.value = it.positionInParent().x + it.size.width
//                    baseOffsetY.value = it.positionInParent().y
//                }
//
//        )
//
//        Text(
//            text = "Your score is ${score.value}",
//            color = Color.White,
//            fontSize = 24.sp,
//            modifier = Modifier
//                .align(Alignment.TopCenter)
//                .padding(top = 16.dp)
//        )
//    }
//}