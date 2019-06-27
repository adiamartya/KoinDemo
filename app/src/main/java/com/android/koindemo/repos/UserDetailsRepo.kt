package com.android.koindemo.repos

import com.android.koindemo.KoinDatabase
import com.android.koindemo.models.local.UserDetails

class UserDetailsRepo(database: KoinDatabase) {
    private val userDetailsDao = database.getUserDetailsDao()
    suspend fun insertUserDetails(userDetails: UserDetails) {
        userDetailsDao.insertDetails(userDetails)
    }

    fun getUserDetails(email: String) = userDetailsDao.getUserDetails(email)
}