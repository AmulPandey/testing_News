package com.example.newstesting.data.model

import android.media.audiofx.AudioEffect.Descriptor
import com.google.gson.annotations.SerializedName

data class ApiArticle(

    @SerializedName("source")
    val source: Source,

    @SerializedName("author")
    val author: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val descripton: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("urlToImage")
    val urlToImage: String,

    @SerializedName("publishedAt")
    val publishedAt: String,

    @SerializedName("content")
    val content: String?
)
