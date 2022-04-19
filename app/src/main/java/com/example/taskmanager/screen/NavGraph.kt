package com.example.taskmanager.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.taskmanager.Task
import com.google.gson.Gson

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navHostController)
        }
        composable(route = Screen.CreateTask.route) {
            CreateTaskScreen(navController = navHostController)
        }
        composable(
            route = Screen.TaskDetailScreen.route + "/{taskJson}",
            arguments = listOf(navArgument("taskJson") {
                type = NavType.StringType
                nullable = false
            })
        ) { entry ->
            TaskDetailsScreen(
                navController = navHostController,
                task = Gson().fromJson(entry.arguments?.getString("taskJson"), Task::class.java)
            )
        }
    }
}
