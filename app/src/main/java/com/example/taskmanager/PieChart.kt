package com.example.taskmanager

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PieChart(donePercentage: Float) {
    val sweepAngle = 360 * donePercentage;

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        drawArc(
            color = Color.Red,
            startAngle = sweepAngle,
            sweepAngle = 360f,
            useCenter = true,
            size = Size(width = size.width, height = size.width)
        )
        drawArc(
            color = Color.Green,
            startAngle = 0f,
            sweepAngle = sweepAngle,
            useCenter = true,
            size = Size(width = size.width, height = size.width)
        )
    }
}

@Preview
@Composable
fun PieChartPreview() {
    PieChart(0.6f)
}