package com.devapp.to_docompose.ui.screens.list

import android.widget.Space
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.devapp.to_docompose.data.models.Priority
import com.devapp.to_docompose.data.models.ToDoTask
import com.devapp.to_docompose.ui.theme.*

@ExperimentalMaterialApi
@Composable
fun ListContent(
    listToDoTask: List<ToDoTask>,
    navigationToTask: (taskId: Int) -> Unit
) {
    if (listToDoTask.isEmpty()) {
        EmptyTasks()
    } else {
        DisplayTasks(listToDoTask = listToDoTask, navigationToTask = navigationToTask)
    }
}

@ExperimentalMaterialApi
@Composable
fun DisplayTasks(
    listToDoTask: List<ToDoTask>,
    navigationToTask: (taskId: Int) -> Unit
) {
    LazyColumn(modifier = Modifier.padding(MEDIUM_SPACE)) {
        items(
            items = listToDoTask,
            key = {
                it.id
            }
        ) { task ->
            TaskItem(todoTask = task, navigateToTaskScreen = navigationToTask)
            Spacer(modifier = Modifier.height(MEDIUM_SPACE))
        }
    }
}


@ExperimentalMaterialApi
@Composable
fun TaskItem(
    todoTask: ToDoTask,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.todoItemBackgroundColor,
        shape = Shapes.medium,
        elevation = TASK_ITEM_ELEVATION,
        onClick = {
            navigateToTaskScreen(todoTask.id)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(LARGE_SPACE)
                .fillMaxWidth()
        ) {
            Row {
                Text(
                    modifier = Modifier.weight(9f),
                    text = todoTask.title,
                    color = MaterialTheme.colors.todoTextColor,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Canvas(modifier = Modifier.size(PRIORITY_INDICATOR_SIZE)) {
                        drawCircle(color = todoTask.priority.color)
                    }
                }
            }
            Spacer(modifier = Modifier.height(LARGE_SPACE))
            Text(
                text = todoTask.content,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colors.todoTextColor,
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.Light,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
