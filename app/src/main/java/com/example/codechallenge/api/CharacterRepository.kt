package com.example.codechallenge.api

import com.example.codechallenge.model.Character
import com.example.codechallenge.model.CharactersResponse

interface CharacterRepository {

    suspend fun fetchImages(page: Int): CharactersResponse

    suspend fun fetchDetail(id: String): Character
}