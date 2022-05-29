package com.example.searchdemo.common.utils

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class LoggerInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Log.d(
            "[API Request]",
            String.format(
                "%s %n%s%n%s", request.url, chain.connection(),
                request.headers
            )
        )
        return chain.proceed(request)
    }
}