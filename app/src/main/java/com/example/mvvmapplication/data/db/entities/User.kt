package com.example.mvvmapplication.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_USER_ID = 0 // store authenticated/logged in user

@Entity //add (*tableName)
data class User(
    var id  : Int? = null,
    var name: String? = null,
    var email : String? = null,
    var password : String? = null,
    var email_verified_at : String? = null,
    var created_at : String? = null,
    var updated_at : String? = null

){
    @PrimaryKey(autoGenerate = false)
    var uid : Int = CURRENT_USER_ID
    // makes only 1 allowable user logged in locally
}