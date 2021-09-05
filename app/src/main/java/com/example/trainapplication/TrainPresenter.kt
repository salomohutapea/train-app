package com.example.trainapplication

class TrainPresenter constructor(private val viewModel: TrainViewModel) {

    private val provider = Provider()
    private val trainData = provider.getTrainData()
    private var filteredData = trainData
    private var sortType: SortTypeModel? = null

    fun getSearchResult(query: String) {
        if (query == "") {
            filteredData = trainData
            val sortedData = trainData?.toMutableList()?.let { sortTrain(it) }
            viewModel.trainSearchResult.postValue(sortedData)
        } else {
            filteredData = trainData?.filter { it.name.contains(query, ignoreCase = true) }
            val sortedData = filteredData?.toMutableList()?.let { sortTrain(it) }
            viewModel.trainSearchResult.postValue(sortedData)
        }
    }

    fun setSortType(sortType: SortTypeModel) {
        provider.setSortType(sortType)
        this.sortType = sortType
        val sortedData = filteredData?.toMutableList()?.let { sortTrain(it) }
        viewModel.trainSearchResult.postValue(sortedData)
    }

    fun getSortType() {
        sortType = provider.getSortType()
        viewModel.sortType.postValue(sortType)
    }

    private fun sortTrain(items: MutableList<TrainModel>): List<TrainModel> {

        when (sortType?.type) {
            0 -> {
                for (count in 1 until items.count()) {
                    val item = items[count]
                    var i = count
                    while (i > 0 && item.price < items[i - 1].price) {
                        items[i] = items[i - 1]
                        i -= 1
                    }
                    items[i] = item
                }
            }
            1 -> {
                for (count in 1 until items.count()) {
                    val item = items[count]
                    var i = count
                    while (i > 0 && item.departingTime < items[i - 1].departingTime) {
                        items[i] = items[i - 1]
                        i -= 1
                    }
                    items[i] = item
                }
            }
            2 -> {
                for (count in 1 until items.count()) {
                    val item = items[count]
                    var i = count
                    while (i > 0 && item.availableSeats < items[i - 1].availableSeats) {
                        items[i] = items[i - 1]
                        i -= 1
                    }
                    items[i] = item
                }
                items.reverse()
            }
        }

        return items
    }
}