package com.devapp.to_docompose.ui.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.devapp.to_docompose.R
import com.devapp.to_docompose.components.PriorityDropDown
import com.devapp.to_docompose.data.models.Priority
import com.devapp.to_docompose.ui.theme.LARGE_SPACE
import com.devapp.to_docompose.ui.theme.MEDIUM_SPACE
import com.devapp.to_docompose.ui.theme.topBarBackgroundColor

@Composable
fun TaskContent(
    title: String?,
    onTitleChange: (String) -> Unit,
    content: String?,
    onContentChange: (String) -> Unit,
    priority: Priority?,
    onPriorityChange: (Priority) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(LARGE_SPACE),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = title ?: "",
            onValueChange = { onTitleChange(it) },
            label = {
                Text(text = stringResource(id = R.string.title))
            },
            singleLine = true,
            textStyle = MaterialTheme.typography.body1,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(MEDIUM_SPACE))
        PriorityDropDown(priority = priority ?: Priority.LOW, onPrioritySelected = onPriorityChange)
        OutlinedTextField(
            value = content ?: "",
            onValueChange = { onContentChange(it) },
            label = {
                Text(text = stringResource(id = R.string.content))
            },
            textStyle = MaterialTheme.typography.body2,
            modifier = Modifier.fillMaxSize(),
        )
    }
}