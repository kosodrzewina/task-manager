package com.example.taskmanager.screen

sealed class Screen(val route: String) {
    object Home: Screen(route = "home_screen")
    object CreateTask: Screen(route = "create_task_screen")
}
