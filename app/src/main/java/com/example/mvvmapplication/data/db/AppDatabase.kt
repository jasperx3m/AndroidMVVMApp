package com.example.mvvmapplication.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmapplication.data.db.entities.User

@Database(
    entities =[User::class], // defining entities
    version = 1 // database version
)
abstract class AppDatabase : RoomDatabase(){

    abstract fun getUserDao() : UserDao

    companion object{
        @Volatile //this variable is visible to other threads
        private var instance : AppDatabase?=null
        private val LOCK = Any() //for prevent creating 2 instances of database

        operator fun invoke(context: Context) = instance?: synchronized(LOCK)  {//take context to create database, checks the context if null, create synchronized blockwith lock variable
            instance?:buildDatabase(context).also{//checks if instance is null then build database
                instance = it
            }
        }
        private fun buildDatabase(context : Context)=
            Room.databaseBuilder(
                context.applicationContext, //context
                AppDatabase::class.java, //
                "MyDatabase.db"
            ).build()
    }
}