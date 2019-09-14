package com.example.javahack

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {
    @GET("/api/get")
    fun getData(@Query("name") resourceName: String, @Query("num") count: Int): Call<List<PostModel>>
}