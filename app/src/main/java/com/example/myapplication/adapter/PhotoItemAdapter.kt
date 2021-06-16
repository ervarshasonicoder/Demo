package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.PhotosModel
import com.example.myapplication.R
import com.example.myapplication.databinding.RowImageItemBinding

class PhotoItemAdapter(var context: Context, var list: PhotosModel, var listner: itemClick) :
    RecyclerView.Adapter<PhotoItemAdapter.MyHolder>() {

//    var listner  =  context as itemClick

    class MyHolder(var binding: RowImageItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = RowImageItemBinding.inflate(
            LayoutInflater.from(parent.context)
            , parent, false
        )
        return MyHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.binding.tvDesc.text = list[position].title

        var myOptions : RequestOptions ? = RequestOptions()
            .override(80, 80)
        Glide.with(context)
            .load(list[position].url.toString()+".jpg")
            .dontAnimate()
            .dontTransform()
            .apply(myOptions!!)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .into(holder.binding.thumbnail)

        holder.itemView.setOnClickListener {
            listner.onClick(list[position])
        }

    }

    interface itemClick {
        fun onClick(data: PhotosModel.PhotosModelItem);
    }
}
