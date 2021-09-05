package com.example.trainapplication

data class TrainModel(
    val id: String,
    val name: String,
    val departingTime: String,
    val arrivingTime: String,
    val departingFrom: String,
    val arrivingIn: String,
    val availableSeats: Int,
    val price: Long,
)
