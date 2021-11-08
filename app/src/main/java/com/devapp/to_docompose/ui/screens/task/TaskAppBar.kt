package com.devapp.to_docompose.ui.screens.task

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.style.TextOverflow
import com.devapp.to_docompose.components.DisplayAlertDialog
import com.devapp.to_docompose.data.models.Priority
import com.devapp.to_docompose.data.models.ToDoTask
import com.devapp.to_docompose.ui.theme.topBarBackgroundColor
import com.devapp.to_docompose.ui.theme.topBarContentColor
import com.devapp.to_docompose.util.Action

@Composable
fun TaskAppBar(todoTask: ToDoTask?, navigateToListScreen: (Action) -> Unit) {
    if (todoTask == null)
        NewTaskAppBar(navigateToListScreen = navigateToListScreen)
    else {
        ExistingTaskAppBar(
            selectedTask = todoTask,
            navigateToListScreen = navigateToListScreen
        )
    }
}

@Composable
fun NewTaskAppBar(navigateToListScreen: (Action) -> Unit) {
    TopAppBar(
        navigationIcon = {
            BackAction(onBackClicked = navigateToListScreen)
        },
        title = {
            Text(
                text = "Add Task",
                color = MaterialTheme.colors.topBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topBarBackgroundColor,
        actions = {
            InsertAction(onAddClicked = navigateToListScreen)
        }
    )
}

@Composable
fun BackAction(onBackClicked: (Action) -> Unit) {
    IconButton(
        onClick = {
            onBackClicked(Action.NO_ACTION)
        },
    ) {
        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = "Back Arrow",
            tint = MaterialTheme.colors.topBarContentColor
        )
    }
}

@Composable
fun InsertAction(onAddClicked: (Action) -> Unit) {
    IconButton(
        onClick = {
            onAddClicked(Action.INSERT)
        },
    ) {
        Icon(
            imageVector = Icons.Rounded.Check,
            contentDescription = "Add Task",
            tint = MaterialTheme.colors.topBarContentColor
        )
    }
}

@Composable
fun ExistingTaskAppBar(
    selectedTask: ToDoTask,
    navigateToListScreen: (Action) -> Unit
) {
    TopAppBar(
        navigationIcon = {
            CloseAction(onCloseClicked = navigateToListScreen)
        },
        title = {
            Text(
                text = selectedTask.title,
                color = MaterialTheme.colors.topBarContentColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        backgroundColor = MaterialTheme.colors.topBarBackgroundColor,
        actions = {
            DeleteAction(title=selectedTask.title,onDeleteClicked = navigateToListScreen)
            UpdateAction(onUpdateClicked = navigateToListScreen)
        }
    )
}

@Composable
fun CloseAction(onCloseClicked: (Action) -> Unit) {
    IconButton(
        onClick = {
            onCloseClicked(Action.NO_ACTION)
        },
    ) {
        Icon(
            imageVector = Icons.Rounded.Close,
            contentDescription = "Close",
            tint = MaterialTheme.colors.topBarContentColor
        )
    }
}

@Composable
fun DeleteAction(
    title:String,
    onDeleteClicked: (Action) -> Unit) {
    var openDialog by remember {
        mutableStateOf(false)
    }
    DisplayAlertDialog(
        title =title,
        openDialog = openDialog,
        onYesClicked = {
            onDeleteClicked(Action.DELETE)
            openDialog = false
        }
    ) {
        openDialog = false
    }
    IconButton(
        onClick = {
            openDialog = true
        },
    ) {
        Icon(
            imageVector = Icons.Rounded.Delete,
            contentDescription = "Delete Task",
            tint = MaterialTheme.colors.topBarContentColor
        )
    }
}

@Composable
fun UpdateAction(onUpdateClicked: (Action) -> Unit) {
    IconButton(
        onClick = {
            onUpdateClicked(Action.UPDATE)
        },
    ) {
        Icon(
            imageVector = Icons.Rounded.Check,
            contentDescription = "Update Task",
            tint = MaterialTheme.colors.topBarContentColor
        )
    }
}