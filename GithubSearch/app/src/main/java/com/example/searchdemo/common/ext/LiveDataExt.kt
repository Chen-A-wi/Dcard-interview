package com.example.searchdemo.common.ext

import androidx.lifecycle.MediatorLiveData

fun <T : Any?> MediatorLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }
