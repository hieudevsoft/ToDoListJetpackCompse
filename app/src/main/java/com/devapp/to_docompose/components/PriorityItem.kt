package com.devapp.to_docompose.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devapp.to_docompose.data.models.Priority
import com.devapp.to_docompose.ui.theme.LARGE_SPACE
import com.devapp.to_docompose.ui.theme.PRIORITY_INDICATOR_SIZE
import com.devapp.to_docompose.ui.theme.Typography

@Composable
fun PriorityItem(priority: Priority) {
    Row(verticalAlignment = Alignment.CenterVertically)
    {
        Canvas(modifier = Modifier.size(PRIORITY_INDICATOR_SIZE)) {
            drawCircle(color = priority.color)
        }
        Spacer(modifier = Modifier.size(LARGE_SPACE))
        Text(text = priority.name, style = Typography.subtitle2,color = MaterialTheme.colors.onSurface)
    }
}

@Composable
@Preview
fun PriorityItemPreview(){
    PriorityItem(priority = Priority.HIGH)
}