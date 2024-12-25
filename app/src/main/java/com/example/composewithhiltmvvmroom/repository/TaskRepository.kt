package com.example.mvvm_hilt_db_retrofit_room.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_hilt_db_retrofit_room.models.TaskItem
import com.example.mvvm_hilt_db_retrofit_room.retrofit.TaskAPIs
import com.example.mvvm_hilt_db_retrofit_room.room_db.TasksDB
import okhttp3.internal.notifyAll
import javax.inject.Inject

class TaskRepository @Inject constructor(private val taskAPIs: TaskAPIs, private val tasksDB: TasksDB) {

    private val _tasks = MutableLiveData<List<TaskItem>>()

    val tasks: LiveData<List<TaskItem>>
        get() = _tasks

    suspend fun getAllTasks() {
        val result = tasksDB.getTasksDao().getAllTaskItems()
        if(result.isNotEmpty()) {
            _tasks.postValue(result)
        }
    }

    suspend fun getPendingTasks() {
        val result = tasksDB.getTasksDao().getPendingTasks()
//        if(result.isNotEmpty()) {
            _tasks.postValue(result)
//        }
    }

    suspend fun getCompletedTasks() {
        val result = tasksDB.getTasksDao().getCompletedTasks()
//        if(result.isNotEmpty()) {
            _tasks.postValue(result)
//        }
    }

    suspend fun insertTask(task: TaskItem) {
        tasksDB.getTasksDao().insertTask(task)
    }

//    private val lock = Object()
    suspend fun insertAllTasks(tasks: List<TaskItem>) {
        //TODO
//        synchronized(lock) {
//            val result = tasksDB.getTasksDao().insertAllTaskItems(tasks)
//            // Notify any threads waiting for this operation
//            lock.notifyAll()
//        }
    }

    suspend fun deleteTask(task: TaskItem) {
        tasksDB.getTasksDao().deleteTask(task)
    }
}