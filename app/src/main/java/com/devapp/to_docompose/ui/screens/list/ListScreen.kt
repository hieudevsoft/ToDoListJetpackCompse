package com.devapp.to_docompose.ui.screens.list

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.devapp.to_docompose.ui.theme.fabBackgroundColor
import com.devapp.to_docompose.ui.viewmodels.SharedViewModel
import com.devapp.to_docompose.util.Action
import com.devapp.to_docompose.util.SearchAppBarState
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun ListScreen(
    navigateToTaskScreen: (Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    val TAG = "ListScreen"
    LaunchedEffect(key1 = true) {
        sharedViewModel.getAllTask()
        sharedViewModel.readSortState()
    }
    val action: Action by sharedViewModel.action
    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState

    val sortState by sharedViewModel.sortState.collectAsState()
    val lowPriorityTasks by sharedViewModel.lowPriorityTasks.collectAsState()
    val highPriorityTasks by sharedViewModel.highPriorityTasks.collectAsState()

    val allTask by sharedViewModel.allTask.collectAsState()
    val searchedTasks by sharedViewModel.searchedTask.collectAsState()
    val scaffoldState = rememberScaffoldState()
    DisplaySnackBar(
        scaffoldState = scaffoldState,
        taskTitle = sharedViewModel.title.value,
        handleDatabaseActions = {
            Log.d(TAG, "ListScreen: $action")
            sharedViewModel.handleDatabaseActions(action)
        },
        action = action,
        onUndoClicked = {
            sharedViewModel.handleDatabaseActions(action = Action.UNDO)
        }
    )

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ListAppBar(
                sharedViewModel = sharedViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
        },
        content = {
            ListContent(
                searchAppBarState = searchAppBarState,
                searchedTasks = searchedTasks,
                listToDoTask = allTask,
                navigationToTask = navigateToTaskScreen,
                lowPriorityTasks = lowPriorityTasks,
                highPriorityTasks = highPriorityTasks,
                sortState = sortState,
                onSwipeToDelete = {
                    action,task->
                    sharedViewModel.action.value = action
                    sharedViewModel.updateTaskFields(task = task)
                }
            )
        },
        floatingActionButton = {
            ListFab(onFabClick = {
                navigateToTaskScreen(it)
            })
        }
    )
}


@Composable
fun ListFab(onFabClick: (taskId: Int) -> Unit) {
    FloatingActionButton(
        onClick = {
            onFabClick(-1)
        },
        backgroundColor = MaterialTheme.colors.fabBackgroundColor
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add Button",
            tint = Color.White,
        )
    }
}

@Composable
fun DisplaySnackBar(
    scaffoldState: ScaffoldState,
    taskTitle: String,
    handleDatabaseActions:()->Unit,
    action: Action,
    onUndoClicked: (Action) -> Unit
) {
    handleDatabaseActions()
    val scopeCoroutine = rememberCoroutineScope()
    LaunchedEffect(key1 = action) {
        if (action != Action.NO_ACTION) {
            scopeCoroutine.launch {
                val snackbar = scaffoldState.snackbarHostState.showSnackbar(
                    message = setMessage(
                        action = action,
                        taskTitle = taskTitle
                    ),
                    actionLabel = setActionLabel (action = action),
                )
                undoDeletedTask(action = action, snackBarResult = snackbar) {
                    onUndoClicked(it)
                }
            }
        }
    }
}

private fun setActionLabel(action: Action): String {
    return if (action.name == "DELETE") {
        "UNDO"
    } else {
        "OK"
    }
}

private fun setMessage(action: Action, taskTitle: String): String {
    return if (action == Action.DELETE_ALL) {
        "All Tasks Removed"
    } else {
        "${action.name}: $taskTitle"
    }
}

private fun undoDeletedTask(
    action: Action,
    snackBarResult: SnackbarResult,
    onUndoClicked: (Action) -> Unit
) {
    if (snackBarResult == SnackbarResult.ActionPerformed &&
        action == Action.DELETE
    ) {
        onUndoClicked(action)
    }
}