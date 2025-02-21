package com.example.newstesting.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newstesting.data.model.ApiArticle
import com.example.newstesting.data.model.NewsResponse
import com.example.newstesting.network.ApiService
import com.example.newstesting.network.RetrofitInstance
import com.example.newstesting.paging.NewsPagingSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject


class NewsRepository @Inject constructor (private val apiService: ApiService) {

    fun getNews(): Flow<PagingData<ApiArticle>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { NewsPagingSource(apiService) }
        ).flow
    }
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