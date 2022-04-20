package com.example.taskmanager.screen

import android.view.KeyEvent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.example.taskmanager.Task
import com.example.taskmanager.Tasks
import com.example.taskmanager.Urgency
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate

@Composable
fun CreateTaskScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    var urgencyValue by remember {
        mutableStateOf(Urgency.LOW)
    }
    var titleValue by remember {
        mutableStateOf("")
    }
    var descriptionValue by remember {
        mutableStateOf("")
    }
    val subtasks by remember {
        mutableStateOf(mutableListOf<String>())
    }
    var deadlineValue by remember {
        mutableStateOf(LocalDate.now())
    }

    var subtaskValue by remember {
        mutableStateOf("")
    }
    val dialogState = rememberMaterialDialogState()

    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("OK")
            negativeButton("CANCEL")
        }
    ) {
        datepicker {
            deadlineValue = it
        }
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
                                deadline = deadlineValue,
                                subtasks = subtasks.map { Pair(it, false) }
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
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
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
            Row(modifier = Modifier.padding(top = 16.dp)) {
                Text(
                    text = "Deadline:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(end = 16.dp)
                )
                Text(
                    text = deadlineValue.toString(),
                    fontSize = 24.sp,
                    modifier = Modifier.clickable {
                        dialogState.show()
                    }
                )
            }
            OutlinedTextField(
                value = titleValue,
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
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
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                subtasks.forEach {
                    TextField(
                        value = it,
                        onValueChange = {},
                        enabled = false,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                TextField(
                    value = subtaskValue,
                    onValueChange = { subtaskValue = it },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .onKeyEvent {
                            if (it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
                                subtasks.add(subtaskValue.trim())
                                subtaskValue = ""
                                true
                            }

                            false
                        }
                )
                TextButton(
                    onClick = {
                        subtasks.add(subtaskValue.trim())
                        subtaskValue = ""
                        println(subtasks)
                    },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text(text = "ADD")
                }
            }
        }
    }
}
