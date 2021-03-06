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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.taskmanager.*
import com.example.taskmanager.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CreateOrEditTaskScreen(navController: NavController, task: Task) {
    val scrollState = rememberScrollState()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val systemUiController = rememberSystemUiController()
    val keyboardController = LocalSoftwareKeyboardController.current
    val backgroundColor = colorResource(id = R.color.background_color)
    val cardColor = colorResource(id = R.color.card_color)

    var urgencyValue by remember {
        mutableStateOf(task.urgency)
    }
    var titleValue by remember {
        mutableStateOf(task.title)
    }
    var descriptionValue by remember {
        mutableStateOf(task.description)
    }
    val subtasks by remember {
        mutableStateOf(task.subtasks.toMutableStateList())
    }
    var deadlineValue by remember {
        mutableStateOf(task.deadline)
    }

    var subtaskValue by remember {
        mutableStateOf("")
    }
    val dialogState = rememberMaterialDialogState()

    SideEffect {
        systemUiController.setStatusBarColor(color = cardColor)
    }

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
        scaffoldState = scaffoldState,
        backgroundColor = backgroundColor,
        topBar = {
            TopAppBar(
                title = { Text(text = "Task Details") },
                backgroundColor = cardColor,
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
                        when {
                            deadlineValue < LocalDate.now() -> {
                                scope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = "Deadline cannot be in the past!"
                                    )
                                }
                            }
                            titleValue.isEmpty() -> {
                                scope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = "Title cannot be empty!"
                                    )
                                }
                            }
                            descriptionValue.isEmpty() -> {
                                scope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = "Description cannot be empty!"
                                    )
                                }
                            }
                            subtasks.size == 0 -> {
                                scope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = "You have to add at least one subtask!"
                                    )
                                }
                            }
                            else -> {
                                if (Tasks.tasks.any { it.id == task.id }) {
                                    Tasks.tasks.first { it.id == task.id }.apply {
                                        title = titleValue
                                        description = descriptionValue
                                        urgency = urgencyValue
                                        deadline = deadlineValue
                                        this.subtasks = subtasks
                                    }
                                    navController.popBackStack()
                                } else {
                                    task.apply {
                                        title = titleValue
                                        description = descriptionValue
                                        urgency = urgencyValue
                                        deadline = deadlineValue
                                        this.subtasks = subtasks

                                        Tasks.tasks.add(this)
                                    }
                                }

                                keyboardController?.hide()
                                navController.popBackStack()
                            }
                        }
                    }) {
                        Text(text = stringResource(id = com.example.taskmanager.R.string.save))
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
                    value = UrgencyHelper.getName(urgencyValue),
                    modifier = Modifier.onGloballyPositioned {
                        urgencyTextFieldSize = it.size.toSize()
                    },
                    readOnly = true,
                    label = {
                        Text(text = stringResource(id = R.string.urgency))
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
                            Text(text = UrgencyHelper.getName(it))
                        }
                    }
                }
            }
            Row(modifier = Modifier.padding(top = 16.dp)) {
                Text(
                    text = "${stringResource(id = R.string.deadline)}:",
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
                        text = stringResource(id = R.string.title)
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
                        text = stringResource(id = R.string.description)
                    )
                },
                onValueChange = {
                    descriptionValue = it
                }
            )
            Text(
                text = stringResource(id = R.string.subtasks),
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TextField(
                            value = it.name,
                            onValueChange = {},
                            enabled = false,
                            modifier = Modifier.weight(1f)
                        )
                        IconButton(
                            onClick = { subtasks.remove(it) },
                            modifier = Modifier.padding(start = 8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = null,
                                tint = Color.Red
                            )
                        }
                    }
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
                                subtasks.add(Subtask(subtaskValue.trim(), false))
                                subtaskValue = ""
                                true
                            }

                            false
                        }
                )
                TextButton(
                    onClick = {
                        if (subtaskValue.trim().isNotEmpty()) {
                            subtasks.add(Subtask(subtaskValue.trim(), false))
                            subtaskValue = ""
                        }

                        scope.launch {
                            scrollState.animateScrollTo(value = Int.MAX_VALUE)
                        }
                    },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text(text = stringResource(id = R.string.add))
                }
            }
        }
    }
}

@Preview
@Composable
fun CreateOrEditTaskScreenPreview() {
    CreateOrEditTaskScreen(navController = rememberNavController(), task = Tasks.tasks[0])
}
