package com.devapp.to_docompose.ui.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.devapp.to_docompose.R
import com.devapp.to_docompose.ui.theme.SIZE_OF_SAD
import com.devapp.to_docompose.ui.theme.todoTextColor

@Composable
fun EmptyTasks(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(SIZE_OF_SAD),
            painter = painterResource(id = R.drawable.ic_sad),
            contentDescription ="Empty Tasks",
            tint = MaterialTheme.colors.todoTextColor
            )
        Text(
            text = stringResource(id = R.string.empty_content),
            color = MaterialTheme.colors.todoTextColor,
            fontWeight = FontWeight.Medium,
            fontSize = MaterialTheme.typography.h6.fontSize
        )
    }
}
@Composable
@Preview
fun previewEmptyContent(){
    EmptyTasks()
}