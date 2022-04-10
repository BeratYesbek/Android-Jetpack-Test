package com.example.testapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapplication.adapter.RickyAndMortyViewAdapter
import com.example.testapplication.databinding.ActivityRickAndMortyBinding
import com.example.testapplication.models.RickAndMorty
import com.example.testapplication.services.abstracts.IRickAndMortyService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.security.auth.callback.Callback

@AndroidEntryPoint
class RickAndMortyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRickAndMortyBinding
    private lateinit var adapter: RickyAndMortyViewAdapter
    private val rickAndMortyList = ArrayList<RickAndMorty>()

    @Inject
    lateinit var rickyAndMortyService: IRickAndMortyService
    private var page = 0
    private var limit = 10


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRickAndMortyBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        runRecyclerView()
        getData(page, limit)
        binding.progressBarRickAndMorty.visibility = View.INVISIBLE
        binding.nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == (v!!.getChildAt(0)?.measuredHeight?.minus(v.measuredHeight))) {
                page++
                binding.progressBarRickAndMorty.visibility = View.VISIBLE

                getData(page, limit)
            }
        })
    }

    private fun runRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        binding.rickyAndMortyRecyclerView.layoutManager = layoutManager
        adapter = RickyAndMortyViewAdapter(rickAndMortyList)
        binding.rickyAndMortyRecyclerView.adapter = adapter
    }

    private fun getData(page: Int, limit: Int) {
        rickyAndMortyService.getData(page,limit).enqueue(object : retrofit2.Callback<List<RickAndMorty>>{
            override fun onResponse(
                call: Call<List<RickAndMorty>>,
                response: Response<List<RickAndMorty>>
            ) {
                if (response.isSuccessful)
                {
                    rickAndMortyList.addAll(response.body()!!)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<RickAndMorty>>, t: Throwable) {
                println(t.message)
            }

        })
    }
}