package com.example.codechallenge.api

import com.example.codechallenge.model.Character
import com.example.codechallenge.model.CharactersResponse
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api: ApiService
): CharacterRepository {
    override suspend fun fetchImages(): CharactersResponse {
        return api.fetchImages()
    }

    override suspend fun fetchDetail(id: String): Character {
        return api.fetchDetail(id)
    }
}