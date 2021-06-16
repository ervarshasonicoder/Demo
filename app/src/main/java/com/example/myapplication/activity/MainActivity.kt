package com.example.myapplication.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.PhotosModel
import com.example.myapplication.PostsModel
import com.example.myapplication.R
import com.example.myapplication.Utils.UserSessionManager
import com.example.myapplication.Utils.Util
import com.example.myapplication.adapter.DrawerItemAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.fragment.ImageFragment
import com.example.myapplication.fragment.PostsFragment
import com.example.myapplication.restAPI.ApiClient
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), DrawerItemAdapter.OnItemClickListener {
    companion object {
        lateinit var postlist: PostsModel
        lateinit var photoslist: PhotosModel
    }

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()
        initdrawer()

    }

    @SuppressLint("WrongConstant")
    private fun initdrawer() {
        binding.customerNavigationRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.toolbar.setOnClickListener { binding.drawerLayout.openDrawer(Gravity.START) }
        var arrayList: ArrayList<String> = ArrayList<String>()

        arrayList.add("Home")
        arrayList.add("Dashboard")
        arrayList.add("Logout")

        val adapter = DrawerItemAdapter(this, arrayList)
        binding.customerNavigationRecyclerview.adapter = adapter
        if (UserSessionManager.getInstance(applicationContext).user != null){
            binding.header.useremail.text = UserSessionManager.getInstance(applicationContext).user
        }
    }


    fun init() {

        getPost()

        binding.viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))

        binding.tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager!!.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

    }

    private fun setStatePageAdapter() {
        var pagerAdapter = ViewPagerAdapter(supportFragmentManager)
        pagerAdapter!!.addFragment(ImageFragment(), "Photos")
        pagerAdapter!!.addFragment(PostsFragment(), "Post")
        binding.viewPager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager, true)

    }


    private fun getPost() {
        Util.showProgressDialog(applicationContext)
        ApiClient.getClient(this).getAllPost()
            .enqueue(object : Callback<PostsModel> {
                override fun onResponse(
                    call: Call<PostsModel>,
                    response: Response<PostsModel>
                ) {

                    if (response.isSuccessful) {

                        Log.e("postresponse", Gson().toJson(response.body()!!))

                        postlist = response.body()!!
                        getPhotos()

                    } else {

                        Util.dismissDialog()
                    }
                }

                override fun onFailure(call: Call<PostsModel>, error: Throwable) {
                    Log.e("fail", error.message.toString())
                    Util.dismissDialog()
                }
            })
    }

    private fun getPhotos() {
        Util.showProgressDialog(this)
        ApiClient.getClient(this).getAllPhotos()
            .enqueue(object : Callback<PhotosModel> {
                override fun onResponse(
                    call: Call<PhotosModel>,
                    response: Response<PhotosModel>
                ) {
                    Util.dismissDialog()

                    if (response.isSuccessful) {

//                        Log.e("postresponse",Gson().toJson(response.body()!!))

                        photoslist = response.body()!!
                        setStatePageAdapter()
                    }
                }

                override fun onFailure(call: Call<PhotosModel>, error: Throwable) {
                    Log.e("fail", error.message.toString())
                    Util.dismissDialog()
                }
            })
    }

    class ViewPagerAdapter(supportFragmentManager: FragmentManager) :
        FragmentStatePagerAdapter(supportFragmentManager) {

        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList.get(position)
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }
    }

    @SuppressLint("WrongConstant")
    override fun HomeonItemClick(item: String?, pos: Int) {
        when (pos) {
            0 -> {
                binding.drawerLayout.closeDrawer(Gravity.START)

            }
            1 -> {
                binding.drawerLayout.closeDrawer(Gravity.START)
                startActivity(Intent(applicationContext,DashboardActivity::class.java))

            }
            2 -> {
                binding.drawerLayout.closeDrawer(Gravity.START)
                if (UserSessionManager.getInstance(applicationContext).isLoggedIn) {
                    UserSessionManager.getInstance(applicationContext).logout()
                  startActivity(
                        Intent(
                            applicationContext,
                            LoginActivity::class.java
                        ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    )
                    finish()

                }
            }
        }
    }

}
