package com.android.koindemo.models.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.koindemo.models.local.UserDetails

@Dao
interface UserDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetails(userDetails: UserDetails)

    @Query("Select * from userDetails where email = :email")
    fun getUserDetails(email: String): LiveData<UserDetails>
}