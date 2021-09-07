package com.example.trainapplication

class TrainPresenter constructor(private val viewModel: TrainViewModel) {

    private val provider = Provider()
    private val trainData = provider.getTrainData()
    private val sortSearchDomain = trainData?.let { SortSearchDomain(it) }
    private var searchQuery = ""

    fun getSearchResult(query: String) {
        searchQuery = query
        sortSearchDomain?.searchAndSort(query)?.let { viewModel.trainSearchResult.onNext(it) }
    }

    fun setSortType(sortType: SortTypeModel) {
        provider.setSortType(sortType)
        sortSearchDomain?.sortType = sortType
        sortSearchDomain?.searchAndSort(searchQuery)?.let { viewModel.trainSearchResult.onNext(it) }
    }

    fun getSortType() {
        sortSearchDomain?.sortType = provider.getSortType()
        sortSearchDomain?.sortType?.let { viewModel.sortType.onNext(it) }
    }
}