package com.example.myapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityDetailBinding
import com.example.myapplication.rv.ListCompanyAdapter
import com.example.myapplication.server.Api
import com.example.myapplication.server.ServerService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDetailBinding = DataBindingUtil.setContentView(this,R.layout.activity_detail)

       val id = intent.extras!!["id"].toString().toInt()



        GlobalScope.launch(Dispatchers.IO) {


            val response = Api.api.getCompany(id)

            GlobalScope.launch(Dispatchers.Main){
                binding.prBar.visibility = View.GONE
                binding.lr.visibility = View.VISIBLE
                Log.i("info",response[0].description)
                binding.name.text = response[0].name
                binding.description.text = response[0].description
                binding.place.text = "Координаты \n\n ${response[0].lat} ${response[0].lon}"
                binding.website.text = "Сайт \n\n ${response[0].www}"
                binding.phone.text = "Телефон \n" + "\n ${response[0].phone}"
            }

        }

    }
}