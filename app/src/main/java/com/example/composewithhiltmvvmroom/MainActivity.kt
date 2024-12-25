package com.example.composewithhiltmvvmroom

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.composewithhiltmvvmroom.ui.theme.ComposeWithHiltMvvmRoomTheme
import com.example.composewithhiltmvvmroom.views.AddItemDialog
import com.example.composewithhiltmvvmroom.views.BottomNavigationWithHover
import com.example.composewithhiltmvvmroom.views.ItemLayout
import com.example.mvvm_hilt_db_retrofit_room.models.TaskItem
import com.example.mvvm_hilt_db_retrofit_room.utils.BottomNavActions
import com.example.mvvm_hilt_db_retrofit_room.utils.toCamelCaseTitle
import com.example.mvvm_hilt_db_retrofit_room.viewmodels.MainViewModel
import com.example.mvvm_hilt_db_retrofit_room.viewmodels.TaskListType
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var mainViewModel: MainViewModel

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.loadDataFromDB(TaskListType.GetAllTasks)

        setContent {
            var topBarTitle by remember { mutableStateOf("All Tasks") }
            var isDialogOpen by remember { mutableStateOf(false) }
            val tasks by mainViewModel.taskLiveData.observeAsState(emptyList())

            ComposeWithHiltMvvmRoomTheme {

                // Your Top Bar
                TopAppBar( modifier = Modifier.fillMaxWidth()
//                    .align(Alignment.CenterVertically)
                    ,
                    title = { Text(topBarTitle) }
                )

                LazyColumn(modifier = Modifier.fillMaxSize()
                    .padding(top = 56.dp, bottom = 56.dp))
                {
                    items(tasks) { taskItem ->
                        ItemLayout(R.drawable.task_completed, taskItem.title, taskItem.description)
                    }
                }

                BottomNavigationWithHover(
                    onAddButtonClicked = { isDialogOpen = true /*onAddButtonClicked()*/ },
                    onBottomNavClicked = {actionName ->
                        handleAction(actionName)
                        topBarTitle = "$actionName TASKS".toCamelCaseTitle()
                    }
                )
            }

            if (isDialogOpen) {
                AddItemDialog(
                    onDismiss = {isDialogOpen = false},
                    onAddItem = {title, description -> insertItem(title, description)}
                )
            }

        }
    }

    private fun getCurrentDateTime(): String {
        return LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
    }

    private fun insertItem(title: String, description: String) {
        val task: TaskItem = TaskItem(0,title,description,"Urgent",
            "",false,getCurrentDateTime(),getCurrentDateTime())
//        mainViewModel.loadDataFromDB(TaskListType.InsertTasks(task))
    }

    private fun onAddButtonClicked() {
        Toast.makeText(this, "Add Action Clicked", Toast.LENGTH_SHORT).show()
    }

    // Function to handle actions based on the string
    private fun handleAction(actionName: String) {
        when (actionName) {
            BottomNavActions.ALL.name -> {
                mainViewModel.loadDataFromDB(TaskListType.GetAllTasks)
            }
            BottomNavActions.PENDING.name -> {
//                Toast.makeText(this, "Pending Action Clicked", Toast.LENGTH_SHORT).show()
                mainViewModel.loadDataFromDB(TaskListType.GetPendingTasks)
            }
            BottomNavActions.COMPLETED.name -> {
//                Toast.makeText(this, "Completed Action: $actionName", Toast.LENGTH_SHORT).show()
                mainViewModel.loadDataFromDB(TaskListType.GetCompletedTasks)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeWithHiltMvvmRoomTheme {
//        Greeting("Android")
    }
}