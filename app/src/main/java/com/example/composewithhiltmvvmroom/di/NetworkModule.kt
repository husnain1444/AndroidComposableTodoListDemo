package com.example.mvvm_hilt_db_retrofit_room.di

import com.example.mvvm_hilt_db_retrofit_room.retrofit.TaskAPIs
import com.example.mvvm_hilt_db_retrofit_room.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesTaskAPI(retrofit: Retrofit): TaskAPIs {
        return retrofit.create(TaskAPIs::class.java)
    }
}