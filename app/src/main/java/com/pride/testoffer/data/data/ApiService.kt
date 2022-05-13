package com.pride.testofferwall.data

import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET("api/v1/entities/getAllIds")
    fun getId(): Call<AllIds>

    @GET
    fun getType(@Url url:String): Call<TypeWork>
}