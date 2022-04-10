package com.example.codechallenge.api

import com.example.codechallenge.model.Character
import com.example.codechallenge.model.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterRepository {

    suspend fun fetchImages(): Response<CharactersResponse>

    suspend fun fetchDetail(id: String): Character
}