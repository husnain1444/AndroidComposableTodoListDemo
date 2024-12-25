package com.example.mvvm_hilt_db_retrofit_room.room_db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvm_hilt_db_retrofit_room.models.TaskItem

@Dao
interface TasksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTaskItems(items: List<TaskItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(item: TaskItem)

    @Delete
    suspend fun deleteTask(item: TaskItem)

    @Query("SELECT * FROM TaskItem WHERE isCompleted = 1")
    suspend fun getCompletedTasks(): List<TaskItem>

    @Query("SELECT * FROM TaskItem WHERE isCompleted = 0")
    suspend fun getPendingTasks(): List<TaskItem>

    @Query("SELECT * FROM TaskItem")
    suspend fun getAllTaskItems(): List<TaskItem>
}