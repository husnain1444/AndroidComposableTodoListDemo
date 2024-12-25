package com.example.mvvm_hilt_db_retrofit_room.room_db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Date

class DateConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromMyObject(myObject: Date?): String? {
        return gson.toJson(myObject)
    }

    @TypeConverter
    fun toMyObject(json: String?): Date? {
        val type = object : TypeToken<Date>() {}.type
        return gson.fromJson(json, type)
    }
}