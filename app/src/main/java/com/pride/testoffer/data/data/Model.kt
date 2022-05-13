package com.pride.testoffer.data.data


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pride.testofferwall.data.AllIds
import com.pride.testofferwall.data.Repository
import com.pride.testofferwall.data.TypeWork
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Model : ViewModel() {
    var repo = Repository()
    var myIdList: MutableLiveData<AllIds> = MutableLiveData()
    var myTypeList: MutableLiveData<TypeWork> = MutableLiveData()
    fun getIds1() {

        repo.getIds().enqueue(object : Callback<AllIds> {
            override fun onResponse(call: Call<AllIds>, response: Response<AllIds>) {
               myIdList.setValue(response.body())

            }

            override fun onFailure(call: Call<AllIds>, t: Throwable) {
                t.printStackTrace()
            }

        })

    }
    fun getType1(url:String) {

        repo.getTypes(url).enqueue(object : Callback<TypeWork> {
            override fun onResponse(call: Call<TypeWork>, response: Response<TypeWork>) {
                myTypeList.setValue(response.body())

            }

            override fun onFailure(call: Call<TypeWork>, t: Throwable) {
                t.printStackTrace()
            }

        })

    }



}

