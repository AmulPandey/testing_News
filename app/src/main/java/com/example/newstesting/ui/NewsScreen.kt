package com.example.newstesting.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.paging.LoadState
import com.example.newstesting.R
import com.example.newstesting.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsScreen : AppCompatActivity() {
    private val newsViewModel: NewsViewModel by viewModels()
    private lateinit var newsAdapter: NewsScreenAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_news_screen)

        val recyclerView = findViewById<RecyclerView>(R.id.recycle)
        recyclerView.layoutManager = LinearLayoutManager(this)

        newsAdapter = NewsScreenAdapter { article ->
            val intent = Intent(this, NewsDetails::class.java)
            intent.putExtra("news_title", article.title)
            intent.putExtra("news_description", article.descripton)
            intent.putExtra("news_image", article.urlToImage)
            startActivity(intent)
        }


        recyclerView.adapter = newsAdapter

        lifecycleScope.launch {
            newsViewModel.getNews().collectLatest { pagingData ->
                newsAdapter.submitData(pagingData)
            }
        }

        // Handle LoadState to show errors or loading indicators
        newsAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Error) {
                val errorMessage = (loadState.refresh as LoadState.Error).error.localizedMessage
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        newsAdapter.addLoadStateListener { loadState ->
            progressBar.visibility = if (loadState.refresh is LoadState.Loading) View.VISIBLE else View.GONE
        }


        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
                Log.d("Paging", "First visible item position: $firstVisibleItem")
            }
        })


    }
}

//if using that shorter version from repo
//@AndroidEntryPoint
//class NewsScreen : AppCompatActivity() {
//    private val newsViewModel: NewsViewModel by viewModels()
//    private lateinit var newsAdapter: NewsScreenAdapter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_news_screen)
//
//        val recyclerView = findViewById<RecyclerView>(R.id.recycle)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        newsAdapter = NewsScreenAdapter()
//        recyclerView.adapter = newsAdapter
//
//        lifecycleScope.launch {
//            newsViewModel.news.collectLatest { pagingData ->
//                newsAdapter.submitData(pagingData)
//            }
//        }
//    }
//}


//without Paging
//import android.os.Bundle
//import android.widget.Toast
//import androidx.activity.enableEdgeToEdge
//import androidx.activity.viewModels
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import androidx.lifecycle.Observer
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.newstesting.R
//import com.example.newstesting.viewmodel.NewsViewModel
//import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
//class NewsScreen : AppCompatActivity() {
//    private val newsViewModel: NewsViewModel by viewModels()
//    private lateinit var newsAdapter: NewsScreenAdapter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_news_screen)
//
//        val recyclerView = findViewById<RecyclerView>(R.id.recycle)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        newsAdapter = NewsScreenAdapter(emptyList())
//        recyclerView.adapter = newsAdapter
//
//        newsViewModel.news.observe(this, Observer { articles ->
//            newsAdapter.updateData(articles)
//        })
//
//        newsViewModel.errorMessage.observe(this, Observer { message ->
//            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//        })
//
//        newsViewModel.fetchNews()
//
//
//    }
//}