package com.example.codechallenge.api

import com.example.codechallenge.common.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
//    private lateinit var apiService: ApiService
//
//    fun getApiService(): ApiService {
//        if (!::apiService.isInitialized) {
//            val retrofit = Retrofit.Builder()
//                .baseUrl(Constants.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(okHttpClient())
//                .build()
//            apiService = retrofit.create(ApiService::class.java)
//        }
//        return apiService
//    }
//
}