package com.example.mvvmapplication.ui.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Coroutines{ //static = object
    fun main(work: suspend(() -> Unit)) =
        CoroutineScope(Dispatchers.Main).launch {
            work()
        } // learn higher order function and lambda
    fun io(work: suspend(() -> Unit))=
        CoroutineScope(Dispatchers.IO).launch{
            work()
        }
}