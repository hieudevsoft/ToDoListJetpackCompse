package com.devapp.to_docompose.ui.screens.list

import android.util.Log
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import com.devapp.to_docompose.ui.theme.fabBackgroundColor
import com.devapp.to_docompose.ui.viewmodels.SharedViewModel
import com.devapp.to_docompose.util.SearchAppBarState

@ExperimentalMaterialApi
@Composable
fun ListScreen(
    navigateToTaskScreen: (Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    val TAG = "ListScreen"
    LaunchedEffect(key1 = true ){
        sharedViewModel.getAllTask()
    }
    
    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState
    val allTask = sharedViewModel.allTask.collectAsState()

    Scaffold(
        topBar = {
            ListAppBar(
                sharedViewModel = sharedViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
        },
        content = {
            ListContent(listToDoTask = allTask.value,navigationToTask = navigateToTaskScreen)
        },
        floatingActionButton = {
            listFab(navigateToTaskScreen)
        }
    )
}

@Composable
fun listFab(onFabClick: (taskId: Int) -> Unit) {
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

