package com.example.internz.ui.notification

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.notification.NotificationListData

class NotificationListAdapter (private val context : Context) : RecyclerView.Adapter<NotificationListViewHolder>() {

    var data =  listOf<NotificationListData>()
    var swipeController = SwipeController()
    var itemtouchHelper = ItemTouchHelper(swipeController)

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        itemtouchHelper.attachToRecyclerView(recyclerView)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationListViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.rv_notificationlist_item, parent, false)
        return NotificationListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: NotificationListViewHolder, position: Int) {
        holder.bind(data[position])
    }

}