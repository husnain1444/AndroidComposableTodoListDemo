package com.example.mvvm_hilt_db_retrofit_room.room_db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mvvm_hilt_db_retrofit_room.models.TaskItem
import com.example.mvvm_hilt_db_retrofit_room.room_db.converters.DateConverter

@Database(entities = [TaskItem::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class TasksDB: RoomDatabase() {

    abstract fun getTasksDao(): TasksDao
}