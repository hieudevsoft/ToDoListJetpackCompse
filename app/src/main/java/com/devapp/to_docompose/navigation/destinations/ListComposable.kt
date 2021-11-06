package com.devapp.to_docompose.navigation.destinations

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.graphics.ShaderBrush
import androidx.navigation.NavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.devapp.to_docompose.data.models.Priority
import com.devapp.to_docompose.ui.screens.list.ListScreen
import com.devapp.to_docompose.ui.viewmodels.SharedViewModel
import com.devapp.to_docompose.util.Constants.LIST_ARGUMENT_KEY
import com.devapp.to_docompose.util.Constants.LIST_SCREEN

@ExperimentalMaterialApi
fun NavGraphBuilder.listComposable(
    navigateToTaskScreen:(taskId:Int)->Unit,
    sharedViewModel: SharedViewModel
){
    composable(
        route = LIST_SCREEN,
        arguments = listOf(
            navArgument(LIST_ARGUMENT_KEY){
                type = NavType.StringType
            }
        )
    ){
        ListScreen(
            navigateToTaskScreen = navigateToTaskScreen,
            sharedViewModel = sharedViewModel
        )
    }
}