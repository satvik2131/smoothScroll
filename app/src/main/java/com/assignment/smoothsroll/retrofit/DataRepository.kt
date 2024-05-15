package com.assignment.smoothsroll.retrofit

import com.assignment.smoothsroll.model.Data

class DataRepository  {
    private val dataService = RetrofitInstance.api

    suspend fun getData(): List<Data> {
        return dataService.getData()
    }
}