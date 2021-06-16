package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.PostsModel
import com.example.myapplication.databinding.RowItemBinding

class ListItemAdapter(context: Context, var list: PostsModel, var listner : itemClick) :
    RecyclerView.Adapter<ListItemAdapter.MyHolder>() {

//    var listner  =  context as itemClick

    class MyHolder(var binding: RowItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = RowItemBinding.inflate(
            LayoutInflater.from(parent.context)
            , parent, false
        )
        return MyHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.binding.tvTitle.text = list[position].title
        holder.binding.tvDesc.text = list[position].body

        holder.itemView.setOnClickListener {
            listner.onClick(list[position])
        }

    }

    interface  itemClick{
        fun onClick(data: PostsModel.PostsModelItem);
    }
}
