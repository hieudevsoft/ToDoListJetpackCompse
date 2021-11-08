package com.devapp.to_docompose.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.devapp.to_docompose.navigation.destinations.listComposable
import com.devapp.to_docompose.navigation.destinations.splashComposable
import com.devapp.to_docompose.navigation.destinations.taskComposable
import com.devapp.to_docompose.ui.viewmodels.SharedViewModel
import com.devapp.to_docompose.util.Constants.SPLASH_SCREEN
import com.google.accompanist.navigation.animation.AnimatedNavHost

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun SetupNavigation(navController: NavHostController,sharedViewModel: SharedViewModel) {
    val screens = remember(navController) {
        Screens(navController)
    }
    AnimatedNavHost(navController = navController, startDestination = SPLASH_SCREEN) {

        splashComposable(
            navigateToListScreen = screens.splash
        )
        listComposable(
            navigateToTaskScreen = screens.task,
            sharedViewModel = sharedViewModel
        )
        taskComposable(
            sharedViewModel = sharedViewModel,
            navigateToListScreen = screens.list
        )
    }
}