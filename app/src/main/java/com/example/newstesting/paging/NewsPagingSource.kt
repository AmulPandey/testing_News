package com.example.newstesting.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newstesting.data.model.ApiArticle
import com.example.newstesting.network.ApiService
import retrofit2.HttpException
import java.io.IOException

class NewsPagingSource(private val apiService: ApiService) : PagingSource<Int, ApiArticle>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ApiArticle> {
        val page = params.key ?: 1 // Start from page 1

        return try {
            val response = apiService.getNews(page = page)
            if (response.isSuccessful && response.body() != null) {
                val articles = response.body()!!.articles
                LoadResult.Page(
                    data = articles,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (articles.isEmpty()) null else page + 1
                )
            } else {
                LoadResult.Error(Exception("Failed to load data"))
            }
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ApiArticle>): Int? {

        val anchorPosition = state.anchorPosition
        anchorPosition?.let {
            Log.d("Paging", "Anchor Position: $anchorPosition")
        }
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
