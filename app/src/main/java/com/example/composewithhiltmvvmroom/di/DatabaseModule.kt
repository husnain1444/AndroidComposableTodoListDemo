package com.example.mvvm_hilt_db_retrofit_room.di

import android.content.Context
import androidx.room.Room
import com.example.mvvm_hilt_db_retrofit_room.room_db.TasksDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providesTaskDatabase(@ApplicationContext context: Context): TasksDB {
        return Room.databaseBuilder(context = context, TasksDB::class.java, "TasksDB").build()
    }
}