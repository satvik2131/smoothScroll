package com.assignment.smoothsroll.retrofit

import com.assignment.smoothsroll.model.Data
import retrofit2.http.GET

interface DataService{
    @GET("media-coverages?limit=100")
    suspend fun getData():List<Data>
}