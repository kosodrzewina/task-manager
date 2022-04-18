package com.example.taskmanager

object Tasks {
    private val TASKS = mutableListOf<Task>()
    val tasks: MutableList<Task>
        get() = TASKS
}
