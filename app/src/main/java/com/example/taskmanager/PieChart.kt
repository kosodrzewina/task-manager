package com.example.taskmanager

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PieChart(donePercentage: Float, size: Float, modifier: Modifier = Modifier) {
    val sweepAngle = 360 * donePercentage;

    Canvas(modifier = modifier) {
        drawArc(
            color = Color.Red,
            startAngle = sweepAngle,
            sweepAngle = 360f,
            useCenter = true,
            size = Size(width = size, height = size),
        )
        drawArc(
            color = Color.Green,
            startAngle = 0f,
            sweepAngle = sweepAngle,
            useCenter = true,
            size = Size(width = size, height = size),
        )
    }
}

@Preview
@Composable
fun PieChartPreview() {
//    PieChart(0.6f)
}