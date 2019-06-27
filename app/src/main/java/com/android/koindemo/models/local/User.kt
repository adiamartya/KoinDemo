package com.android.koindemo.models.local

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "email")
    val email: String = "",

    @ColumnInfo(name = "name")
    val name: String? = "",

    @ColumnInfo(name = "phoneNo")
    val phoneNo: String? = ""
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString()!!,
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(email)
        writeString(name)
        writeString(phoneNo)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(source: Parcel): User = User(source)
            override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
        }
    }
}