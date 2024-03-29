package com.example.codechallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codechallenge.common.Resource
import com.example.codechallenge.model.CharactersResponse
import com.example.codechallenge.usecase.FetchCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class ListViewModel @Inject constructor(
    private val fetchCharactersUseCase: FetchCharactersUseCase
) : ViewModel() {

    val pictureList: LiveData<Resource<CharactersResponse>>
        get() = _pictureList
    private val _pictureList = MutableLiveData<Resource<CharactersResponse>>()

    init {
        load()
    }

    private fun load() {
        fetchCharactersUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _pictureList.postValue(result)
                }
                is Resource.Error -> {
                    _pictureList.postValue(Resource.Error("${result.message}"))
                }
                is Resource.Loading -> {
                    _pictureList.postValue(Resource.Loading())
                }
            }
        }.launchIn(viewModelScope)
    }
}
