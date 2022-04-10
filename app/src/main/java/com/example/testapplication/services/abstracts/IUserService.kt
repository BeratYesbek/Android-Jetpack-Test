package com.example.testapplication.services.abstracts

import com.example.testapplication.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IUserService {
    @GET("users/")
    fun getAll() : Call<List<User>>
}