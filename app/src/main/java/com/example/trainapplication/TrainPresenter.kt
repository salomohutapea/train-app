package com.example.trainapplication

class TrainPresenter(private val viewModel: TrainViewModel) {
    private val data = GenerateDataHelper.getData()

    fun getSearchResult(query: String) {
        if (query == "") {
            viewModel.trainSearchResult.postValue(data)
        }
    }

    fun setCurrentSortType() {

    }
}