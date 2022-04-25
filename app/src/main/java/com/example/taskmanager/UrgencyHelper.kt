package com.example.taskmanager

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource

class UrgencyHelper {
    companion object {
        @Composable
        fun getColor(urgency: Urgency): Color {
            return when (urgency) {
                Urgency.LOW -> colorResource(id = R.color.urgency_low)
                Urgency.MEDIUM -> colorResource(id = R.color.urgency_medium)
                Urgency.HIGH -> colorResource(id = R.color.urgency_high)
            }
        }

        @Composable
        fun getName(urgency: Urgency): String {
            return when (urgency) {
                Urgency.LOW -> stringResource(id = R.string.urgency_low)
                Urgency.MEDIUM -> stringResource(id = R.string.urgency_medium)
                Urgency.HIGH -> stringResource(id = R.string.urgency_high)
            }
        }

    }
}
