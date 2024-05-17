package com.assignment.smoothsroll.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.assignment.smoothsroll.model.Data
import com.assignment.smoothsroll.paging.DataPagingSource
import com.assignment.smoothsroll.retrofit.DataService
import com.assignment.smoothsroll.retrofit.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DataVM() : ViewModel() {
    val data:MutableState<List<Data>> = mutableStateOf(emptyList<Data>())
    private val apiService: DataService = RetrofitInstance.api
    fun getImageData() = Pager(
        config = PagingConfig(
            pageSize = 20,
        ),
        pagingSourceFactory = { DataPagingSource(apiService) }
    ).flow


    fun getDataFlow(): Flow<PagingData<Data>> = getImageData().cachedIn(viewModelScope)

    fun fetchImages() {
        viewModelScope.launch {
             data.value = apiService.getData()
        }
    }
}