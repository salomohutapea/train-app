package com.example.trainapplication

import android.util.Log

class Provider {
    var trainApplication = Application

    fun setSortType(sortType: SortTypeModel) {
        Log.d("TAAG UPDATED", sortType.toString())
        trainApplication.getDatabase()?.sortTypeDao()?.setSortType(sortType)
    }

    fun getSortType(): SortTypeModel? {
        val data = trainApplication.getDatabase()?.sortTypeDao()?.getSortType()

        if (data == null)
            trainApplication.getDatabase()?.sortTypeDao()?.addSortType(SortTypeModel("ID",0))

        return trainApplication.getDatabase()?.sortTypeDao()?.getSortType()
    }
}