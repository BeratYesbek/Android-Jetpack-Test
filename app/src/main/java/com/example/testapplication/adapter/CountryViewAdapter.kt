package com.example.testapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.R
import com.example.testapplication.databinding.CountryItemBinding
import com.example.testapplication.fragments.FeedFragmentDirections
import com.example.testapplication.models.Country
import com.example.testapplication.utilities.ClickListener
import com.example.testapplication.utilities.downloadedUrl
import com.example.testapplication.utilities.placeHolderProgressBar

class CountryViewAdapter(private val countryList: ArrayList<Country>,private val clickListener: ClickListener) :
    RecyclerView.Adapter<CountryViewAdapter.CountryViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = DataBindingUtil.inflate<CountryItemBinding>(
            inflater,
            R.layout.country_item,
            parent,
            false
        )
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.view.country = countryList[position]
        holder.view.imageViewCountryItem.downloadedUrl(
            countryList[position].countryFlag,
            placeHolderProgressBar(holder.view.root.context)
        )
        holder.view.imageViewCountryItem.setOnClickListener {
            clickListener.onClick(position)
        }
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    class CountryViewHolder(var view: CountryItemBinding) : RecyclerView.ViewHolder(view.root) {


    }



}