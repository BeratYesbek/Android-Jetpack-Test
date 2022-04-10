package com.example.testapplication.services.concretes

import com.example.testapplication.models.RickAndMorty
import com.example.testapplication.services.API
import com.example.testapplication.services.abstracts.IRickAndMortyService
import retrofit2.Call
import retrofit2.Response

class RickAndMortyService : IRickAndMortyService {

    private val api = API.api<IRickAndMortyService>("https://picsum.photos/")

    override fun getData(page : Int,limit: Int): Call<List<RickAndMorty>> {
        return api.getData(page,limit)
    }
}