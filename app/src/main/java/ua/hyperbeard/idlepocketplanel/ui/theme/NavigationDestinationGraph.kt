package ua.hyperbeard.idlepocketplanel.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ua.hyperbeard.idlepocketplanel.surfaces.Fifth
import ua.hyperbeard.idlepocketplanel.surfaces.First
import ua.hyperbeard.idlepocketplanel.surfaces.Fourth
import ua.hyperbeard.idlepocketplanel.surfaces.Second
import ua.hyperbeard.idlepocketplanel.surfaces.Third


@Composable
fun NavigationDestinationGraph(){

    val navigator = rememberNavController()

    NavHost(navController = navigator, startDestination = Targets.FirstScreen.dest){

        composable(route = Targets.FirstScreen.dest){
            First(navigator = navigator, target = Targets.FirstScreen.dest)
        }

        composable(route = Targets.SecondScreen.dest){
            Second(navigator = navigator, target = Targets.SecondScreen.dest)
        }

        composable(route = Targets.ThirdScreen.dest){
            Third(n = navigator, t = Targets.ThirdScreen.dest)
        }

        composable(route = Targets.FourthScreen.dest){
            Fourth(navigator = navigator, target = Targets.FourthScreen.dest)
        }

        composable(route = Targets.FifthScreen.dest){
            Fifth(navigator = navigator, target = Targets.FifthScreen.dest)
        }
    }
}