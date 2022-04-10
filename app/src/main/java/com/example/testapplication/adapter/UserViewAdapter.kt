package com.example.testapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.R
import com.example.testapplication.databinding.UserItemBinding
import com.example.testapplication.models.User

class UserViewAdapter(private val users : ArrayList<User>) : RecyclerView.Adapter<UserViewAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = DataBindingUtil.inflate<UserItemBinding>(inflater, R.layout.user_item,parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

       holder.view.user = users[position]
    }
    override fun getItemCount(): Int {
        return users.size
    }
     class UserViewHolder(var view : UserItemBinding) : RecyclerView.ViewHolder(view.root)
    {

    }


}