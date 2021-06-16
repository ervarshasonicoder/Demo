package  com.example.myapplication.restAPI

import android.content.Context
import com.engear.vansales.restAPI.ConnectivityInterceptor
import com.example.myapplication.Utils.Constant.BASE_URL
import com.example.myapplication.Utils.Constant.BASE_URL_2
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiClient {

    private var retrofit: Retrofit? = null

    fun getClient(context: Context): ApiInterface {
        retrofit = null
        val client = OkHttpClient.Builder()
            .connectTimeout(30000, TimeUnit.SECONDS)
            .addInterceptor(ConnectivityInterceptor(context))
            .readTimeout(30000, TimeUnit.SECONDS).build()

        val gson = GsonBuilder()
            .setLenient()
            .create()
        if (retrofit == null) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging) // <-- this is the important line!
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL_2)
                //.addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
        }

        return retrofit!!.create(ApiInterface::class.java)
    }

    fun getClientLogin(context: Context): ApiInterface {

        val client = OkHttpClient.Builder()
            .connectTimeout(30000, TimeUnit.SECONDS)
            .addInterceptor(ConnectivityInterceptor(context))
            .readTimeout(30000, TimeUnit.SECONDS).build()

        val gson = GsonBuilder()
            .setLenient()
            .create()
        if (retrofit == null) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging) // <-- this is the important line!
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                //.addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
        }

        return retrofit!!.create(ApiInterface::class.java)
    }


}