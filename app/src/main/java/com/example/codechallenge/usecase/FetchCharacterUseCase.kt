package com.example.codechallenge.usecase

import com.example.codechallenge.api.CharacterRepository
import com.example.codechallenge.common.Resource
import com.example.codechallenge.model.Character
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class FetchCharacterUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(id: String): Flow<Resource<Character>> = flow {
        try {
            emit(Resource.Loading())
            val character = repository.fetchDetail(id)
            emit(Resource.Success(character))
        } catch (e: HttpException) {
            emit(Resource.Error<Character>(e.localizedMessage ?: "An unexpected error ocurred"))
        } catch (e: IOException) {
            emit(Resource.Error<Character>("An unexpected error ocurred"))
        }
    }
}
