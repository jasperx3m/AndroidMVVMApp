package com.example.mvvmapplication.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmapplication.data.db.AppDatabase
import com.example.mvvmapplication.data.db.entities.User
import com.example.mvvmapplication.data.network.LoginApi
import com.example.mvvmapplication.data.network.SafeApiRequest
import com.example.mvvmapplication.data.network.responses.AuthResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(
    private val api: LoginApi,
    private val db: AppDatabase
) : SafeApiRequest() {
    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest { api.userLogin(email, password) }
    }
    suspend fun userSignup(
        name: String,
        email: String,
        password: String
    ) : AuthResponse{
        return apiRequest {api.userSignup(name,email,password)}
    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getuser()


}

/*         val loginResponse = MutableLiveData<String>()
            return LoginApi().userLogin(email,password)
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
        return loginResponse*/ //change because call was changed to response
