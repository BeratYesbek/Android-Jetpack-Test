package com.example.testapplication.services.abstracts

import com.example.testapplication.models.RickAndMorty
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IRickAndMortyService {
    @GET("v2/list")
    fun getData(@Query("page") page:Int, @Query("limit") limit: Int) : Call<List<RickAndMorty>>
}