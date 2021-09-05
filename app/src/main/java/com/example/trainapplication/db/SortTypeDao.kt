package com.example.trainapplication.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.trainapplication.SortTypeModel

@Dao
interface SortTypeDao {

    @Update
    fun setSortType(sortType: SortTypeModel)

    @Insert
    fun addSortType(sortType: SortTypeModel)

    @Query("SELECT * from sorttype")
    fun getSortType(): SortTypeModel
}