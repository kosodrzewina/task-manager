package com.example.taskmanager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Float.percentage get() = (this * 100).toInt().toString() + "%"

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskListItem(title: String, description: String, urgency: Urgency, donePercentage: Float) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp,
        onClick = {}
    ) {
        Row {
            Box(
                modifier = Modifier
                    .width(16.dp)
                    .height(112.dp)
                    .background(
                        when (urgency) {
                            Urgency.LOW -> colorResource(id = R.color.urgency_low)
                            Urgency.MEDIUM -> colorResource(id = R.color.urgency_medium)
                            Urgency.HIGH -> colorResource(id = R.color.urgency_high)
                        }
                    )
            ) {}
            Column(modifier = Modifier.weight(weight = 100f)) {
                Text(
                    text = title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    )
                )
                Text(
                    text = "${donePercentage.percentage} · $description",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Thin,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.padding(start = 16.dp, bottom = 8.dp, end = 16.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            PieChart(
                donePercentage = donePercentage, modifier = Modifier.padding(16.dp)
            )
        }
    }
}
