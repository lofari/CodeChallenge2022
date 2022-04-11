package com.example.codechallenge.usecase

import com.example.codechallenge.api.CharacterRepository
import com.example.codechallenge.common.Resource
import com.example.codechallenge.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FetchCharacterUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(id: String): Flow<Resource<Character>> = flow {
        try {
            emit(Resource.Loading())
            val character = repository.fetchDetail(id)
            emit(Resource.Success(character))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error ocurred") ) as Resource<Character>
        } catch (e: IOException) {
            emit(Resource.Error( "An unexpected error ocurred")) as Resource<Character>
        }
    }
}