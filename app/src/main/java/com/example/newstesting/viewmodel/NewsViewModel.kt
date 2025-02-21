package com.example.newstesting.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newstesting.data.model.ApiArticle
import com.example.newstesting.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {

    fun getNews(): Flow<PagingData<ApiArticle>> {
        return repository.getNews().cachedIn(viewModelScope)
    }


     //shorter version
     //val news: Flow<PagingData<ApiArticle>> = repository.getNews().cachedIn(viewModelScope)
}

//Without Paging
//@HiltViewModel
//class NewsViewModel @Inject constructor(
//    private val repository: NewsRepository
//) : ViewModel() {
//
//    private val _news = MutableLiveData<List<ApiArticle>>()
//    val news: LiveData<List<ApiArticle>> = _news
//
//    private val _errorMessage = MutableLiveData<String>()
//    val errorMessage: LiveData<String> = _errorMessage
//
//    fun fetchNews() {
//        viewModelScope.launch {
//            try {
//                val response = repository.getNews()
//                if (response.isSuccessful && response.body() != null) {
//                    _news.postValue(response.body()!!.articles)
//                    Log.e("tag", "Data fetched")
//                } else {
//                    _errorMessage.postValue("Failed to fetch news")
//                    Log.e("tag", "Failed to fetch")
//                }
//            } catch (e: Exception) {
//                _errorMessage.postValue(e.message)
//            }
//        }
//    }
//}
