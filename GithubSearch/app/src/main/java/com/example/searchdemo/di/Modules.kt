package com.example.searchdemo.di

import com.example.searchdemo.api.Config
import com.example.searchdemo.api.SearchServices
import com.example.searchdemo.common.utils.AppSchedulerProvider
import com.example.searchdemo.common.utils.LoggerInterceptor
import com.example.searchdemo.common.utils.SchedulerProvider
import com.example.searchdemo.repository.SearchRepository
import com.example.searchdemo.ui.search.SearchViewModel
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val viewModelModule = module {
    viewModel { SearchViewModel(get(), get()) }
}

val networkModule = module {
    single { createOkHttpClient() }
    single { createService<SearchServices>(get(), Config.URL) }
}

val schedulerModule = module {
    single { AppSchedulerProvider() as SchedulerProvider }
}

val repositoryModule = module {
    single { SearchRepository(get()) }
}

val diModules = listOf(viewModelModule, networkModule, schedulerModule, repositoryModule)

//TODO: Need to do mockWebServer

fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .retryOnConnectionFailure(true)
        .addNetworkInterceptor(LoggerInterceptor())
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .connectionPool(ConnectionPool(0, 1, TimeUnit.NANOSECONDS))
        .build()
}


inline fun <reified T> createService(okHttpClient: OkHttpClient, serverUrl: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(serverUrl)                                     //設定請求URL
        .client(okHttpClient)                                   //設定OkHttp攔截器
        .addConverterFactory(GsonConverterFactory.create())     //設定解析工具，這裡使用Gson解析，你也可以使用Jackson等
        .build()

    return retrofit.create(T::class.java)
}