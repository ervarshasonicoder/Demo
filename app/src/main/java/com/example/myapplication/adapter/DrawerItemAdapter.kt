package com.example.myapplication.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapter.DrawerItemAdapter.CustomViewHolder
import java.util.*

class DrawerItemAdapter(
    context: Activity,
    list: List<String>
) : RecyclerView.Adapter<CustomViewHolder>() {
    var context: Context
    var list: List<String> = ArrayList()

    interface OnItemClickListener {
        fun HomeonItemClick(item: String?, pos: Int)
    }

    // allows clicks events to be caught
    fun setClickListener(itemClickListener: OnItemClickListener?) {
        listener = itemClickListener
    }

    var listener: OnItemClickListener? = context as OnItemClickListener
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewtype: Int): CustomViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_drawer_layout, viewGroup, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class CustomViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var imageViewIcon: ImageView? = null
        var modeTv: ImageView? = null
        var transType: ImageView? = null
        var textViewName: TextView

        init {
            //            imageViewIcon = view.findViewById(R.id.imageViewIcon);
            textViewName = view.findViewById(R.id.textViewName)
        }
    }

    override fun onBindViewHolder(holder: CustomViewHolder, i: Int) {
        holder.textViewName.text = list[i]
        holder.itemView.setOnClickListener {
            if (listener != null) listener!!.HomeonItemClick(
                list[i], i
            )
        }
    }

    init {
        this.context = context
        this.list = list
    }
}