package com.devapp.to_docompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.devapp.to_docompose.navigation.destinations.listComposable
import com.devapp.to_docompose.navigation.destinations.taskComposable
import com.devapp.to_docompose.util.Constants.LIST_SCREEN

@Composable
fun SetupNavigation(navController: NavHostController) {
    val screens = remember(navController) {
        Screens(navController)
    }
    NavHost(navController = navController, startDestination = LIST_SCREEN) {
        listComposable(
            navigateToTaskScreen = screens.task,{},{}
        )
        taskComposable(
            navigateToListScreen = screens.list
        )
    }
}