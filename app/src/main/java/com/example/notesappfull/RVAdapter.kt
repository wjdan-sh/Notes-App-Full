package com.example.notesappfull

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*


class RVAdapter(
    private val activity: MainActivity, private val messages: ArrayList<String>): RecyclerView.Adapter<RVAdapter.MessageViewHolder>() {

    class MessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapter.MessageViewHolder {
        return RVAdapter.MessageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RVAdapter.MessageViewHolder, position: Int) {
        val message = messages[position]

        holder.itemView.apply {
            tvMessage.text = message
            if(position%2==0){llitem.setBackgroundColor(Color.parseColor("#A5D1D6"))}
            EditNote.setOnClickListener {
                activity.Dialog(message)
            }
            DeleteNote.setOnClickListener {
                activity.delete(message)
            }
        }
    }

    override fun getItemCount() = messages.size
}
