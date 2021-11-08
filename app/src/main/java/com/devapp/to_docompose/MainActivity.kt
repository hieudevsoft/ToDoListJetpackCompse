package com.devapp.to_docompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavHostController
import com.devapp.to_docompose.navigation.SetupNavigation
import com.devapp.to_docompose.ui.theme.ToDoComposeTheme
import com.devapp.to_docompose.ui.viewmodels.SharedViewModel
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalAnimationApi
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoComposeTheme {
                nabController = rememberAnimatedNavController()
                SetupNavigation(navController = nabController,sharedViewModel = sharedViewModel)
            }
        }
    }
    private lateinit var nabController: NavHostController
    private val sharedViewModel:SharedViewModel by viewModels()
}

