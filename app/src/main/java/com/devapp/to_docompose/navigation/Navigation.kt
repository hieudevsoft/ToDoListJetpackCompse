package com.devapp.to_docompose.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.devapp.to_docompose.navigation.destinations.listComposable
import com.devapp.to_docompose.navigation.destinations.taskComposable
import com.devapp.to_docompose.ui.viewmodels.SharedViewModel
import com.devapp.to_docompose.util.Constants.LIST_SCREEN

@ExperimentalMaterialApi
@Composable
fun SetupNavigation(navController: NavHostController,sharedViewModel: SharedViewModel) {
    val screens = remember(navController) {
        Screens(navController)
    }
    NavHost(navController = navController, startDestination = LIST_SCREEN) {
        listComposable(
            navigateToTaskScreen = screens.task,sharedViewModel = sharedViewModel
        )
        taskComposable(
            navigateToListScreen = screens.list
        )
    }
}