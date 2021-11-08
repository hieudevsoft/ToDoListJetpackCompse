package com.devapp.to_docompose.navigation

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.devapp.to_docompose.util.Action
import com.devapp.to_docompose.util.Constants.LIST_SCREEN
import com.devapp.to_docompose.util.Constants.SPLASH_SCREEN
import com.devapp.to_docompose.util.Constants.TASK_SCREEN

class Screens(navController: NavHostController) {
    val splash:()->Unit = {
        navController.navigate(route = "list/${Action.NO_ACTION.name}"){
            popUpTo(SPLASH_SCREEN){inclusive = true}
        }
    }
    val list:(Action)->Unit={
        navController.navigate("list/${it.name}"){
            popUpTo(LIST_SCREEN){inclusive = true}
        }
    }
    val task:(Int)->Unit = {
        navController.navigate("task/$it"){
        }
    }
}