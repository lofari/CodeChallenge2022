package com.example.codechallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codechallenge.api.RetrofitInstance
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.codechallenge.model.Character

class DetailViewModel : ViewModel() {

    val detail: LiveData<Character>
        get() = _detail
    private val _detail = MutableLiveData<Character>()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        // Log Error
    }

    fun fetchImageDetail(imageId: String) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val response = RetrofitInstance.api.fetchDetail(imageId)
            if (response.isSuccessful && response.body() != null) {
                response.body()?.let {
                    _detail.postValue(it)
                }
            }
        }
    }
}

