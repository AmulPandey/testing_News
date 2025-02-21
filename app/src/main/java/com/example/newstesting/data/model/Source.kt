package com.example.newstesting.data.model

import com.google.gson.annotations.SerializedName

data class Source(

    @SerializedName("status")
     val status: String,

    @SerializedName("sources")
    val sources: List<ApiSource>

)
