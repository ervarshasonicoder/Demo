package com.example.myapplication.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.PhotosModel
import com.example.myapplication.R
import com.example.myapplication.activity.MainActivity
import com.example.myapplication.activity.ViewImageActivity
import com.example.myapplication.adapter.PhotoItemAdapter
import com.example.myapplication.databinding.FragmentImageBinding


class ImageFragment : Fragment(),PhotoItemAdapter.itemClick {

    lateinit var binding : FragmentImageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_image, container, false
        )
        setDataList()
        return binding.root
    }
    fun setDataList() {
        binding.recyeclerview.layoutManager =
            LinearLayoutManager(activity)

        var adapter = PhotoItemAdapter(activity!!, MainActivity.photoslist,this)
        binding.recyeclerview.adapter = adapter
    }

    override fun onClick(data: PhotosModel.PhotosModelItem) {

        var intent = Intent(activity,ViewImageActivity::class.java)
        intent.putExtra("url",data.url)
        startActivity(intent)

    }


}