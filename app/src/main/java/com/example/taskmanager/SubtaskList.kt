package com.example.taskmanager

import android.view.KeyEvent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun SubtaskList(modifier: Modifier = Modifier) {
    val subtasks by remember {
        mutableStateOf(mutableListOf<String>())
    }
    var textValue by remember {
        mutableStateOf("")
    }

    Column(modifier = modifier) {
        subtasks.forEach {
            TextField(
                value = it,
                onValueChange = {},
                enabled = false,
                modifier = Modifier.fillMaxWidth()
            )
        }
        TextField(
            value = textValue,
            onValueChange = { textValue = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .onKeyEvent {
                    if (it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
                        subtasks.add(textValue.trim())
                        textValue = ""
                        true
                    }

                    false
                }
        )
        TextButton(
            onClick = {
                subtasks.add(textValue.trim())
                textValue = ""
                println(subtasks)
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "ADD")
        }
    }
}
