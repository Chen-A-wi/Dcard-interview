package com.example.searchdemo.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchdemo.common.utils.SingleLiveEvent
import com.example.searchdemo.data.ErrorMessage

abstract class BaseViewModel : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val errorEvent by lazy { SingleLiveEvent<ErrorMessage>() }
}
