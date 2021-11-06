package com.devapp.to_docompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.devapp.to_docompose.navigation.SetupNavigation
import com.devapp.to_docompose.ui.theme.ToDoComposeTheme
import com.devapp.to_docompose.ui.viewmodels.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var nabController: NavHostController
    private val sharedViewModel:SharedViewModel by viewModels()
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoComposeTheme {
                nabController = rememberNavController()
                SetupNavigation(navController = nabController,sharedViewModel = sharedViewModel)
            }
        }
    }
}

