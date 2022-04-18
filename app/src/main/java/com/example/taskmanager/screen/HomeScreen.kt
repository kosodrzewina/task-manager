package com.example.taskmanager.screen

import TaskList
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Task Manager") }) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(route = Screen.CreateTask.route)
                },
                content = {
                    Icon(Icons.Default.Add, null)
                }
            )
        }) {
        TaskList()
    }
}
