package com.example.searchdemo.api

import com.example.searchdemo.data.Repositories
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchServices {
    @GET("/search/repositories")
    fun getRepositories(
        @Query("q") keyword: String,
        @Query("page") page: Int,
    ): Call<Repositories>
}