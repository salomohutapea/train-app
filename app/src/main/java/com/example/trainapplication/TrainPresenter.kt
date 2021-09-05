package com.example.trainapplication

class TrainPresenter constructor(private val viewModel: TrainViewModel) {
    private val data = GenerateDataHelper.getData()
    private val provider = Provider()

    fun getSearchResult(query: String) {
        if (query == "") {
            viewModel.trainSearchResult.postValue(data)
        } else {
            val filteredData = data?.filter { it.name.contains(query, ignoreCase = true) }
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