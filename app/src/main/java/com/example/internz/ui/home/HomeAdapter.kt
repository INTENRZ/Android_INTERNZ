package com.example.internz.ui.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.ui.profile.main.OtherProfileActivity
import com.example.internz.R
import com.example.internz.data.home.RecommData

class HomeAdapter(private val context : Context) : RecyclerView.Adapter<HomeViewHolder>() {

    var data = listOf<RecommData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.rv_recomm_profile_item, parent, false)
        return HomeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(data[position])

        holder.itemView.setOnClickListener{
            val intent = Intent(context, OtherProfileActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}