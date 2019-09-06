package com.example.mvvmapplication.ui.auth

import androidx.lifecycle.LiveData
import com.example.mvvmapplication.data.db.entities.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(user : User)
    fun onFailure(message : String)
}