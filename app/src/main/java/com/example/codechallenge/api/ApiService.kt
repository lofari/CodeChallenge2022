package com.example.codechallenge.api

import com.example.codechallenge.common.Constants
import com.example.codechallenge.model.Character
import com.example.codechallenge.model.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET(Constants.IMAGES_URL)
    suspend fun fetchImages(): CharactersResponse

    @GET("character/{id}")
    suspend fun fetchDetail(
        @Path("id") id: String
    ): Character


}
