package com.example.mvvmapplication.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mvvmapplication.data.repositories.UserRepository
import com.example.mvvmapplication.ui.util.ApiException
import com.example.mvvmapplication.ui.util.Coroutines

class AuthViewModel : ViewModel(){
    var email : String? = null
    var password : String? = null
    var authListener : AuthListener? = null

    fun onLoginButtonClick(view: View){
        authListener?.onStarted()
        if (email.isNullOrEmpty()|| password.isNullOrEmpty()){
            authListener?.onFailure("Invalid Email or Password")
            return
        }
       /* val loginResponse =UserRepository().userLogin(email!!,password!!)
        authListener?.onSuccess(loginResponse)*/
        Coroutines.main{
            try{
                val authResponse = UserRepository().userLogin(email!!, password!!)
                authResponse.user?.let{
                    authListener?.onSuccess(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)

            }catch(e: ApiException){
                authListener?.onFailure(e.message!!)
            }
            /*
            val response = UserRepository().userLogin(email!!,password!!)
            if(response.isSuccessful){
                authListener?.onSuccess(response.body()?.user!!)
            }else{
                authListener?.onFailure("Error Code: ${response.code()}")
            }*/
        }

    }
}