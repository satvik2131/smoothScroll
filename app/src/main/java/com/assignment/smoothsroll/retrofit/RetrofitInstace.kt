package com.assignment.smoothsroll.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    private const val BASE_URL = "https://acharyaprashant.org/api/v2/content/misc/"
    val gson: Gson = GsonBuilder()
        .setLenient()
        .create()
    val api: DataService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        retrofit.create(DataService::class.java)
    }
}