package com.example.searchdemo.ui.search

import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.searchdemo.R
import com.example.searchdemo.common.ext.default
import com.example.searchdemo.common.utils.SchedulerProvider
import com.example.searchdemo.common.utils.SimplyTextWatcher
import com.example.searchdemo.common.utils.SingleLiveEvent
import com.example.searchdemo.data.Item
import com.example.searchdemo.data.NotifyRange
import com.example.searchdemo.data.repository.SearchRepository
import com.example.searchdemo.ui.base.BaseViewModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: SearchRepository,
    val scheduler: SchedulerProvider
) : BaseViewModel() {
    var repositoriesList = arrayListOf<Item>()
    val notifyEvent by lazy { MutableLiveData<NotifyRange>() }
    val clickLiveEvent by lazy { SingleLiveEvent<Int>() }
    private val currentPage by lazy { MediatorLiveData<Int>().default(1) }
    val edtSearch by lazy { MediatorLiveData<String>().default("") }
    val errorText = MutableLiveData(R.string.empty)
    val isShowError = MutableLiveData<Boolean>()
    val isShowClear = MediatorLiveData<Boolean>()

    fun onClick(v: View) {
        clickLiveEvent.postValue(v.id)
    }

    fun searchRepositories(keyword: String, page: Int, restState: Boolean) {
        isLoading.postValue(true)
        viewModelScope.launch {
            repository.getRepositories(keyword = keyword, page = page)
                .flowOn(scheduler.io())
                .catch { e ->
                    //TODO: Jump Error Dialog
                    Log.e("API Error",e.message.orEmpty())
                    isLoading.postValue(false)
                }
                .collectLatest { result ->
                    if (restState) {
                        resetPage()
                    }

                    result.items?.let { item ->
                        repositoriesList.addAll(item)
                    }

                    notifyEvent.postValue(
                        NotifyRange(
                            StartPage = repositoriesList.size - (result.items?.size ?: 0),
                            EndPage = repositoriesList.size
                        )
                    )
                    isLoading.postValue(false)
                }
        }
    }

    private fun resetPage() {
        repositoriesList.clear()
        currentPage.postValue(1)
    }

    fun EditText.onTextChangedFlow() = callbackFlow {
        val watcher = object : SimplyTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                s?.let { word ->
                    isShowClear.postValue(word.isNotEmpty())
                    setupEditTextError(word)
                    trySend(word).onFailure { e -> e?.printStackTrace() }
                }
            }
        }

        addTextChangedListener(watcher)
        awaitClose { removeTextChangedListener(watcher) }
    }

    private fun setupEditTextError(word: Editable) {
        if (word.isNotEmpty() && word.isBlank()) {
            isShowError.postValue(word.isBlank())
            errorText.postValue(R.string.enter_empty_warning)
        } else {
            isShowError.postValue(word.length > 256)
            errorText.postValue(R.string.enter_over_warning)
        }
    }
}