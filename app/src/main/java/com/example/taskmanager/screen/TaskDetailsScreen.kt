package com.example.taskmanager.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskmanager.R
import com.example.taskmanager.Task
import com.example.taskmanager.Tasks
import com.example.taskmanager.Urgency

@Composable
fun TaskDetailsScreen(navController: NavController, task: Task) {
    val urgencyColor = when (task.urgency) {
        Urgency.LOW -> colorResource(id = R.color.urgency_low)
        Urgency.MEDIUM -> colorResource(id = R.color.urgency_medium)
        Urgency.HIGH -> colorResource(id = R.color.urgency_high)
    }

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = task.title) },
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
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                    }
                    IconButton(onClick = {
                        Tasks.tasks.remove(task)
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                    }
                }
            )
        }
    ) {
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            Row {
                Text(
                    text = "Urgency:",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(all = 16.dp)
                )
                Text(
                    text = task.urgency.toString(),
                    fontSize = 24.sp,
                    fontStyle = FontStyle.Italic,
                    color = urgencyColor,
                    modifier = Modifier.padding(top = 16.dp, end = 16.dp, bottom = 16.dp)
                )
            }
            Row {
                Text(
                    text = "Deadline:",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(all = 16.dp)
                )
                Text(
                    text = task.deadline.toString(),
                    fontSize = 24.sp,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(top = 16.dp, end = 16.dp, bottom = 16.dp)
                )
            }
            Text(
                text = "Description:",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(all = 16.dp)
            )
            Text(
                text = task.description,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 32.dp, end = 16.dp, bottom = 16.dp)
            )
            Text(
                text = "To Do:",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(all = 16.dp)
            )
            task.subtasks.forEach {
                Row(modifier = Modifier.padding(start = 8.dp, bottom = 8.dp, end = 8.dp)) {
                    Checkbox(
                        checked = it.second,
                        onCheckedChange = {},
                    )
                    Text(
                        text = it.first,
                        fontSize = 18.sp,
                        style = if (it.second)
                            TextStyle(textDecoration = TextDecoration.LineThrough)
                        else
                            TextStyle.Default,
                        modifier = Modifier.padding(top = 11.dp)
                    )
                }
            }
        }
    }
}
