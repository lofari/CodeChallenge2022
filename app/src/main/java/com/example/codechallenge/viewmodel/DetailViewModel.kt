package com.example.codechallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codechallenge.common.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import com.example.codechallenge.model.Character
import com.example.codechallenge.usecase.FetchCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val fetchCharacterUseCase: FetchCharacterUseCase
) : ViewModel() {

    val detail: LiveData<Character>
        get() = _detail
    private val _detail = MutableLiveData<Character>()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        // Log Error
    }

    fun fetchImageDetail(imageId: String) {
        fetchCharacterUseCase(imageId).onEach { result ->
            when (result) {
                is Resource.Success ->
                    _detail.postValue(result.data!!)
//                is Result.Error -> //TODO
//
//                is Result.Loading ->  //TODO
            }
        }.launchIn(viewModelScope)
//        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
//            val response = RetrofitInstance.api.fetchDetail(imageId)
//            if (response.isSuccessful && response.body() != null) {
//                response.body()?.let {
//                    _detail.postValue(it)
//                }
//            }
//        }
    }
}

