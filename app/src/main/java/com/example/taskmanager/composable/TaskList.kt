package com.example.taskmanager.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskmanager.LocalDateTypeAdapter
import com.example.taskmanager.Task
import com.example.taskmanager.Tasks
import com.example.taskmanager.navigation.Screen
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskList(navController: NavController, scaffoldState: ScaffoldState, tasks: List<Task>) {
    val gson =
        GsonBuilder().registerTypeAdapter(LocalDate::class.java, LocalDateTypeAdapter()).create()
    val scope = rememberCoroutineScope()
    var isDialogOpen by remember {
        mutableStateOf(false)
    }
    var longPressedTask: Task? by remember {
        mutableStateOf(null)
    }

    if (isDialogOpen) {
        RemoveTaskAlertDialog(
            onDismissRequest = { isDialogOpen = false },
            onConfirmClick = {
                Tasks.tasks.remove(longPressedTask)
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "Task has been successfully removed"
                    )
                }
                isDialogOpen = false
            },
            onDismissClick = { isDialogOpen = false })
    }

    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = tasks) {
            TaskListItem(
                task = it,
                modifier = Modifier.combinedClickable(
                    onClick = {
                        navController.navigate(
                            Screen.TaskDetailsScreen.routeWithArgs(gson.toJson(it))
                        )
                    },
                    onLongClick = {
                        isDialogOpen = true
                        longPressedTask = it
                    }
                )
            )
        }
    }
}
