package com.example.searchdemo.data.repository

import com.example.searchdemo.api.SearchServices
import com.example.searchdemo.data.Repositories
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class SearchRepository(private val services: SearchServices) {
    fun getRepositories(keyword: String, page: Int): Flow<Response<Repositories>> {
        return flow {
            emit(services.getRepositories(keyword = keyword, page = page))
        }
    }
}