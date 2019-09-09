package com.example.mvvmapplication.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmapplication.data.db.entities.CURRENT_USER_ID
import com.example.mvvmapplication.data.db.entities.User

@Dao //Data Access Object
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) // if there is a conflict it overwrite current user
    suspend fun upsert(user : User) : Long //update or insert

    @Query("SELECT * FROM user WHERE uid = $CURRENT_USER_ID")
    fun getuser() : LiveData<User>

}