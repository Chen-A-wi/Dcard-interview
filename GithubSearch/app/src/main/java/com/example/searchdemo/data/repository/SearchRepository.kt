package com.example.searchdemo.data.repository

import com.example.searchdemo.api.SearchServices
import com.example.searchdemo.data.Repositories
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRepository(private val services: SearchServices) {
    fun getRepositories(keyword: String, page: Int): Flow<Repositories> {
        return flow {
            emit(services.getRepositories(keyword = keyword, page = page))
        }
    }
}