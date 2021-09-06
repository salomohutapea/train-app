package com.example.trainapplication

import androidx.lifecycle.ViewModel
import io.reactivex.subjects.BehaviorSubject

class TrainViewModel : ViewModel() {
    var sortType = BehaviorSubject.create<SortTypeModel>()
    var trainSearchResult = BehaviorSubject.create<List<TrainModel>>()
}