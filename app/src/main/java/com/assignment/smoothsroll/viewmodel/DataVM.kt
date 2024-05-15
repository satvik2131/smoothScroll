package com.assignment.smoothsroll.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.smoothsroll.model.Data
import com.assignment.smoothsroll.retrofit.DataService
import com.assignment.smoothsroll.retrofit.RetrofitInstance
import kotlinx.coroutines.launch

class DataVM(val apiService: DataService = RetrofitInstance.api) : ViewModel() {
    val data:MutableState<List<Data>> = mutableStateOf(emptyList<Data>())
    fun fetchImages() {
        viewModelScope.launch {
             data.value = apiService.getData()
        }
    }
}