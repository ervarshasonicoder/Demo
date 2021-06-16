package com.example.myapplication.fragment

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.ListItemAdapter
import com.example.myapplication.PostsModel
import com.example.myapplication.R
import com.example.myapplication.Utils.Util
import com.example.myapplication.activity.MainActivity
import com.example.myapplication.databinding.FragmentImageBinding
import com.example.myapplication.restAPI.ApiClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PostsFragment : Fragment() , ListItemAdapter.itemClick{

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

        var adapter = ListItemAdapter(
            activity!!,
            MainActivity.postlist,
            this
        )
        binding.recyeclerview.adapter = adapter
    }
    override fun onClick(value: PostsModel.PostsModelItem) {
        Log.e(
            "value", value .id.toString()
        )


        getPostfromId(value.id)

    }

    private fun showViewPostDialog(body: PostsModel.PostsModelItem) {

        val dialog = Dialog(activity!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_view_post)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val metrics = resources.displayMetrics
        val screenWidth = (metrics.widthPixels * 0.95).toInt()
        dialog.window!!.setLayout(screenWidth, ViewGroup.LayoutParams.WRAP_CONTENT)
        val tvDesc = dialog.findViewById(R.id.tvDesc) as TextView
        val tvTitle = dialog.findViewById(R.id.tvTitle) as TextView
        tvDesc.text = body.body
        tvTitle.text = body.title
        dialog.show()
    }




    private fun getPostfromId(id: Int) {
        Util.showProgressDialog(activity!!)
        ApiClient.getClient(activity!!).getPost(id)
            .enqueue(object : Callback<PostsModel.PostsModelItem> {
                override fun onResponse(
                    call: Call<PostsModel.PostsModelItem>,
                    response: Response<PostsModel.PostsModelItem>
                ) {
                    Util.dismissDialog()

                    if (response.isSuccessful) {

                        Log.e("postresponse", Gson().toJson(response.body()!!))
                        showViewPostDialog(response.body()!!)

                    }
                }

                override fun onFailure(call: Call<PostsModel.PostsModelItem>, error: Throwable) {
                    Log.e("fail",error.message.toString())
                    Util.dismissDialog()
                }
            })
    }
}