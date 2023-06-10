package com.example.core_entities

import androidx.room.TypeConverter
import java.util.stream.Collectors




internal class TegsConverter {

    @TypeConverter
    fun fromHobbies(hobbies: List<String?>): String? {
        return hobbies.stream().collect(Collectors.joining(","))
    }

    @TypeConverter
    fun toHobbies(data: String): List<String> {
        return listOf(*data.split(",".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray())
    }

}