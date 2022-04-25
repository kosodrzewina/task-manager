package com.example.taskmanager

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

object Tasks {
    val tasks = mutableStateListOf<Task>()
    val mediumTasksUntilEndOfWeekCount = mutableStateOf(
        tasks.filter {
            it.urgency == Urgency.MEDIUM
        }.count {
            it.deadline <= LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
        }
    )

    init {
        tasks.addAll(
            listOf(
                Task(
                    title = "Task no. 1",
                    description = "This is the first task on the list",
                    urgency = Urgency.LOW,
                    deadline = LocalDate.of(2022, 4, 30),
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
                    deadline = LocalDate.of(2022, 4, 30),
                    subtasks = listOf(Subtask("subtask1", false))
                ),
                Task(
                    title = "A regular task",
                    description = "This is a regular task",
                    urgency = Urgency.MEDIUM,
                    deadline = LocalDate.of(2022, 4, 30),
                    subtasks = listOf(Subtask("subtask1", true))
                ),
                Task(
                    title = "A regular task",
                    description = "This is a regular task",
                    urgency = Urgency.MEDIUM,
                    deadline = LocalDate.of(2021, 7, 5),
                    subtasks = listOf(Subtask("subtask1", false))
                ),
                Task(
                    title = "This is a veeeeeery loooooong title",
                    description = "This is an even loooooooooonger description to the task that has a veeeeeeeeeeeeeeeery long title. It is really long!",
                    urgency = Urgency.MEDIUM,
                    deadline = LocalDate.of(2023, 7, 5),
                    subtasks = listOf(Subtask("subtask1", false))
                )
            )
        )
    }

    fun getTasksUntilEndOfWeek(urgency: Urgency): List<Task> {
        return tasks.filter {
            it.urgency == urgency
        }.filter {
            it.deadline >= LocalDate.now() && it.deadline <= LocalDate.now()
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
        }
    }
}
