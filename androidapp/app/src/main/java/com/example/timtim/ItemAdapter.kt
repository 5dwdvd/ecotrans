package com.example.timtim

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(
    private val items: List<Item>
) : RecyclerView.Adapter<ItemAdapter.InfoViewHolder>() {
    class InfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        return InfoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_view,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        val mainEvent = holder.itemView.findViewById<TextView>(R.id.tvEventName)
        val extraInfo = holder.itemView.findViewById<TextView>(R.id.info)
        val data = items[position]

        mainEvent.text = data.eventName
        extraInfo.text = data.eventLocation + " | " + data.eventName
    }

    override fun getItemCount(): Int {
        return items.size
    }
}