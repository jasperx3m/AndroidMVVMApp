package com.example.mvvmapplication.data.network.responses

import com.example.mvvmapplication.data.db.entities.Quote

data class QuotesResponse(
    val isSuccesful : String,
    val quotes : List<Quote>
)