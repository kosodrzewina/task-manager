package com.example.taskmanager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun TaskList(tasks: List<Task>) {
    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = tasks) {
            TaskListItem(
                title = it.title,
                description = it.description,
                urgency = it.urgency,
                donePercentage = it.donePercentage
            )
        }

    }
}
