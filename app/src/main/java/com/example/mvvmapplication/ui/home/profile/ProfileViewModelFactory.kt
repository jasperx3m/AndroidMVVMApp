package com.example.mvvmapplication.ui.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmapplication.data.repositories.UserRepository


@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory(
    private val repository: UserRepository
) : ViewModelProvider.NewInstanceFactory(){
    override fun <T: ViewModel?>create(modelClass: Class<T>) : T{
        return ProfileViewModel(repository) as T
    }
}