package com.example.mvvmapplication.ui.home.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmapplication.data.repositories.QuotesRepository
import com.example.mvvmapplication.data.repositories.UserRepository
import com.example.mvvmapplication.ui.home.profile.ProfileViewModel

@Suppress("UNCHECKED_CAST")
class QuotesViewModelFactory(
    private val repository : QuotesRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>) : T {
        return QuotesViewModel(repository) as T
    }

}