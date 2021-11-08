package com.devapp.to_docompose.ui.screens.task

import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.devapp.to_docompose.data.models.Priority
import com.devapp.to_docompose.data.models.ToDoTask
import com.devapp.to_docompose.ui.viewmodels.SharedViewModel
import com.devapp.to_docompose.util.Action

@Composable
fun TaskScreen(
    todoTask: ToDoTask?,
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit
) {
    val title:String by sharedViewModel.title
    val content:String by sharedViewModel.content
    val priority:Priority by sharedViewModel.priority
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TaskAppBar(
                todoTask = todoTask,
                navigateToListScreen = { action ->
                    if(action==Action.NO_ACTION){
                        navigateToListScreen(action)
                    }else if(sharedViewModel.validateFields()){
                        navigateToListScreen(action)
                    } else{
                        Toast.makeText(context, "Error...", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        },
        content = {
                TaskContent(
                    title = title,
                    onTitleChange = {
                        sharedViewModel.updateTitle(it)
                    },
                    content = content,
                    onContentChange = {
                        sharedViewModel.content.value = it
                    },
                    priority = priority,
                    onPriorityChange = {
                        sharedViewModel.priority.value = it
                    }
                )
        }
    )
}

