package com.example.trainapplication

class SortSearchDomain(private var trainData: List<TrainModel>) {

    private var filteredData = trainData
    var sortType: SortTypeModel? = null

    fun searchAndSort(query: String): List<TrainModel> {
        filteredData = if (query == "") {
            trainData
        } else {
            trainData.filter { it.name.contains(query, ignoreCase = true) }
        }
        return sort()
    }

    private fun sort(): List<TrainModel> {
        val items: MutableList<TrainModel> = filteredData.toMutableList()

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