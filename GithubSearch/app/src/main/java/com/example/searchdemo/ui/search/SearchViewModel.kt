package com.example.searchdemo.ui.search

import android.view.View
import androidx.lifecycle.viewModelScope
import com.example.searchdemo.common.utils.SchedulerProvider
import com.example.searchdemo.common.utils.SingleLiveEvent
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
    val onClickEvent = SingleLiveEvent<Int>()

    fun onClick(v: View) = onClickEvent.postValue(v.id)

    fun searchRepositories() {
        viewModelScope.launch {
            repository.getRepositories("android", 1)
                .flowOn(scheduler.io())
                .catch { e ->
                    Logger.d(e)
                }
                .collect { result ->
                    Logger.d(result.items)
                }
        }
    }
}