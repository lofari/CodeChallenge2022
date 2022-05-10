package com.example.codechallenge.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.codechallenge.model.Character
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.codechallenge.api.CharacterRepository
import com.example.codechallenge.common.Resource
import com.example.codechallenge.model.CharactersResponse
import com.example.codechallenge.ui.adapter.CharacterPagingSource
import com.example.codechallenge.usecase.FetchCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val fetchCharactersUseCase: FetchCharactersUseCase,
    private val repository: CharacterRepository
) : ViewModel() {

    val pictureList: LiveData<Resource<CharactersResponse>>
        get() = _pictureList
    private val _pictureList = MutableLiveData<Resource<CharactersResponse>>()

//    init {
//        load()
//    }

//    fun load() {
//        fetchCharactersUseCase().onEach { result ->
//            when (result) {
//                is Resource.Success -> {
//                    _pictureList.postValue(result)
//
//                }
//                is Resource.Error -> {
//                    _pictureList.postValue(Resource.Error("${result.message}"))
//
//                }
//                is Resource.Loading -> {
//                    _pictureList.postValue(Resource.Loading())
//
//                }
//            }
//        }.launchIn(viewModelScope)
//    }

    fun getList(): Flow<PagingData<Character>> {
        return Pager(config = PagingConfig(pageSize = 42, maxSize = 200),
            pagingSourceFactory = { CharacterPagingSource(repository) }).flow.cachedIn(
            viewModelScope
        )
    }

}