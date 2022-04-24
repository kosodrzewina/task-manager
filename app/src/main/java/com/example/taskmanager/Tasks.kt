package com.example.taskmanager

import androidx.compose.runtime.mutableStateListOf
import java.time.LocalDate

object Tasks {
    val tasks = mutableStateListOf<Task>()

    init {
        tasks.addAll(
            listOf(
                Task(
                    title = "Task no. 1",
                    description = "This is the first task on the list",
                    urgency = Urgency.LOW,
                    deadline = LocalDate.of(2022, 5, 5),
                    subtasks = listOf(
                        Subtask("Subtask1", false),
                        Subtask("subtask2", true),
                        Subtask("subtask3", false)
                    )
                ),
                Task(
                    title = "An important task",
                    description = "This is my next task",
                    urgency = Urgency.HIGH,
                    deadline = LocalDate.of(2023, 5, 5),
                    subtasks = listOf(Subtask("subtask1", false))
                ),
                Task(
                    title = "A regular task",
                    description = "This is a regular task",
                    urgency = Urgency.MEDIUM,
                    deadline = LocalDate.of(2022, 7, 5),
                    subtasks = listOf(Subtask("subtask1", false))
                ),
                Task(
                    title = "A regular task",
                    description = "This is a regular task",
                    urgency = Urgency.MEDIUM,
                    deadline = LocalDate.of(2021, 7, 5),
                    subtasks = listOf(Subtask("subtask1", false))
                )
            )
        )
    }
}
