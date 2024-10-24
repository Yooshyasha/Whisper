package com.yooshyasha.whisper.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yooshyasha.whisper.R
import com.yooshyasha.whisper.data.model.ChatDTO

class RecyclerHolder(view: View) : RecyclerView.ViewHolder(view) {

    @SuppressLint("SetTextI18n")
    fun bind(item: ChatDTO) {
        val vTitle = itemView.findViewById<TextView>(R.id.item_title)
        vTitle.text = item.chatId.toString()
    }

}


class RecyclerAdapter (val items: List<ChatDTO>) : RecyclerView.Adapter<RecyclerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_items, parent, false)

        return RecyclerHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }
}

