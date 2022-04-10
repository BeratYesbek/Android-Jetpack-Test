package com.example.testapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapplication.adapter.PlatformViewAdapter
import com.example.testapplication.adapter.UserViewAdapter
import com.example.testapplication.databinding.ActivityUsersBinding
import com.example.testapplication.models.User
import com.example.testapplication.services.abstracts.IUserService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.security.auth.callback.Callback

@AndroidEntryPoint
class UsersActivity : AppCompatActivity() {

    private val job: Job? = null
    private lateinit var binding: ActivityUsersBinding
    private val users = ArrayList<User>()
    private lateinit var usersAdapter : UserViewAdapter
    @Inject
    lateinit var userService: IUserService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        runRecyclerView()
        getUsers()
    }

    private fun runRecyclerView()
    {
        val layoutManager = LinearLayoutManager(this)
        binding.userRecyclerView.layoutManager = layoutManager
        usersAdapter = UserViewAdapter(users)
        binding.userRecyclerView.adapter = usersAdapter

    }

    private fun getUsers() {
        val call = userService.getAll()
        call.enqueue(object : retrofit2.Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    response.body()?.let { users.addAll(it) }
                    usersAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                println(t.message)
            }

        })

    }

}