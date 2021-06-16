package com.example.myapplication.activity

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.myapplication.PostsModel
import com.example.myapplication.R
import com.example.myapplication.Utils.Util
import com.example.myapplication.databinding.ActivityViewPostBinding
import com.example.myapplication.restAPI.ApiClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ViewImageActivity : AppCompatActivity() {
    lateinit var binding: ActivityViewPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_post)
        init()
    }

    fun init() {
        var url  = intent.getStringExtra("url")
        Glide.with(applicationContext)
            .load(url+".jpg")
            .dontAnimate()
            .dontTransform()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .into(binding.thumbnail)

    }





}
