package com.example.core_db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.io.Serializable

@Entity
data class DishTable(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    val id: Int = 0,
    @ColumnInfo(name="name")
    val name: String = "",
    @ColumnInfo("price")
    val price: Int = 0,
    @ColumnInfo("weight")
    val weight: Int = 0,
    @ColumnInfo("description")
    val description: String = "",
    @ColumnInfo("imageUrl")
    val imageUrl: String = "",
    @TypeConverters(TegsConverter::class)
    @ColumnInfo("tegs")
    val tegs: List<String> = listOf(),
    @ColumnInfo("amount")
    val amount: Int = 0
)