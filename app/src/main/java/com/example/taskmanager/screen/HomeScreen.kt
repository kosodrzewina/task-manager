package com.example.taskmanager.screen

import TaskList
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.taskmanager.TopBar

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(topBar = { TopBar() }, floatingActionButton = {
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
