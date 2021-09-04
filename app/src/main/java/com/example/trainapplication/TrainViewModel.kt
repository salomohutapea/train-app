package com.example.trainapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TrainViewModel : ViewModel() {
    var trainSearchResult = MutableLiveData<ArrayList<TrainModel>>()
}