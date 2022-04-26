package com.example.taskmanager.composable

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.taskmanager.R

@Composable
fun RemoveTaskAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmClick: () -> Unit,
    onDismissClick: () -> Unit
) {
    val backgroundColor = colorResource(id = R.color.background_color)

    AlertDialog(
        onDismissRequest = onDismissRequest,
        backgroundColor = backgroundColor,
        title = {
            Text(
                text = stringResource(id = R.string.remove_task_title),
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Text(text = stringResource(id = R.string.remove_task_content))
        },
        confirmButton = {
            TextButton(onClick = onConfirmClick) {
                Text(text = "OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissClick) {
                Text(text = "CANCEL")
            }
        },
    )
}
