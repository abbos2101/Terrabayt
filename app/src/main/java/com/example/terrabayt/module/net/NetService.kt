package com.example.module.data.net

import com.example.terrabayt.module.net.model.CategoryModelNet
import com.example.terrabayt.module.net.model.PostModelNet
import com.example.terrabayt.module.net.model.StatusModelNet
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NetService {
    @GET("api.php?action=categories")
    suspend fun getCategories(): Response<ArrayList<CategoryModelNet>>

    @GET("api.php?action=posts")
    suspend fun getPosts(
        @Query("first_update") first_update: Int = 0,
        @Query("last_update") last_update: Int = 0,
        @Query("category") category: Int = 0,
        @Query("limit") limit: Int = 30
    ): Response<ArrayList<PostModelNet>>

    @GET("api.php?action=status")
    suspend fun getStatus(@Query("time") time: Long): Response<StatusModelNet>
}