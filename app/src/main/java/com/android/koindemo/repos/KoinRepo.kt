package com.android.koindemo.repos

import com.android.koindemo.KoinDatabase
import com.android.koindemo.models.local.User

class KoinRepo(koinDatabase: KoinDatabase) {
    private val userDao = koinDatabase.getUserDao()

    suspend fun insertUser(user: User) = userDao.insertUser(user)

    fun getUsers() = userDao.getUsers()

    suspend fun deleteUser(user: User) = userDao.deleteUser(user)

    suspend fun deleteUserByEmail(email: String) = userDao.deleteUserByEmail(email)
}