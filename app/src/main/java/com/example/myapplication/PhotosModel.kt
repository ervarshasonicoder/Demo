package com.example.myapplication


import com.google.gson.annotations.SerializedName

class PhotosModel : ArrayList<PhotosModel.PhotosModelItem>(){
    data class PhotosModelItem(
        @SerializedName("albumId")
        var albumId: Int = 0,
        @SerializedName("id")
        var id: Int = 0,
        @SerializedName("thumbnailUrl")
        var thumbnailUrl: String = "",
        @SerializedName("title")
        var title: String = "",
        @SerializedName("url")
        var url: String = ""
    )
}