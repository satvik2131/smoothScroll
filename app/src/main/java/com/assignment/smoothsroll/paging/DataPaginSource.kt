package com.assignment.smoothsroll.paging

import androidx.compose.runtime.remember
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.assignment.smoothsroll.model.Data
import com.assignment.smoothsroll.retrofit.DataService
import com.assignment.smoothsroll.retrofit.RetrofitInstance
import com.assignment.smoothsroll.viewmodel.DataVM
import kotlin.math.max

class DataPagingSource(apiService: DataService): PagingSource<Int, Data>() {
    private val STARTING_KEY:Int = 0
    private val apiService = apiService
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val page = params.key ?: 1
            LoadResult.Page(
                data = apiService.getData(),
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (apiService.getData().isEmpty()) null else page.plus(1),
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }

//    private fun ensureValidKey(key: Int): Int {
//        return max(STARTING_KEY, key)
//    }

}