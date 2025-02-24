package com.example.newstesting.repository

import androidx.paging.PagingData
import com.example.newstesting.data.model.ApiArticle
import kotlinx.coroutines.flow.Flow


interface NewsRepository {
    fun getNews(): Flow<PagingData<ApiArticle>>
}


//without Paging
//class NewsRepository @Inject constructor(
//    private val apiService: ApiService
//) {
//    suspend fun getNews(): Response<NewsResponse> {
//        return apiService.getNews()
//    }
//}

//without DI
//class NewsRepository @Inject constructor() {
//    suspend fun getNews(): Response<NewsResponse> {
//        return RetrofitInstance.api.getNews()
//    }
//}