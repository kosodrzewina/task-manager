package com.example.taskmanager.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskmanager.*
import com.example.taskmanager.R
import com.example.taskmanager.composable.EmptyView
import com.example.taskmanager.composable.TaskList
import com.example.taskmanager.composable.ToDoBadge
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
        topBar = {
            TopAppBar(
                title = { Text(text = "Task Manager") },
                actions = {
                    ToDoBadge(
                        number = Tasks.getTasksUntilEndOfWeek(Urgency.LOW).size,
                        color = colorResource(id = R.color.urgency_low),
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                    )
                    ToDoBadge(
                        number = Tasks.getTasksUntilEndOfWeek(Urgency.MEDIUM).size,
                        color = colorResource(id = R.color.urgency_medium),
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                    )
                    ToDoBadge(
                        number = Tasks.getTasksUntilEndOfWeek(Urgency.HIGH).size,
                        color = colorResource(id = R.color.urgency_high),
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                    )
                }
            )
        },
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
        }
    ) {
        if (Tasks.tasks.none { it.deadline >= LocalDate.now() }) {
            EmptyView()
        } else {
            TaskList(
                navController = navController,
                scaffoldState = scaffoldState,
                tasks = Tasks.tasks.filter {
                    it.deadline >= LocalDate.now()
                }.sortedBy {
                    it.deadline
                }
            )
        }
    }
}
