package com.example.taskmanager

import java.time.LocalDate
import java.util.*

data class Task(
    val title: String,
    val description: String,
    val urgency: Urgency,
    val deadline: LocalDate,
    val subtasks: List<Subtask>
) {
    val donePercentage: Float get() = subtasks.count { it.isDone }.toFloat() / subtasks.size
    val id: String

    init {
        var id = UUID.randomUUID().toString()

        while (Tasks.tasks.any { it.id == id }) {
            id = UUID.randomUUID().toString()
        }

        this.id = id
    }
}
