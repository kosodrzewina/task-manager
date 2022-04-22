package com.example.taskmanager.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.taskmanager.LocalDateTypeAdapter
import com.example.taskmanager.Task
import com.example.taskmanager.Urgency
import com.example.taskmanager.screen.CreateOrEditTaskScreen
import com.example.taskmanager.screen.HomeScreen
import com.example.taskmanager.screen.TaskDetailsScreen
import com.google.gson.GsonBuilder
import java.time.LocalDate

@Composable
fun NavGraph(navHostController: NavHostController) {
    val gson =
        GsonBuilder().registerTypeAdapter(LocalDate::class.java, LocalDateTypeAdapter())
            .create()

    NavHost(navController = navHostController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navHostController)
        }
        composable(
            route = Screen.CreateOrEditTask.route + "/{taskJson}",
            arguments = listOf(
                navArgument("taskJson") {
                    type = NavType.StringType
                    nullable = false
                    defaultValue = gson.toJson(
                        Task(
                            title = "",
                            description = "",
                            urgency = Urgency.LOW,
                            deadline = LocalDate.now(),
                            subtasks = listOf()
                        )
                    )
                },
            )
        ) { entry ->
            CreateOrEditTaskScreen(
                navController = navHostController,
                task = gson.fromJson(entry.arguments?.getString("taskJson"), Task::class.java)
            )
        }
        composable(
            route = Screen.TaskDetailsScreen.route + "/{taskJson}",
            arguments = listOf(
                navArgument("taskJson") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { entry ->
            TaskDetailsScreen(
                navController = navHostController,
                task = gson.fromJson(entry.arguments?.getString("taskJson"), Task::class.java)
            )
        }
    }
}
