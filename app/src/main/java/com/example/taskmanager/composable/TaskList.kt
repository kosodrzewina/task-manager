package com.example.taskmanager.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskmanager.LocalDateTypeAdapter
import com.example.taskmanager.R
import com.example.taskmanager.Task
import com.example.taskmanager.Tasks
import com.example.taskmanager.navigation.Screen
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskList(
    navController: NavController,
    scaffoldState: ScaffoldState,
    tasksToDo: List<Task>,
    tasksDone: List<Task>
) {
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
        if (tasksToDo.isNotEmpty()) {
            item {
                Text(
                    text = "${stringResource(id = R.string.to_do)}:",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(all = 16.dp)
                )
            }
            items(items = tasksToDo) {
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

        if (tasksDone.isNotEmpty()) {
            item {
                Text(
                    text = "${stringResource(id = R.string.done)}:",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(all = 16.dp)
                )
            }
            items(items = tasksDone) {
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

        item {
            Spacer(modifier = Modifier.height(70.dp))
        }
    }
}
