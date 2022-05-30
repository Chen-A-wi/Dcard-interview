package com.example.searchdemo.ui.search

import android.util.Log
import android.view.View
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_DRAGGING
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE
import com.example.searchdemo.R
import com.example.searchdemo.common.ext.debounce
import com.example.searchdemo.common.ext.default
import com.example.searchdemo.common.ext.safeLet
import com.example.searchdemo.common.utils.SingleLiveEvent
import com.example.searchdemo.data.ErrorMessage
import com.example.searchdemo.data.Item
import com.example.searchdemo.data.Repositories
import com.example.searchdemo.repository.SearchRepository
import com.example.searchdemo.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel(
    private val repository: SearchRepository,
) : BaseViewModel() {
    var repositoriesList = arrayListOf<Item>()
    val notifyEvent by lazy { MutableLiveData<Unit>() }
    val clickLiveEvent by lazy { SingleLiveEvent<Int>() }
    private val currentPage by lazy { MediatorLiveData<Int>().default(1) }
    val edtSearch by lazy { MediatorLiveData<String>().default("") }
    val errorText = MutableLiveData(R.string.empty)
    val isShowError = MutableLiveData<Boolean>()
    val isShowClear = MediatorLiveData<Boolean>()
    val isFocus = MutableLiveData(false)
    val keywordStateFlow = MutableStateFlow("")

    init {
        viewModelScope.launch {
            keywordStateFlow
                .debounce(500)
                .collect { word ->
                    isShowClear.postValue(word.isNotEmpty())
                    setupEditTextError(word)
                    if (word.isNotBlank()) {
                        searchRepositories(keyword = word, page = 1, restState = true)
                    } else {
                        resetPage()
                    }

                }
        }
    }

    fun onClick(v: View) {
        clickLiveEvent.postValue(v.id)
    }

    fun searchRepositories(keyword: String, page: Int, restState: Boolean) {
        isLoading.postValue(true)
        repository.getRepositories(keyword = keyword, page = page)
            .enqueue(object : Callback<Repositories> {
                override fun onResponse(
                    call: Call<Repositories>,
                    response: Response<Repositories>
                ) {
                    if (response.isSuccessful) {
                        if (restState) {
                            resetPage()
                        }

                        response.body()?.items?.let { item ->
                            repositoriesList.addAll(item)
                            notifyEvent.postValue(Unit)
                        }

                        isLoading.postValue(false)
                    } else {
                        isFocus.postValue(false)
                        errorEvent.postValue(ErrorMessage(errorCode = response.code()))
                        isLoading.postValue(false)
                    }
                }

                override fun onFailure(call: Call<Repositories>, t: Throwable) {
                    isFocus.postValue(false)
                    errorEvent.postValue(ErrorMessage())
                    isLoading.postValue(false)
                    Log.e("[API Error]", "${call.execute().errorBody()}")
                }
            })
    }

    fun resetPage() {
        repositoriesList.clear()
        currentPage.postValue(1)
        notifyEvent.postValue(Unit)
    }

    private fun setupEditTextError(word: String) {
        if (word.isNotEmpty() && word.isBlank()) {
            isShowError.postValue(word.isBlank())
            errorText.postValue(R.string.enter_empty_warning)
        } else {
            isShowError.postValue(word.length > 256)
            errorText.postValue(R.string.enter_over_warning)
        }
    }

    fun onScrollListener() = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            val position = (recyclerView.layoutManager as LinearLayoutManager)
                .findLastCompletelyVisibleItemPosition()

            if (newState == SCROLL_STATE_DRAGGING) isFocus.postValue(false)

            if (newState == SCROLL_STATE_IDLE && position > -1 && position == repositoriesList.lastIndex) {
                safeLet(edtSearch.value, currentPage.value) { word, page ->
                    searchRepositories(keyword = word, page = page + 1, restState = false)
                    currentPage.postValue(page + 1)
                }
            }
        }
    }

    fun getOnFocusChangeListener(): View.OnFocusChangeListener {
        return View.OnFocusChangeListener { _, hasFocus ->
            isFocus.value = hasFocus
        }
    }
}