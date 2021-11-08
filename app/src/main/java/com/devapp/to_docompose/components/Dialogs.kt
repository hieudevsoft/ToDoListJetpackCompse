package com.devapp.to_docompose.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.devapp.to_docompose.R

@Composable
fun DisplayAlertDialog(
    title: String,
    openDialog: Boolean,
    onYesClicked: () -> Unit,
    onCloseClicked: () -> Unit
) {
    if (openDialog) {
        AlertDialog(
            onDismissRequest = { onCloseClicked() },
            title = {
                Text(
                    text = stringResource(id = R.string.title_delete, title),
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.message_delete, title),
                    style = MaterialTheme.typography.subtitle2,
                    fontWeight = FontWeight.Light
                )
            },
            confirmButton = {
                Button(onClick = {
                    onCloseClicked()
                    onYesClicked()
                }
                ) {
                    Text(text = "OK")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { onCloseClicked() }) {
                    Text(text = "NO")
                }
            }
        )
    }
}