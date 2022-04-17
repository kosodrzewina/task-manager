package com.example.taskmanager

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TaskListItem(title: String, description: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.clickable(
            onClick = {}
        ),
        elevation = 8.dp
    ) {
        Row {
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
            PieChart(donePercentage = 0.7f)
        }
    }
}