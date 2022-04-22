package com.example.taskmanager.navigation

sealed class Screen(val route: String) {
    object Home : Screen(route = "home_screen")
    object CreateOrEditTask : Screen(route = "create_or_edit_task_screen")
    object TaskDetailScreen : Screen(route = "task_detail_screen")

    fun routeWithArgs(vararg args: String): String = buildString {
        append(route)
        args.forEach { append("/$it") }
    }
}
