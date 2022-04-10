package com.example.testapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.adapter.PlatformViewAdapter
import com.example.testapplication.models.Platform
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList
import kotlin.time.Duration.Companion.days

class MainActivity : AppCompatActivity() {


    private lateinit var recyclerView : RecyclerView
    private lateinit var platformViewAdapter : PlatformViewAdapter
    private val platforms = ArrayList<Platform>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        recyclerView = findViewById(R.id.platformRecyclerView)

        val platform1 = Platform("Asp.Net Core 6.0","Free","Microsoft","https://i.pinimg.com/564x/ce/af/83/ceaf8384322af790486cff176a0a2f24.jpg","27-12-2015")
        val platform2 = Platform("Ruby on Rails","Free","Community","https://rubyonrails.org/assets/images/opengraph.png","25-02-2009")
        val platform3 = Platform("Docker","Free","Docker","https://logos-world.net/wp-content/uploads/2021/02/Docker-Symbol.png","22-12-2018")
        val platform4 = Platform("React","Free","Facebook","https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/React-icon.svg/2300px-React-icon.svg.png","27-12-2016")
        platforms.add(platform1)
        platforms.add(platform2)
        platforms.add(platform3)
        platforms.add(platform4)
        runRecyclerView()

        val intent = Intent(this,CountryActivity::class.java)
        startActivity(intent)
    }

    private fun runRecyclerView()
    {
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        platformViewAdapter = PlatformViewAdapter(platforms)
        recyclerView.adapter = platformViewAdapter
    }
}