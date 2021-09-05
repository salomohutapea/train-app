package com.example.trainapplication

class TrainPresenter constructor(private val viewModel: TrainViewModel) {

    private val provider = Provider()
    private val trainData = provider.getTrainData()

    fun getSearchResult(query: String) {
        if (query == "") {
            viewModel.trainSearchResult.postValue(trainData)
        } else {
            val filteredData = trainData?.filter { it.name.contains(query, ignoreCase = true) }
            viewModel.trainSearchResult.postValue(filteredData)
        }
    }

    fun setSortType(sortType: SortTypeModel) {
        provider.setSortType(sortType)
    }

    fun getSortType() {
        viewModel.sortType.postValue(provider.getSortType())
    }

}