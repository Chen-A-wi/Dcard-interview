package com.example.searchdemo.ui.search

import android.view.View
import androidx.lifecycle.viewModelScope
import com.example.searchdemo.common.utils.SchedulerProvider
import com.example.searchdemo.common.utils.SingleLiveEvent
import com.example.searchdemo.data.Item
import com.example.searchdemo.data.Repositories
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
    private val onClickEvent = SingleLiveEvent<Int>()
    var repositoriesList = arrayListOf<Item>()
    val notifyEvent by lazy { SingleLiveEvent<Unit> ()}

    init {
        searchRepositories("android", 1)
    }

    fun onClick(v: View) = onClickEvent.postValue(v.id)

    fun searchRepositories(keyword: String, page: Int) {
        viewModelScope.launch {
            repository.getRepositories(keyword = keyword, page = page)
                .flowOn(scheduler.io())
                .catch { e ->
                    Logger.d(e)
                }
                .collect { result ->
                    result.items?.let { item ->
                        repositoriesList.addAll(item)
                    }
                    notifyEvent.postValue(Unit)
                    Logger.d(repositoriesList)
                }
        }
    }
}