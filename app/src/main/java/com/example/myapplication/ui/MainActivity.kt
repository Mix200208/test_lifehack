package com.example.myapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.rv.ListCompanyAdapter
import com.example.myapplication.server.ServerService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val interceptor = run {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }

        val okHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .build()

        val api: ServerService = Retrofit.Builder().baseUrl("https://lifehack.studio/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ServerService::class.java)

        val adapter = ListCompanyAdapter(){
            val intent = Intent(this,DetailActivity::class.java)
            intent.putExtra("id",it.id)
            startActivity(intent)
        }

        GlobalScope.launch(Dispatchers.IO) {


            val response = api.getListCompany()

            GlobalScope.launch(Dispatchers.Main){
                binding.prBar.visibility = View.GONE
                adapter.setData(response.toMutableList())
                binding.companyList.visibility = View.VISIBLE
            }

        }

        binding.companyList.layoutManager = LinearLayoutManager(this)
        binding.companyList.adapter = adapter

    }
}