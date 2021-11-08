package com.devapp.to_docompose.ui.screens.list

import android.util.Log
import android.widget.Space
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devapp.to_docompose.data.models.Priority
import com.devapp.to_docompose.data.models.ToDoTask
import com.devapp.to_docompose.ui.theme.*
import com.devapp.to_docompose.util.Action
import com.devapp.to_docompose.util.RequestState
import com.devapp.to_docompose.util.SearchAppBarState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.log

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun ListContent(
    listToDoTask: RequestState<List<ToDoTask>>,
    searchedTasks: RequestState<List<ToDoTask>>,
    navigationToTask: (taskId: Int) -> Unit,
    onSwipeToDelete: (Action, ToDoTask) -> Unit,
    searchAppBarState: SearchAppBarState,
    lowPriorityTasks: List<ToDoTask>,
    highPriorityTasks: List<ToDoTask>,
    sortState: RequestState<Priority>,
) {
    Log.d("TAG", "AppBarState: $searchAppBarState")
    if (sortState is RequestState.Success) {
        if (searchAppBarState == SearchAppBarState.TRIGGERED) {
            if (searchedTasks is RequestState.Success) {
                HandleListContent(
                    listToDoTask = searchedTasks.data,
                    navigationToTask = navigationToTask,
                    onSwipeToDelete = onSwipeToDelete
                )
            }
        } else if (sortState.data == Priority.NONE) {
            if (listToDoTask is RequestState.Success) {
                HandleListContent(
                    listToDoTask = listToDoTask.data,
                    navigationToTask = navigationToTask,
                    onSwipeToDelete = onSwipeToDelete
                )
            }
        } else if (sortState.data == Priority.LOW) {
            HandleListContent(
                listToDoTask = lowPriorityTasks,
                navigationToTask = navigationToTask,
                onSwipeToDelete = onSwipeToDelete
            )
        } else {
            HandleListContent(
                listToDoTask = highPriorityTasks,
                navigationToTask = navigationToTask,
                onSwipeToDelete = onSwipeToDelete
            )
        }
    }

}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun HandleListContent(
    listToDoTask: List<ToDoTask>,
    onSwipeToDelete: (Action, ToDoTask) -> Unit,
    navigationToTask: (taskId: Int) -> Unit
) {
    if (listToDoTask.isEmpty()) EmptyTasks()
    else
        DisplayTasks(
            listToDoTask = listToDoTask,
            navigationToTask = navigationToTask,
            onSwipeToDelete = onSwipeToDelete
        )
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun DisplayTasks(
    listToDoTask: List<ToDoTask>,
    onSwipeToDelete: (Action, ToDoTask) -> Unit,
    navigationToTask: (taskId: Int) -> Unit
) {
    LazyColumn(modifier = Modifier.padding(MEDIUM_SPACE)) {
        items(
            items = listToDoTask,
            key = {
                it.id
            }
        ) { task ->

            val dismissState = rememberDismissState()
            val dismissDirection = dismissState.dismissDirection
            val isDismissed = dismissState.isDismissed(DismissDirection.EndToStart)

            if (isDismissed && dismissDirection == DismissDirection.EndToStart) {
                rememberCoroutineScope().launch {
                    delay(500)
                    onSwipeToDelete(Action.DELETE, task)
                }
            }
            val degrees: Float by animateFloatAsState(
                targetValue =
                if (dismissState.targetValue == DismissValue.Default) 0f else -45f
            )

            var itemAppeared by remember {
                mutableStateOf(false)
            }

            LaunchedEffect(key1 = true){
                itemAppeared = true
            }

            AnimatedVisibility(
                visible = itemAppeared && !isDismissed,
                enter = expandVertically(
                    animationSpec = tween(300)
                ),
                exit = shrinkVertically(
                    animationSpec = tween(300)
                )
            ) {
                SwipeToDismiss(
                    directions = setOf(DismissDirection.EndToStart),
                    dismissThresholds = {
                        FractionalThreshold(fraction = 0.2f)
                    },
                    state = dismissState,
                    background = {
                        RedBackground(degrees = degrees)
                    },
                ) {
                    TaskItem(
                        todoTask = task,
                        navigateToTaskScreen = navigationToTask
                    )
                }
            }
            Spacer(modifier = Modifier.height(MEDIUM_SPACE))
        }
    }
}

@Composable
fun RedBackground(degrees: Float) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        shape = Shapes.medium
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(HighPriorityColor)
                .padding(LARGEST_PADDING),
            contentAlignment = Alignment.CenterEnd,
        ) {
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = "Delete",
                tint = Color.White,
                modifier = Modifier.rotate(degrees)
            )
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
