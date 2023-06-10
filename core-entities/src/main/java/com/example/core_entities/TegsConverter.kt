package com.example.core_entities

import androidx.room.TypeConverter
import java.util.stream.Collectors



class TegsConverter {

    @TypeConverter
    fun fromTegs(tegs: List<String?>): String {
        return tegs.stream().collect(Collectors.joining(","))
    }

    @TypeConverter
    fun toTegs(data: String): List<String> {
        return listOf(*data.split(",".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray())
    }

}