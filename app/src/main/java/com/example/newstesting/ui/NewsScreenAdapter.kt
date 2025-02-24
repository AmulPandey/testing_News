package com.example.newstesting.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newstesting.R
import com.example.newstesting.data.model.ApiArticle

class NewsScreenAdapter(private val onItemClick: (ApiArticle) -> Unit) :
    PagingDataAdapter<ApiArticle, NewsScreenAdapter.NewsViewHolder>(ARTICLE_COMPARATOR) {

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.articleimage)
        val title: TextView = itemView.findViewById(R.id.articletext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = getItem(position)
        article?.let {
            Glide.with(holder.itemView.context).load(it.urlToImage).into(holder.image)
            holder.title.text = it.title

            holder.itemView.setOnClickListener {
                onItemClick(article)  // Call the click function with the clicked article
            }
        }
    }

    companion object {
        private val ARTICLE_COMPARATOR = object : DiffUtil.ItemCallback<ApiArticle>() {
            override fun areItemsTheSame(oldItem: ApiArticle, newItem: ApiArticle): Boolean {
                return oldItem.url == newItem.url
            }
            override fun areContentsTheSame(oldItem: ApiArticle, newItem: ApiArticle): Boolean {
                return oldItem == newItem
            }

        }
    }






}

//Without Using Paging normal RecylerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.newstesting.R
//import com.example.newstesting.data.model.ApiArticle
//
//
//class NewsScreenAdapter(private var articles: List<ApiArticle>) :
//    RecyclerView.Adapter<NewsScreenAdapter.NewsViewHolder>() {
//
//    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val image: ImageView = itemView.findViewById(R.id.articleimage)
//        val title: TextView = itemView.findViewById(R.id.articletext)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_article, parent, false)
//        return NewsViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
//        val article = articles[position]
//        Glide.with(holder.itemView.context).load(article.urlToImage).into(holder.image)
//        holder.title.text = article.title
//    }
//
//    override fun getItemCount(): Int = articles.size
//
//    fun updateData(newArticles: List<ApiArticle>) {
//        articles = newArticles
//        notifyDataSetChanged()
//    }
//}
