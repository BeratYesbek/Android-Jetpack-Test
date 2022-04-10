package com.example.testapplication.services.concretes

import com.example.testapplication.models.User
import com.example.testapplication.services.API
import com.example.testapplication.services.abstracts.IUserService
import retrofit2.Call

class UserService : IUserService {

    private val api = API.api<IUserService>("")

    override fun getAll(): Call<List<User>> {
        return api.getAll()
    }
}