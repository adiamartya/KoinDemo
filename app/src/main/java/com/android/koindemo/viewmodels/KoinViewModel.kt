package com.android.koindemo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.koindemo.models.local.User
import com.android.koindemo.repos.KoinRepo
import kotlinx.coroutines.launch

class KoinViewModel(private val koinRepo: KoinRepo) : ViewModel() {
    fun insertUser(user: User) {
        viewModelScope.launch {
            koinRepo.insertUser(user)
        }
    }

    fun getUsers() = koinRepo.getUsers()

    fun deleteUser(user: User) {
        viewModelScope.launch {
            koinRepo.deleteUser(user)
        }
    }

    fun deleteUserByEmail(email: String) {
        viewModelScope.launch {
            koinRepo.deleteUserByEmail(email)
        }
    }
}
