package com.devapp.to_docompose.ui.screens.task

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.devapp.to_docompose.data.models.ToDoTask
import com.devapp.to_docompose.util.Action

@Composable
fun TaskScreen(
    todoTask:ToDoTask?,
    navigateToListScreen: (Action) -> Unit
){
    Scaffold(
        topBar = { TaskAppBar(
            todoTask = todoTask,
            navigateToListScreen = navigateToListScreen
        ) },
        content = {}
    )
}

