package com.example.taskmanager

object Tasks {
    val tasks = mutableListOf<Task>()

    init {
        tasks.addAll(
            listOf(
                Task(
                    title = "Task no. 1",
                    description = "This is the first task on the list",
                    urgency = Urgency.LOW,
                    subtasks = listOf(
                        Pair("subtask1", false),
                        Pair("subtask2", true),
                        Pair("subtask3", false)
                    )
                ),
                Task(
                    title = "An important task",
                    description = "This is my next task",
                    urgency = Urgency.HIGH,
                    subtasks = listOf(Pair("subtask1", false))
                ),
                Task(
                    title = "A regular task",
                    description = "This is a regular task",
                    urgency = Urgency.MEDIUM,
                    subtasks = listOf(Pair("subtask1", false))
                )
            )
        )
    }
}
