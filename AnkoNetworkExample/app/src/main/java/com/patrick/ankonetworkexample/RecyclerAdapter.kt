package com.patrick.ankonetworkexample

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class RecyclerAdapter(val recyclerList: List<Int>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = recyclerList.count()

    override fun onBindViewHolder(parent: ViewHolder, position: Int) {
        parent.bind(recyclerList[position])
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val itemTextView: TextView = itemView.findViewById(R.id.recycler_row_text_view)
        fun bind(recyclerItemText: Int) {
            itemTextView.text = recyclerItemText.toString()
        }
    }
}