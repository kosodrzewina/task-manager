package com.example.taskmanager

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@Composable
fun RemoveTaskAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmClick: () -> Unit,
    onDismissClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(text = "Are you sure?", fontWeight = FontWeight.Bold) },
        text = {
            Text(text = "If you proceed, you will permanently delete this task.")
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
