package com.example.myapplication.server

import com.example.myapplication.data.company.Company
import com.example.myapplication.data.companylist.CompanyList
import retrofit2.http.GET
import retrofit2.http.Query

interface ServerService {

    @GET(link)
    suspend fun getListCompany(): CompanyList

    @GET(link)
    suspend fun getCompany(
        @Query("id") id:Int
    ): Company

    companion object{
        const val link = "test_task/test.php"
    }
}