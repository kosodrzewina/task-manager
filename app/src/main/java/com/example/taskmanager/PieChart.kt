package com.example.taskmanager

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

@Composable
fun PieChart(donePercentage: Float, modifier: Modifier = Modifier) {
    val sweepAngle = 360 * donePercentage
    val backgroundColor = colorResource(id = R.color.chart_background)
    val doneColor = colorResource(id = R.color.chart_done)

    Canvas(modifier = modifier
        .height(80.dp)
        .width(80.dp)) {
        drawArc(
            color = backgroundColor,
            startAngle = sweepAngle,
            sweepAngle = 360f,
            useCenter = true,
            size = Size(width = size.width, size.width)
        )
        drawArc(
            color = doneColor,
            startAngle = 0f,
            sweepAngle = sweepAngle,
            useCenter = true,
            size = Size(width = size.width, height = size.width)
        )
    }
}
