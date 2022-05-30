package com.example.searchdemo.repository

import com.example.searchdemo.api.SearchServices
import com.example.searchdemo.data.Repositories
import retrofit2.Call

class SearchRepository(private val services: SearchServices) {
    fun getRepositories(keyword: String, page: Int): Call<Repositories> {
        return services.getRepositories(keyword = keyword, page = page)
    }
}