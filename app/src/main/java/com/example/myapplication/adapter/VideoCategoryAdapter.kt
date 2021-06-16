package com.stalwart.mybenefits.adapter

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.stalwart.mybenefits.model.CategoryModel

import java.util.ArrayList

class VideoCategoryAdapter(
    private val context: Context, var list: ArrayList<CategoryModel>
) :
    RecyclerView.Adapter<VideoCategoryAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_category_row, parent, false)

        return MyViewHolder(view)
    }

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model = list[position]
        holder.itemImage.setImageResource(list[position].image)

    }


    override fun getItemCount(): Int {
        return list.size
    }


    override fun getItemId(position: Int): Long {
        // return super.getItemId(position)
        return position.toLong()

    }

    override fun getItemViewType(position: Int): Int {
        //  return super.getItemViewType(position)
        return position
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val itemImage: ImageView

        init {

            itemImage = itemView.findViewById(R.id.itemImage)


        }
    }
}
