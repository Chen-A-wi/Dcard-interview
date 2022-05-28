package com.example.searchdemo.common.ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun <T : Any?> MediatorLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }

infix fun <T> LiveData<T>.observeTo(owner: LifecycleOwner) = observe(owner) {}

infix fun <T> LiveData<T>.removeObserverFrom(owner: LifecycleOwner) = removeObservers(owner)