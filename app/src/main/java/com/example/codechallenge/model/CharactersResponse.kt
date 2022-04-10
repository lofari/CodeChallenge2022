package com.example.codechallenge.model

import com.example.codechallenge.model.Character
import com.example.codechallenge.model.Info

data class CharactersResponse(
    val info: Info,
    val results: List<Character>
)