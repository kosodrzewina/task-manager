package com.example.taskmanager

import java.time.LocalDate

data class Task(
    val title: String,
    val description: String,
    val urgency: Urgency,
    val deadline: LocalDate,
    val subtasks: List<Pair<String, Boolean>>
) {
    val donePercentage: Float get() = subtasks.count { it.second }.toFloat() / subtasks.size
}
