package com.example.newstesting.data.model

import com.google.gson.annotations.SerializedName

data class ApiSource(

    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String
)
