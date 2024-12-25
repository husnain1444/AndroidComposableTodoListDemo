package com.example.mvvm_hilt_db_retrofit_room.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_hilt_db_retrofit_room.models.TaskItem
import com.example.mvvm_hilt_db_retrofit_room.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: TaskRepository): ViewModel() {

    val taskLiveData: LiveData<List<TaskItem>>
        get() = repository.tasks

//    init {
//        viewModelScope.launch {
//            repository.getAllProducts()
//        }
//    }

    fun loadDataFromDB(listType: TaskListType) {
        viewModelScope.launch {
            when(listType) {
                is TaskListType.DeleteTasks -> deleteTask(listType.task)
                TaskListType.GetAllTasks -> getAllTasks()
                TaskListType.GetCompletedTasks -> getCompletedTasks()
                TaskListType.GetPendingTasks -> getPendingTasks()
                is TaskListType.InsertTasks -> insertTask(listType.task)
                else -> {getAllTasks()}
            }
        }
    }

    private suspend fun getAllTasks() {
        repository.getAllTasks()
    }

    private suspend fun getPendingTasks() {
        repository.getPendingTasks()
    }

    private suspend fun getCompletedTasks() {
        repository.getCompletedTasks()
    }

    private suspend fun insertTask(item: TaskItem) {
        repository.insertTask(item)
    }

    private suspend fun deleteTask(item: TaskItem) {
        repository.deleteTask(item)
    }
}

sealed class TaskListType {
    data object GetAllTasks: TaskListType()
    data object GetPendingTasks: TaskListType()
    data object GetCompletedTasks: TaskListType()
    data class InsertTasks(val task: TaskItem): TaskListType()
    data class DeleteTasks(val task: TaskItem): TaskListType()
}