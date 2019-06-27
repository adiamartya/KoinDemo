package com.android.koindemo

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.koindemo.models.dao.UserDao
import com.android.koindemo.models.dao.UserDetailsDao
import com.android.koindemo.models.local.User
import com.android.koindemo.models.local.UserDetails

@Database(entities = [User::class, UserDetails::class], version = 1)
abstract class KoinDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getUserDetailsDao(): UserDetailsDao
}