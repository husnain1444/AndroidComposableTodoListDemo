package com.example.mvvm_hilt_db_retrofit_room.utils

object Constants {
    const val BASE_URL: String = "https://taskapis.com/"
}

enum class BottomNavActions {
    ALL,
    PENDING,
    COMPLETED
}

fun String.toCamelCaseTitle(): String {
    return this.split(" ") // Split the string by spaces
        .joinToString(" ") { it.lowercase().replaceFirstChar { char -> char.uppercase() } } // Capitalize the first letter of each word
}
