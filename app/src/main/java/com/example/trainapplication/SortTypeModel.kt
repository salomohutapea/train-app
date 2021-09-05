package com.example.trainapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sorttype")
data class SortTypeModel(
    @PrimaryKey val id: String = "ID",
    val type: Int
)