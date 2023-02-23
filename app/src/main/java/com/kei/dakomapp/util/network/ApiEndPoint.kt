package com.kei.dakomapp.util.network

import com.kei.dakomapp.model.ResponseLectures
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndPoint {
    @GET("lecture/get")
    fun getData(): Call<ResponseLectures>

    @GET("lecture/get")
    fun getDataFav(): ResponseLectures

    @GET("lecture/search/name/{search}")
    fun searchItem(@Query("search") data: String?): Call<ResponseLectures?>?
}