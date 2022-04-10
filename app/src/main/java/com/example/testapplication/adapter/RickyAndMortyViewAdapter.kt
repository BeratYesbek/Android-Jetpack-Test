package com.example.testapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.R
import com.example.testapplication.databinding.RickyAndMortyCardItemBinding
import com.example.testapplication.databinding.UserItemBinding
import com.example.testapplication.models.RickAndMorty
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class RickyAndMortyViewAdapter(private val rickyAndMortyList : ArrayList<RickAndMorty>) : RecyclerView.Adapter<RickyAndMortyViewAdapter.RickyAndMortViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RickyAndMortViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = DataBindingUtil.inflate<RickyAndMortyCardItemBinding>(inflater, R.layout.ricky_and_morty_card_item,parent,false)
        return RickyAndMortViewHolder(view)
    }

    override fun onBindViewHolder(holder: RickyAndMortViewHolder, position: Int) {
        holder.view.rickyAndMorty = rickyAndMortyList[position]
        CoroutineScope(Dispatchers.Main).launch {
            async {
                Picasso.get().load(rickyAndMortyList[position].download_url).into(holder.view.imageViewRickyAndMortyCardItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return rickyAndMortyList.size
    }

    class RickyAndMortViewHolder(var view : RickyAndMortyCardItemBinding) : RecyclerView.ViewHolder(view.root) {

    }
}