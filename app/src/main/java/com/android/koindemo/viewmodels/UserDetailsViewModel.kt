package com.android.koindemo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.koindemo.models.local.UserDetails
import com.android.koindemo.repos.UserDetailsRepo
import kotlinx.coroutines.launch

class UserDetailsViewModel(private val userDetailsRepo: UserDetailsRepo) : ViewModel() {
    var name: String? = ""
    fun insertUserDetails(userDetails: UserDetails) {
        viewModelScope.launch {
            userDetailsRepo.insertUserDetails(userDetails)
        }
    }

    fun getUserDetails(email: String) = userDetailsRepo.getUserDetails(email)
}