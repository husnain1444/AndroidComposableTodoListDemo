package com.example.mvvm_hilt_db_retrofit_room.retrofit

import com.example.mvvm_hilt_db_retrofit_room.models.TaskItem
import retrofit2.Response
import retrofit2.http.GET

interface TaskAPIs {

    @GET("tasks")
    suspend fun getTasks(): Response<List<TaskItem>>
}