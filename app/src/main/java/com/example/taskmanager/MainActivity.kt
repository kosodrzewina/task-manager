package com.example.taskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.taskmanager.screen.NavGraph
import com.example.taskmanager.ui.theme.TaskManagerTheme

class MainActivity : ComponentActivity() {
    private lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Tasks.tasks.addAll(
            listOf(
                Task(
                    title = "Task no. 1",
                    description = "This is the first task on the list",
                    urgency = Urgency.LOW,
                    donePercentage = 0.7f
                ),
                Task(
                    title = "An important task",
                    description = "This is my next task",
                    urgency = Urgency.HIGH,
                    donePercentage = 0.3f
                ),
                Task(
                    title = "A regular task",
                    description = "This is a regular task",
                    urgency = Urgency.MEDIUM,
                    donePercentage = 0.2f
                )
            )
        )
        setContent {
            TaskManagerTheme {
                navHostController = rememberNavController()

                NavGraph(navHostController = navHostController)
            }
        }
    }
}
