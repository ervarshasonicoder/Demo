package com.example.myapplication


import com.google.gson.annotations.SerializedName

class PostsModel : ArrayList<PostsModel.PostsModelItem>(){
    data class PostsModelItem(
        @SerializedName("body")
        var body: String = "",
        @SerializedName("id")
        var id: Int = 0,
        @SerializedName("title")
        var title: String = "",
        @SerializedName("userId")
        var userId: Int = 0
    )
}