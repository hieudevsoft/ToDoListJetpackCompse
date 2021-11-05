package com.devapp.to_docompose.navigation.destinations

import androidx.navigation.NavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.devapp.to_docompose.data.models.Priority
import com.devapp.to_docompose.ui.screens.list.ListScreen
import com.devapp.to_docompose.util.Constants.LIST_ARGUMENT_KEY
import com.devapp.to_docompose.util.Constants.LIST_SCREEN

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen:(taskId:Int)->Unit,
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit
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
            onSearchClicked = onSearchClicked,
            onSortClicked = onSortClicked
        )
    }
}