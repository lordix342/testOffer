package com.pride.testofferwall.data

import retrofit2.Call
import retrofit2.Response

class Repository {
    fun getIds() : Call<AllIds> {
        return RertofitInstance.api.getId()
    }
    fun getTypes(url:String) : Call<TypeWork> {
        return RertofitInstance.api.getType(url)
    }
}