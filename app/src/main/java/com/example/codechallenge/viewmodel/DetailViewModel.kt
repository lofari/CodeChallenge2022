package com.example.codechallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codechallenge.common.Constants
import com.example.codechallenge.common.Resource
import com.example.codechallenge.model.Character
import com.example.codechallenge.usecase.FetchCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val fetchCharacterUseCase: FetchCharacterUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val detail: LiveData<Character>
        get() = _detail
    private val _detail = MutableLiveData<Character>()

    init {
        savedStateHandle.get<String>(Constants.CACHE_KEY)?.let {
            fetchImageDetail(it)
        }
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
    }
}

