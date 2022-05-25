package com.example.searchdemo.common.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class AppSchedulerProvider : SchedulerProvider {
    override fun io(): CoroutineDispatcher = Dispatchers.IO
    override fun ui(): CoroutineDispatcher = Dispatchers.Main
    override fun default(): CoroutineDispatcher = Dispatchers.Default
    override fun unConfirm(): CoroutineDispatcher = Dispatchers.Unconfined
}