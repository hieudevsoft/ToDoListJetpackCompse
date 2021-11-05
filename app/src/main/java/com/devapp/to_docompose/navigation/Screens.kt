package com.devapp.to_docompose.navigation

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.devapp.to_docompose.util.Action
import com.devapp.to_docompose.util.Constants.LIST_SCREEN
import com.devapp.to_docompose.util.Constants.TASK_SCREEN

class Screens(navController: NavHostController) {
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