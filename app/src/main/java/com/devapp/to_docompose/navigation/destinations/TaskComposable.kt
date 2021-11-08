package com.devapp.to_docompose.navigation.destinations

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.devapp.to_docompose.ui.screens.task.TaskScreen
import com.devapp.to_docompose.ui.viewmodels.SharedViewModel
import com.devapp.to_docompose.util.Action
import com.devapp.to_docompose.util.Constants
import com.devapp.to_docompose.util.Constants.LIST_SCREEN
import com.devapp.to_docompose.util.Constants.TASK_ARGUMENT_KEY
import com.devapp.to_docompose.util.Constants.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(
            navArgument(TASK_ARGUMENT_KEY) {
                type = NavType.IntType
            }
        )
    ) {
        val taskID = it.arguments?.getInt(Constants.TASK_ARGUMENT_KEY)
        if (taskID != null) {
            LaunchedEffect(key1 = taskID) {
                sharedViewModel.getSelectedTask(taskId = taskID)
            }
            val selectedTask by sharedViewModel.selectedTask.collectAsState()
            LaunchedEffect(key1 = selectedTask) {
                if (taskID == -1 || selectedTask != null)
                    sharedViewModel.updateTaskFields(selectedTask)
            }
            TaskScreen(
                todoTask = selectedTask,
                sharedViewModel = sharedViewModel,
                navigateToListScreen = navigateToListScreen
            )
        }
    }
}