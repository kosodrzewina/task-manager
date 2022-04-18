package com.example.taskmanager

import TaskList
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import com.example.taskmanager.ui.theme.TaskManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskManagerTheme {
                Scaffold(topBar = { TopBar() }, floatingActionButton = {
                    FloatingActionButton(
                        onClick = {}, content = {
                            Icon(Icons.Default.Add, null)
                        }
                    )
                }) {
                    TaskList()
                }
            }
        }
    }
}
