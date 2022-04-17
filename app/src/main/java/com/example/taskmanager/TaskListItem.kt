package com.example.taskmanager

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TaskListItem(title: String, description: String, urgency: Urgency, donePercentage: Float) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.clickable(
            onClick = {}
        ),
        elevation = 8.dp
    ) {
        Row {
            Box(
                modifier = Modifier
                    .width(16.dp)
                    .height(100.dp)
                    .background(
                        when (urgency) {
                            Urgency.LOW -> Color.Blue
                            Urgency.MEDIUM -> Color.Yellow
                            Urgency.HIGH -> Color.Red
                        }
                    )
            ) {}
            Column {
                Text(
                    text = title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.padding(all = 16.dp)
                )
                Text(
                    text = description,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Thin,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.padding(start = 16.dp, bottom = 16.dp, end = 16.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize(),
//                    .background(Color.Yellow),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
//                Text(text = "text")
                PieChart(
                    donePercentage = donePercentage, 200f, modifier = Modifier
                        .fillMaxSize()
//                        .background(Color.Yellow)
                        .padding(16.dp)
//                        .align(alignment = Alignment.End)
                )
            }
        }
    }
}
