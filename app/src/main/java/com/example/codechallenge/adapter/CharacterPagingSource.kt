package com.example.codechallenge.ui.adapter

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.codechallenge.api.CharacterRepository
import com.example.codechallenge.model.Character
import java.lang.Exception

class CharacterPagingSource(val repository: CharacterRepository) : PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX
            val response = repository.fetchImages(nextPage)
            var nextPageNumber: Int? = null
            val uri = Uri.parse(response.info.next)
            val nextPageQuery = uri.getQueryParameter("page")
            nextPageNumber = nextPageQuery?.toInt()
            LoadResult.Page(data = response.results, prevKey = null, nextKey = nextPageNumber)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }
}