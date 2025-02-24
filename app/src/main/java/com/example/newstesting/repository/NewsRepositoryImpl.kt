package com.example.newstesting.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newstesting.data.model.ApiArticle
import com.example.newstesting.network.ApiService
import com.example.newstesting.paging.NewsPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val apiService: ApiService) : NewsRepository {

    override fun getNews(): Flow<PagingData<ApiArticle>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { NewsPagingSource(apiService) }
        ).flow
    }
}
