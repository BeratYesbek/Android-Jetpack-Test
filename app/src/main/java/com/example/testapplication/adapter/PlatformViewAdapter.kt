package com.example.testapplication.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.R
import com.example.testapplication.models.Platform

class PlatformViewAdapter(val platforms : ArrayList<Platform>) : RecyclerView.Adapter<PlatformViewAdapter.PlatformViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatformViewHolder {
        var context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return PlatformViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlatformViewHolder, position: Int) {
        holder.textViewPlatformDate.text = platforms[position].published_date
        holder.textViewPlatformName.text = platforms[position].name
        holder.textViewPlatformPrice.text = platforms[position].price
        holder.textViewPlatformPublisher.text = platforms[position].publisher

    }

    override fun getItemCount(): Int {
        return platforms.size
    }

    class PlatformViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageViewPlatformImage : ImageView = view.findViewById(R.id.platformImage);
        val textViewPlatformName : TextView = view.findViewById(R.id.platformName);
        val textViewPlatformPublisher : TextView = view.findViewById(R.id.platformPublisher);
        val textViewPlatformPrice : TextView = view.findViewById(R.id.platformPrice);
        val textViewPlatformDate : TextView = view.findViewById(R.id.platformPublishedDate);


    }
}