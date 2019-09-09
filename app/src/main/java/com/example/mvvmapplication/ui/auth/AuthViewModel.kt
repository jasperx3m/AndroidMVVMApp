package com.example.mvvmapplication.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mvvmapplication.data.repositories.UserRepository
import com.example.mvvmapplication.ui.util.ApiException
import com.example.mvvmapplication.ui.util.Coroutines
import com.example.mvvmapplication.ui.util.NoInternetException

class AuthViewModel(private val repository: UserRepository) : ViewModel() {
    var name: String? = null
    var email: String? = null
    var password: String? = null
    var passwordConfirm: String? = null
    var authListener: AuthListener? = null

    fun getLoggedInUser() = repository.getUser()

    fun onSignup(view: View){
        Intent(view.context,SignUpActivity::class.java).also{
            view.context.startActivity(it)
        }
    }

    fun onLogin(view: View){
        Intent(view.context, LoginActivity::class.java).also{
            view.context.startActivity(it)
        }
    }

    fun onLoginButtonClick(view: View) {
        authListener?.onStarted()


        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid Email or Password")
            return
        }

        Coroutines.main {
            try {
                val authResponse = repository.userLogin(email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)

            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
            } catch (e: NoInternetException) {
                authListener?.onFailure(e.message!!)
            }
        }

    }

    fun onSignupButtonClick(view: View) {

        authListener?.onStarted()

        if (name.isNullOrEmpty()) {
            authListener?.onFailure("Name is Required")
        }
        if (email.isNullOrEmpty()) {
            authListener?.onFailure("Name is Required")
        }
        if (password.isNullOrEmpty()) {
            authListener?.onFailure("Name is Required")
        }
        if (password != passwordConfirm) {
            authListener?.onFailure("Password does not match")
        }
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Incomplete Fields")
            return
        }

        Coroutines.main {
            try {
                val authResponse = repository.userSignup(name!!, email!!, password!!)
                authResponse.user?.let {
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
            } catch (e: NoInternetException) {
                authListener?.onFailure(e.message!!)
            }
        }
    }
}


/*
val response = UserRepository().userLogin(email!!,password!!)
if(response.isSuccessful){
    authListener?.onSuccess(response.body()?.user!!)
}else{
    authListener?.onFailure("Error Code: ${response.code()}")
}*/
