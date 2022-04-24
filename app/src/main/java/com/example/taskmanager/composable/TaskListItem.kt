package com.example.taskmanager.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.ColorUtils
import com.example.taskmanager.R
import com.example.taskmanager.Task
import com.example.taskmanager.Urgency

private val Float.percentage get() = (this * 100).toInt().toString() + "%"

@Composable
fun TaskListItem(task: Task, modifier: Modifier = Modifier) {
    val urgencyColor = when (task.urgency) {
        Urgency.LOW -> colorResource(id = R.color.urgency_low)
        Urgency.MEDIUM -> colorResource(id = R.color.urgency_medium)
        Urgency.HIGH -> colorResource(id = R.color.urgency_high)
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp
    ) {
        Box(modifier = modifier) {
            Row(modifier = Modifier.height(IntrinsicSize.Min)) {
                Box(
                    modifier = Modifier
                        .width(16.dp)
                        .fillMaxHeight()
                        .background(urgencyColor)
                )
                Column(modifier = Modifier.weight(1f)) {
                    Row {
                        Bubble(
                            text = task.deadline.toString(),
                            textColor = Color.Black,
                            backgroundColor = Color.LightGray,
                            modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 8.dp)
                        )
                        Bubble(
                            text = task.donePercentage.percentage,
                            textColor = Color.White,
                            backgroundColor = Color(
                                ColorUtils.blendARGB(
                                    urgencyColor.toArgb(),
                                    Color.Black.toArgb(),
                                    0.25f
                                )
                            ),
                            modifier = Modifier.padding(top = 16.dp)
                        )
                    }
                    Text(
                        text = task.title,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Left,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(
                                start = 16.dp,
                                top = 4.dp,
                                end = 16.dp,
                                bottom = 8.dp
                            )
                    )
                    Text(
                        text = task.description,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Thin,
                        textAlign = TextAlign.Left,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(start = 16.dp, bottom = 8.dp, end = 16.dp)
                    )
                }
                PieChart(
                    donePercentage = task.donePercentage,
                    modifier = Modifier
                        .padding(all = 16.dp)
                        .size(80.dp)
                )
            }
        }
    }
}
