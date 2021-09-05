package com.example.trainapplication

class Provider {
    private val data = GenerateDataHelper.getData()

    private val trainApplication = TrainApplication

    fun getTrainData() = data

    fun setSortType(sortType: SortTypeModel) {
        trainApplication.getDatabase()?.sortTypeDao()?.setSortType(sortType)
    }

    fun getSortType(): SortTypeModel? {
        val data = trainApplication.getDatabase()?.sortTypeDao()?.getSortType()

        if (data == null)
            trainApplication.getDatabase()?.sortTypeDao()?.addSortType(SortTypeModel("ID",0))

        return trainApplication.getDatabase()?.sortTypeDao()?.getSortType()
    }
}