package com.devapp.to_docompose.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import com.devapp.to_docompose.ui.screens.splash.SplashScreen
import com.devapp.to_docompose.util.Constants
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
fun NavGraphBuilder.splashComposable(
    navigateToListScreen: () -> Unit
) {
    composable(
        route = Constants.SPLASH_SCREEN,
        exitTransition = { _, _ ->
                slideOutVertically(
                    animationSpec = tween(2000),
                    targetOffsetY = {
                        -it
                    }
                )
        }
    ) {
        SplashScreen(navigateToListScreen = navigateToListScreen)
    }
}