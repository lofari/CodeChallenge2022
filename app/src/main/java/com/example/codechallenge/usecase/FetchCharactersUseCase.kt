package com.example.codechallenge.usecase

import com.example.codechallenge.api.CharacterRepository
import com.example.codechallenge.common.Resource
import com.example.codechallenge.model.CharactersResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FetchCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(): Flow<Resource<CharactersResponse>> = flow {
        try {
            emit(Resource.Loading())
            val charactersResponse = repository.fetchImages()
            emit(Resource.Success(charactersResponse))
        } catch (e: HttpException) {
            emit(Resource.Error<CharactersResponse>(e.localizedMessage ?: "An unexpected error ocurred"))
        } catch (e: IOException) {
            emit(Resource.Error<CharactersResponse>( "An unexpected error ocurred"))
        }
    }
}