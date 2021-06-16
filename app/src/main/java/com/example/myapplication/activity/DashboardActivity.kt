package com.example.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityDashboardBinding
import com.stalwart.mybenefits.adapter.CategoryAdapter
import com.stalwart.mybenefits.adapter.VideoCategoryAdapter
import com.stalwart.mybenefits.model.CategoryModel

class DashboardActivity : AppCompatActivity() {
    lateinit var binding : ActivityDashboardBinding
    var categoryList: ArrayList<CategoryModel> = ArrayList()
    var categoryAdapter: CategoryAdapter? = null
    var vcategoryAdapter: VideoCategoryAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding = DataBindingUtil.setContentView(this,R.layout.activity_dashboard)
        init()
    }
    fun init(){
        binding.recycleView!!.isNestedScrollingEnabled = false
        binding.recycleView!!.setHasFixedSize(false)
        categoryList.add(CategoryModel(R.drawable.image, "5 Minutes of daily Yoga,\nlifetime of Strength", false))
        categoryList.add(CategoryModel(R.drawable.image, "Muscle- Up your way to your Strong Self", true))
        categoryList.add(CategoryModel(R.drawable.image, "5 Minutes of daily Yoga,\nlifetime of Strength", false))
        categoryList.add(CategoryModel(R.drawable.image, "Muscle- Up your way to your Strong Self", true))
        categoryAdapter =
            CategoryAdapter(
                this,
                categoryList
            )

        var gridLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recycleView!!.layoutManager = gridLayoutManager

        binding.recycleView!!.adapter = categoryAdapter

      binding.recycleViewvideo!!.isNestedScrollingEnabled = false
        binding.recycleViewvideo!!.setHasFixedSize(false)
                vcategoryAdapter =
            VideoCategoryAdapter(
                this,
                categoryList
            )

        binding.recycleViewvideo!!.layoutManager= LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        binding.recycleViewvideo!!.adapter = vcategoryAdapter
    }
}