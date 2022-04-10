package com.example.testapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentCountryBinding
import com.example.testapplication.viewmodel.CountryViewModel

class CountryFragment : Fragment() {

    lateinit var binding : FragmentCountryBinding
    private var countryUuid = 0
    private val viewModel : CountryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryBinding.inflate(inflater)
        val view = binding.root

        arguments?.let {
            countryUuid = CountryFragmentArgs.fromBundle(it).countryUuid
        }
        observeLiveData()
        return view
    }

    private fun observeLiveData() {
        viewModel.getDataFromSqlLite(countryUuid)
        viewModel.countryLiveData.observe(viewLifecycleOwner) {
            it?.let {
                binding.country = it
            }
        }
    }

}