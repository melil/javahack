package com.example.javahack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class MainActivity : AppCompatActivity() {

    val BASE_URL = "https://umorili.herokuapp.com"
    var tv_user: TextView? = null
    var str:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_user = findViewById(R.id.tv_users)
        getUsers()

    }

    fun getUsers() {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(Api::class.java)
        val call = api.getData("bash", 99).enqueue(object : Callback<List<PostModel>> {
            override fun onResponse(
                call: Call<List<PostModel>>,
                response: Response<List<PostModel>>
            ) {
                var usres = response.body()
                var user = usres?.get(0)
                val size = usres!!.size

                for (i in 0 until size) {
                    str = str + "\n" + usres?.get(i)?.link
                }

                tv_user!!.text = str

                //tv_user!!.text = user?.name

            }

            override fun onFailure(call: Call<List<PostModel>>, t: Throwable) {

            }
        })



    }
}
