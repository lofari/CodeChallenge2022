package com.example.codechallenge.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.codechallenge.model.Character
import androidx.lifecycle.viewModelScope
import com.example.codechallenge.common.Resource
import com.example.codechallenge.usecase.FetchCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val fetchCharactersUseCase: FetchCharactersUseCase
) : ViewModel() {

    val pictureList: LiveData<List<Character>>
        get() = _pictureList
    private val _pictureList = MutableLiveData<List<Character>>()

    init {
        load()
    }

    fun load(){
        fetchCharactersUseCase().onEach { result ->
            when (result) {
                is Resource.Success ->
                    _pictureList.postValue(result.data?.results)
//                is Result.Error -> //TODO
//
//                is Result.Loading ->  //TODO
            }
        }.launchIn(viewModelScope)
    }
}