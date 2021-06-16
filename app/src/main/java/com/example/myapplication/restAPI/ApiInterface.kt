package  com.example.myapplication.restAPI


import com.example.myapplication.PhotosModel
import com.example.myapplication.PostsModel
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {

    @GET
    fun getAPICall(@Url url: String?): Call<JsonObject?>?

    @POST("login")
    fun login(@Body data: JsonObject?): Call<ResponseBody>


    @GET("posts")
    fun getAllPost(
    ): Call<PostsModel>

  @GET("photos")
    fun getAllPhotos(
    ): Call<PhotosModel>

    @GET("posts/{id}")
    fun getPost(
        @Path("id") id: Int
    ): Call<PostsModel.PostsModelItem>


}