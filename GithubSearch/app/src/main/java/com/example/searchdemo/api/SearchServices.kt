package com.example.searchdemo.api

import com.example.searchdemo.data.Repositories
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchServices {
    @GET("/search/repositories")
    suspend fun getRepositories(
        @Query("q") keyword: String,
        @Query("page") page: Int,
    ): Repositories
}