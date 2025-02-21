package com.example.newstesting.network

import com.example.newstesting.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = "96d1a3df6f1c41e1aded45e8a91b776a",
        @Query("page") page: Int, // Added for paging
        @Query("pageSize") pageSize: Int = 20, // Default page size
    ): Response<NewsResponse>
}

//GET https://newsapi.org/v2/top-headlines?country=us&apiKey=96d1a3df6f1c41e1aded45e8a91b776a