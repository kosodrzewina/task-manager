import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.taskmanager.TaskListItem
import com.example.taskmanager.Urgency

@Composable
fun TaskList() {
    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            TaskListItem(
                title = "Task no. 1",
                description = "This is the first task on the list",
                urgency = Urgency.LOW,
                donePercentage = 0.7f
            )
        }
        item {
            TaskListItem(
                title = "An important task",
                description = "This is my next task",
                urgency = Urgency.HIGH,
                donePercentage = 0.3f
            )
        }
        item {
            TaskListItem(
                title = "A regular task",
                description = "This is a regular task",
                urgency = Urgency.MEDIUM,
                donePercentage = 0.2f
            )
        }
    }
}
