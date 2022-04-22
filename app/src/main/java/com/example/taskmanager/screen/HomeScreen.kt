package com.example.taskmanager.screen

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.taskmanager.*
import com.example.taskmanager.navigation.Screen
import com.google.gson.GsonBuilder
import java.time.LocalDate

@Composable
fun HomeScreen(navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    val gson =
        GsonBuilder().registerTypeAdapter(LocalDate::class.java, LocalDateTypeAdapter())
            .create()

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Task Manager") }) },
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(
                        route = Screen.CreateOrEditTask.routeWithArgs(
                            gson.toJson(
                                Task(
                                    title = "",
                                    description = "",
                                    urgency = Urgency.LOW,
                                    deadline = LocalDate.now(),
                                    subtasks = listOf()
                                )
                            )
                        )
                    )
                },
                content = {
                    Icon(Icons.Default.Add, null)
                }
            )
        }) {
        if (Tasks.tasks.isEmpty()) {
            EmptyView()
        } else {
            TaskList(
                navController = navController,
                scaffoldState = scaffoldState,
                tasks = Tasks.tasks.sortedBy { it.deadline })
        }
    }
}
