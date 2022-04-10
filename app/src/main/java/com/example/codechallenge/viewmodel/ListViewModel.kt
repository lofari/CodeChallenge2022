package com.example.codechallenge.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.codechallenge.model.Character
import androidx.lifecycle.viewModelScope
import com.example.codechallenge.api.RetrofitInstance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor() : ViewModel() {

    val pictureList: LiveData<List<Character>>
        get() = _pictureList
    private val _pictureList = MutableLiveData<List<Character>>()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        // Log Error
        Log.e("TAG", "TEST")
        // Todo: error handling

    }

    fun load() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val response =
                RetrofitInstance.api.fetchImages()

            if (response.isSuccessful && response.body() != null) {
                _pictureList.postValue(response.body()!!.results)
            }


        }
    }
}