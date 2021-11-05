package com.devapp.to_docompose.ui.screens.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.devapp.to_docompose.ui.theme.topBarBackgroundColor
import com.devapp.to_docompose.ui.theme.topBarContentColor
import com.devapp.to_docompose.R
import com.devapp.to_docompose.data.models.Priority
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import com.devapp.to_docompose.components.PriorityItem
import com.devapp.to_docompose.ui.theme.TOP_APP_BAR_HEIGHT

@Composable
fun ListAppBar(onSearchClicked: () -> Unit, onSortClicked: (Priority) -> Unit) {
    //DefaultListAppBar(onSearchClicked, onSortClicked)
    SearchAppBar("", {}, {}, {})
}

@Composable
fun DefaultListAppBar(onSearchClicked: () -> Unit, onSortClicked: (Priority) -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = "Tasks",
                color = MaterialTheme.colors.topBarContentColor
            )
        },
        actions = {
            ListAppBarAction(onSearchClicked, onSortClicked, {})
        },
        backgroundColor = MaterialTheme.colors.topBarBackgroundColor
    )
}


@Composable
fun ListAppBarAction(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteAllClicked: () -> Unit
) {
    SearchAction(onSearchClicked)
    SortAction(onSortClicked)
    DeleteAllAction(onDeleteAllClicked)
}

@Composable
fun SearchAction(onSearchClicked: () -> Unit) {
    IconButton(
        onClick = {
            onSearchClicked()
        },
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Search",
            tint = MaterialTheme.colors.topBarContentColor
        )
    }
}

@Composable
fun SortAction(onSortClicked: (Priority) -> Unit) {
    var expanded by remember {
        mutableStateOf(false)
    }
    IconButton(
        onClick = {
            expanded = true
        }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_filter_list),
            contentDescription = "Sort List",
            tint = MaterialTheme.colors.topBarContentColor
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropDownItemSort(
                priority = Priority.LOW,
                onSortClicked = onSortClicked
            ) {
                expanded = false
            }
            DropDownItemSort(
                priority = Priority.MEDIUM,
                onSortClicked = onSortClicked
            ) {
                expanded = false
            }
            DropDownItemSort(
                priority = Priority.HIGH,
                onSortClicked = onSortClicked
            ) {
                expanded = false
            }
            DropDownItemSort(
                priority = Priority.NONE,
                onSortClicked = onSortClicked
            ) {
                expanded = false
            }

        }
    }
}

@Composable
fun DeleteAllAction(onDeleteAllClicked: () -> Unit) {
    IconButton(
        onClick = {
            onDeleteAllClicked()
        }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_delete),
            contentDescription = "Sort List",
            tint = MaterialTheme.colors.topBarContentColor
        )
    }
}

@Composable
fun DropDownItemSort(
    priority: Priority,
    onSortClicked: (Priority) -> Unit,
    onExpandedCallBack: () -> Unit
) {
    DropdownMenuItem(onClick = {
        onExpandedCallBack()
        onSortClicked(priority)
    }) {
        PriorityItem(priority = priority)
    }
}

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {
    var textPresent by remember {
        mutableStateOf(text)
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_APP_BAR_HEIGHT),
        elevation = AppBarDefaults.BottomAppBarElevation,
        color = MaterialTheme.colors.topBarBackgroundColor
    ) {
        TextField(
            value = textPresent,
            onValueChange = {
                onTextChange(it)
                textPresent = it
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = stringResource(id = R.string.search_place_holder),
                    color = Color.White
                )
            },
            textStyle = TextStyle(
                color = MaterialTheme.colors.topBarContentColor,
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    onClick = {

                    },
                    modifier = Modifier.alpha(ContentAlpha.disabled)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = "Search Icon",
                        tint = MaterialTheme.colors.topBarContentColor
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        onCloseClicked()
                    },
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = "Close Icon",
                        tint = MaterialTheme.colors.topBarContentColor
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = MaterialTheme.colors.topBarContentColor,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent
            )

        )
    }
}

@Composable
@Preview
private fun DefaultListAppBarPreview() {
    DefaultListAppBar({}, {})
}

@Composable
@Preview
private fun SearchAppBarPreview() {
    SearchAppBar("", {}, {}, {})
}
