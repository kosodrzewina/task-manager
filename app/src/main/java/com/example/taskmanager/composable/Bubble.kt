package com.example.taskmanager.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Bubble(
    text: String,
    textColor: Color,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(30.dp))
                .background(color = backgroundColor)
        ) {
            Text(
                text = text,
                fontSize = 12.sp,
                modifier = Modifier.padding(
                    start = 6.dp,
                    end = 6.dp,
                    top = 2.dp,
                    bottom = 2.dp
                ),
                color = textColor
            )
        }
    }
}
