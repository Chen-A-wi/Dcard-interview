package com.example.searchdemo.ui.search

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.example.searchdemo.common.ext.default
import com.example.searchdemo.common.ext.safeLet
import com.example.searchdemo.common.utils.SchedulerProvider
import com.example.searchdemo.common.utils.SingleLiveEvent
import com.example.searchdemo.data.Item
import com.example.searchdemo.data.repository.SearchRepository
import com.example.searchdemo.ui.base.BaseViewModel
import com.orhanobut.logger.Logger
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: SearchRepository,
    private val scheduler: SchedulerProvider
) : BaseViewModel() {
    var repositoriesList = arrayListOf<Item>()
    val notifyEvent by lazy { SingleLiveEvent<List<Item>>() }
    val keyword by lazy { MediatorLiveData<String>().default("android") }
    val currentPage by lazy { MediatorLiveData<Int>().default(1) }

    init {
        safeLet(keyword.value, currentPage.value) { keyword, page ->
            searchRepositories(keyword = keyword, page = page)
        }
    }

    fun searchRepositories(keyword: String, page: Int) {
        isLoading.postValue(true)
        viewModelScope.launch {
            repository.getRepositories(keyword = keyword, page = page)
                .flowOn(scheduler.io())
                .catch { e ->
                    Logger.d(e)
                    isLoading.postValue(false)
                }
                .collect { result ->
                    result.items?.let { item ->
                        repositoriesList.addAll(item)
                    }
                    notifyEvent.postValue(repositoriesList)
                    Logger.d(repositoriesList)
                    isLoading.postValue(false)
                }
        }
    }
}