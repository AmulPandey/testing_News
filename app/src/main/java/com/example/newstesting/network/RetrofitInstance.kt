package com.example.newstesting.network


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

//GET https://newsapi.org/v2/top-headlines?country=us&apiKey=96d1a3df6f1c41e1aded45e8a91b776a

// if interceptor required need dependency too

//object RetrofitInstance {
//    private val logging = HttpLoggingInterceptor().apply {
//        level = HttpLoggingInterceptor.Level.BODY
//    }
//
//    private val client = OkHttpClient.Builder()
//        .addInterceptor(ApiKeyInterceptor())
//        .addInterceptor(logging)
//        .build()
//
//    val api: ApiService by lazy {
//        Retrofit.Builder()
//            .baseUrl("https://newsapi.org/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
//            .build()
//            .create(ApiService::class.java)
//    }
//}

//import okhttp3.Interceptor
//import okhttp3.HttpUrl
//import okhttp3.Request
//import okhttp3.Response
//import java.io.IOException
//import javax.inject.Inject
//import javax.inject.Singleton

//@Singleton
//class ApiKeyInterceptor @Inject constructor() : Interceptor {
//
//    @Throws(IOException::class)
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val originalRequest: Request = chain.request()
//        val originalHttpUrl: HttpUrl = originalRequest.url
//
//        val newHttpUrl = originalHttpUrl.newBuilder()
//            //.addQueryParameter("apiKey", "96d1a3df6f1c41e1aded45e8a91b776a")   already present in interface
//            .build()
//
//        val newRequest = originalRequest.newBuilder()
//            .url(newHttpUrl)
//            .build()
//
//        return chain.proceed(newRequest)
//    }
//}
