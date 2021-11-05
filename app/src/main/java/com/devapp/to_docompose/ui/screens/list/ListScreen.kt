package com.devapp.to_docompose.ui.screens.list

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.devapp.to_docompose.data.models.Priority
import com.devapp.to_docompose.ui.theme.fabBackgroundColor

@Composable
fun ListScreen(
    onSearchClicked:()->Unit,
    onSortClicked: (Priority) -> Unit,
    navigateToTaskScreen: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            ListAppBar(onSearchClicked,onSortClicked)
        },
        content = {},
        floatingActionButton = {
            listFab(navigateToTaskScreen)
        }
    )
}

@Composable
fun listFab(onFabClick: (taskId:Int) -> Unit) {
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
@Preview
private fun ListScreenPreview() {
    ListScreen(navigateToTaskScreen = {},onSearchClicked = {},onSortClicked = {})
}
