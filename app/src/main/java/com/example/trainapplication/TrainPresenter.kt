package com.example.trainapplication

class TrainPresenter (private val viewModel: TrainViewModel) {

    var provider = Provider()
    var sortSearchDomain = SortSearchDomain()
    private var searchQuery = ""

    init {
        provider.getTrainData().let {
            if (it != null) {
                sortSearchDomain.trainData = it
            }
        }
    }

    fun getSearchResult(query: String) {
        searchQuery = query
        sortSearchDomain.searchAndSort(query).let { viewModel.trainSearchResult.onNext(it) }
    }

    fun setSortType(sortType: SortTypeModel) {
        provider.setSortType(sortType)
        sortSearchDomain.sortType = sortType
        sortSearchDomain.searchAndSort(searchQuery).let { viewModel.trainSearchResult.onNext(it) }
    }

    fun getSortType() {
        sortSearchDomain.sortType = provider.getSortType()
        sortSearchDomain.sortType?.let { viewModel.sortType.onNext(it) }
    }
}