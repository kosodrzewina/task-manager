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
                    donePercentage = 0.7f,
                    subtasks = listOf()
                ),
                Task(
                    title = "An important task",
                    description = "This is my next task",
                    urgency = Urgency.HIGH,
                    donePercentage = 0.3f,
                    subtasks = listOf()
                ),
                Task(
                    title = "A regular task",
                    description = "This is a regular task",
                    urgency = Urgency.MEDIUM,
                    donePercentage = 0.2f,
                    subtasks = listOf()
                )
            )
        )
    }
}
