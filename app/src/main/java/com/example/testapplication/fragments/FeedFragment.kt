package com.example.testapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapplication.adapter.CountryViewAdapter
import com.example.testapplication.databinding.FragmentFeedBinding
import com.example.testapplication.models.Country
import com.example.testapplication.utilities.ClickListener
import com.example.testapplication.viewmodel.FeedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : Fragment(),ClickListener {

    private lateinit var binding: FragmentFeedBinding
    private lateinit var adapter: CountryViewAdapter
    private val viewModel: FeedViewModel by viewModels()
    private val countries = ArrayList<Country>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFeedBinding.inflate(inflater)
        val view = binding.root

        runRecyclerView()
        getCountries()

        binding.swipeRefreshLayout.setOnRefreshListener {
            countries.clear()
            getCountries()

        }

        return view
    }

    private fun getCountries() {
        viewModel.refreshData()
        viewModel.countriesLiveData.observe(viewLifecycleOwner) { countryList ->
            countryList?.let {
                countries.addAll(countryList)
                adapter.notifyDataSetChanged()
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressBarCountryLoading.visibility = View.VISIBLE
            } else {
                binding.progressBarCountryLoading.visibility = View.INVISIBLE
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {
            if (it) {
                binding.textViewError.visibility = View.VISIBLE
            } else {
                binding.textViewError.visibility = View.INVISIBLE
            }
        }
    }

    private fun runRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerViewCountryList.layoutManager = layoutManager
        adapter = CountryViewAdapter(countries,this)
        binding.recyclerViewCountryList.adapter = adapter
    }



    override fun onClick(position: Int) {
        val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment()
        action.countryUuid = countries[position].uuid
        Navigation.findNavController(binding.root).navigate(action)
    }


}