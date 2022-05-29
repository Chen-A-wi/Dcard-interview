package com.example.searchdemo.common.utils

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Coroutine Scheduler Provider
 */

interface SchedulerProvider {
    fun io(): CoroutineDispatcher
    fun ui(): CoroutineDispatcher
    fun default(): CoroutineDispatcher
    fun unConfirm(): CoroutineDispatcher
}