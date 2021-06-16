package com.example.myapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.myapplication.PostsModel
import com.example.myapplication.R
import com.example.myapplication.Utils.UserSessionManager
import com.example.myapplication.Utils.Util
import com.example.myapplication.databinding.ActivityLoaginBinding
import com.example.myapplication.restAPI.ApiClient
import com.google.gson.Gson
import com.google.gson.JsonIOException
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoaginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_loagin)
        if (UserSessionManager.getInstance(applicationContext).isLoggedIn){
            var intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        init()

    }

    private fun init() {
        binding.buttonLogin.setOnClickListener {
            if (validate()) {
                postLogin()
            }
        }
    }

    private fun validate(): Boolean {

        if (binding.etUsername.text.toString().isEmpty()) {
            Toast.makeText(applicationContext, "Please enter email Id", Toast.LENGTH_SHORT).show()
            return false
        } else
            if (binding.etPassword.text.toString().isEmpty()) {
                Toast.makeText(applicationContext, "Please enter Password", Toast.LENGTH_SHORT)
                    .show()
                return false
            }
        return true
    }

    private fun postLogin() {
        Util.showProgressDialog(this)
        val jObj = JsonObject()
        try {
            jObj.addProperty("email", binding.etUsername.text.toString())
            jObj.addProperty("password", binding.etPassword.text.toString())
        } catch (e: JsonIOException) {
            e.printStackTrace()
        }
        ApiClient.getClientLogin(this).login(jObj)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {

                    if (response.isSuccessful) {
                        Util.dismissDialog()
                        Log.e("loginresponse", response.body()!!.string())
                        UserSessionManager.getInstance(applicationContext).setUserid(binding.etUsername.text.toString())
                        var intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                        finish()

                    } else {

                        Util.dismissDialog()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, error: Throwable) {
                    Log.e("fail", error.message.toString())
                    Util.dismissDialog()
                }
            })
    }
}