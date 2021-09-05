package com.example.trainapplication

import android.util.Log

class TrainPresenter(private val viewModel: TrainViewModel) {
    private val data = GenerateDataHelper.getData()

    fun getSearchResult(query: String) {
        if (query == "") {
            Log.d("TAAAG", "KOSONG")
            viewModel.trainSearchResult.postValue(data)
        } else {
            val filteredData = data?.filter { it.name.contains(query, ignoreCase = true) }
            viewModel.trainSearchResult.postValue(filteredData)
        }
    }

    fun setCurrentSortType() {

    }
}