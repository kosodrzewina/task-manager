package com.example.taskmanager

data class Task(
    val title: String,
    val description: String,
    val urgency: Urgency,
    val donePercentage: Float
)
