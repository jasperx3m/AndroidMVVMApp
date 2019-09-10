package com.example.mvvmapplication.ui.home.quotes

import androidx.lifecycle.ViewModel
import com.example.mvvmapplication.data.repositories.QuotesRepository
import com.example.mvvmapplication.ui.util.lazyDeferred

class QuotesViewModel(
    repository : QuotesRepository
) : ViewModel() {

    val quotes by lazyDeferred {
        repository.getQuotes()

    }


}
