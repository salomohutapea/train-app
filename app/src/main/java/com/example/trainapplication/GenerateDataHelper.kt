package com.example.trainapplication

import com.beust.klaxon.Klaxon

object GenerateDataHelper {
    fun getData(): List<TrainModel>? {
        return Klaxon()
            .parseArray(
                """
                    [
                        {
                          "id": "1djlwhjkvn",
                          "name": "Gajayana Luxury",
                          "departingTime": "2021-09-07 20:15:00",
                          "arrivingTime": "2021-09-08 03:29:00",
                          "departingFrom": "Yogyakarta (YK)",
                          "arrivingIn": "Gambir (GMR)",
                          "availableSeats": 10,
                          "price": 1050000,
                        },
                        {
                          "id": "2knsglknvj",
                          "name": "Gajayana",
                          "departingTime": "2021-09-07 20:15:00",
                          "arrivingTime": "2021-09-08 03:29:00",
                          "departingFrom": "Yogyakarta (YK)",
                          "arrivingIn": "Gambir (GMR)",
                          "availableSeats": 20,
                          "price": 480000,
                        },
                        {
                          "id": "3lksnvjdhg",
                          "name": "Argo Dwipangga",
                          "departingTime": "2021-09-07 20:47:00",
                          "arrivingTime": "2021-09-08 03:55:00",
                          "departingFrom": "Yogyakarta (YK)",
                          "arrivingIn": "Gambir (GMR)",
                          "availableSeats": 14,
                          "price": 390000,
                        },
                        {
                          "id": "4kejckv7zw",
                          "name": "Argo Dwipangga Luxury",
                          "departingTime": "2021-09-07 20:47:00",
                          "arrivingTime": "2021-09-08 03:55:00",
                          "departingFrom": "Yogyakarta (YK)",
                          "arrivingIn": "Gambir (GMR)",
                          "availableSeats": 4,
                          "price": 900000,
                        },
                        {
                          "id": "5qu3xv4j2a",
                          "name": "Bima",
                          "departingTime": "2021-09-07 21:21:00",
                          "arrivingTime": "2021-09-08 04:52:00",
                          "departingFrom": "Yogyakarta (YK)",
                          "arrivingIn": "Gambir (GMR)",
                          "availableSeats": 32,
                          "price": 410000,
                        },
                        {
                          "id": "61zvjwrlk0",
                          "name": "Argo Dwipangga",
                          "departingTime": "2021-09-07 23:04:00",
                          "arrivingTime": "2021-09-08 06:31:00",
                          "departingFrom": "Yogyakarta (YK)",
                          "arrivingIn": "Gambir (GMR)",
                          "availableSeats": 11,
                          "price": 390000,
                        },
                        {
                          "id": "7bwx92mcvk",
                          "name": "Tasaka",
                          "departingTime": "2021-09-07 08:50:00",
                          "arrivingTime": "2021-09-08 15:59:00",
                          "departingFrom": "Yogyakarta (YK)",
                          "arrivingIn": "Gambir (GMR)",
                          "availableSeats": 18,
                          "price": 320000,
                        },
                        {
                          "id": "82skzfgnm3",
                          "name": "Tasaka Luxury",
                          "departingTime": "2021-09-07 08:50:00",
                          "arrivingTime": "2021-09-08 15:59:00",
                          "departingFrom": "Yogyakarta (YK)",
                          "arrivingIn": "Gambir (GMR)",
                          "availableSeats": 4,
                          "price": 800000,
                        },
                        {
                          "id": "9gx83cjv2a",
                          "name": "Argo Lawu Luxury",
                          "departingTime": "2021-09-07 09:22:00",
                          "arrivingTime": "2021-09-08 16:28:00",
                          "departingFrom": "Yogyakarta (YK)",
                          "arrivingIn": "Gambir (GMR)",
                          "availableSeats": 4,
                          "price": 900000,
                        },
                        {
                          "id": "10axvkt78e",
                          "name": "Argo Lawu",
                          "departingTime": "2021-09-07 09:22:00",
                          "arrivingTime": "2021-09-08 16:28:00",
                          "departingFrom": "Yogyakarta (YK)",
                          "arrivingIn": "Gambir (GMR)",
                          "availableSeats": 10,
                          "price": 360000,
                        },
                    ]
                    """
            )
    }
}