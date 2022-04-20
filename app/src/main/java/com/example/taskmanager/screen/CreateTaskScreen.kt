package com.example.taskmanager.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.example.taskmanager.SubtaskList
import com.example.taskmanager.Task
import com.example.taskmanager.Tasks
import com.example.taskmanager.Urgency

@Composable
fun CreateTaskScreen(navController: NavController) {
    var urgencyValue by remember {
        mutableStateOf(Urgency.LOW)
    }
    var titleValue by remember {
        mutableStateOf("")
    }
    var descriptionValue by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Create New Task") },
                backgroundColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    TextButton(onClick = {
                        Tasks.tasks.add(
                            Task(
                                title = titleValue,
                                description = descriptionValue,
                                urgency = urgencyValue,
                                donePercentage = 0f,
                                listOf()
                            )
                        )
                        navController.popBackStack()
                    }) {
                        Text(text = "SAVE")
                    }
                }
            )
        },
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
        ) {
            var expanded by remember {
                mutableStateOf(false)
            }
            var urgencyTextFieldSize by remember {
                mutableStateOf(Size.Zero)
            }
            val dropDownIcon =
                if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown

            Column(modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp)) {
                OutlinedTextField(
                    value = urgencyValue.toString(),
                    modifier = Modifier.onGloballyPositioned {
                        urgencyTextFieldSize = it.size.toSize()
                    },
                    readOnly = true,
                    label = {
                        Text(
                            text = "Urgency"
                        )
                    },
                    onValueChange = {
                        urgencyValue = when (it) {
                            Urgency.LOW.toString() -> Urgency.LOW
                            Urgency.MEDIUM.toString() -> Urgency.MEDIUM
                            Urgency.HIGH.toString() -> Urgency.HIGH
                            else -> throw NotImplementedError()
                        }
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = dropDownIcon,
                            contentDescription = null,
                            modifier = Modifier.clickable { expanded = !expanded })
                    }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.width(
                        with(LocalDensity.current) { urgencyTextFieldSize.width.toDp() })
                ) {
                    Urgency.values().forEach {
                        DropdownMenuItem(
                            onClick = {
                                urgencyValue = it
                                expanded = false
                            }
                        ) {
                            Text(text = it.toString())
                        }
                    }
                }
            }
            OutlinedTextField(
                value = titleValue,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp),
                label = {
                    Text(
                        text = "Title"
                    )
                },
                onValueChange = {
                    titleValue = it
                }
            )
            OutlinedTextField(
                value = descriptionValue,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp),
                label = {
                    Text(
                        text = "Description"
                    )
                },
                onValueChange = {
                    descriptionValue = it
                }
            )
            Text(
                text = "Subtasks",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(all = 16.dp)
            )
            SubtaskList(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
            )
        }
    }

}
