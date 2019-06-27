package com.android.koindemo.models.local

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userDetails")
data class UserDetails(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "email")
    val email: String = "",

    @ColumnInfo(name = "aboutUser")
    val aboutUser: String = ""
)