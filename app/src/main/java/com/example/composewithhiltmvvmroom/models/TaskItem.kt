package com.example.mvvm_hilt_db_retrofit_room.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class TaskItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val type: String,
    val image: String,
    val isCompleted: Boolean,
    val completionDate: String,
    val creationDate: String
)