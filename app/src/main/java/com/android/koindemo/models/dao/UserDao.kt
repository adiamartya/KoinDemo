package com.android.koindemo.models.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.koindemo.models.local.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    @Delete
    suspend fun deleteUser(user: User): Int

    @Query("DELETE from users where email=:email")
    suspend fun deleteUserByEmail(email: String): Int

    @Query("Select * from users")
    fun getUsers(): LiveData<List<User>>

    @Update
    suspend fun updateUser(user: User)

    @Query("UPDATE users SET phoneNo = :phoneNo where email = :email")
    suspend fun updatePhoneNumber(phoneNo: String, email: String)

    @Query("UPDATE users SET name = :name where email = :email")
    suspend fun updateName(name: String, email: String)
}