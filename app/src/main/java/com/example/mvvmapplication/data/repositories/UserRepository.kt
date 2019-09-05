package com.example.mvvmapplication.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmapplication.data.network.LoginApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {
    fun userLogin(email: String, password : String) : LiveData<String>{
        val loginResponse = MutableLiveData<String>()

        LoginApi().userLogin(email, password)
            .enqueue(object: Callback<ResponseBody>{
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    loginResponse.value= t.message
                    Log.d("failure","onFail")
                }

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if(response.isSuccessful){
                        Log.d("successful", "success")
                        loginResponse.value=response.body()?.toString()
                    }else{
                        Log.d("failure","fail")
                        loginResponse.value=response.errorBody()?.toString()
                    }
                }

            })
        return loginResponse
    }
}