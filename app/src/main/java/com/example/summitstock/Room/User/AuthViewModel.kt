package com.example.summitstock.Room.User

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.summitstock.Room.AppRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class AuthViewModel(private val repository: AppRepository) : ViewModel() {

    private val adminUsername = "admin"
    private val adminPassword = "admin123"

    suspend fun login(username: String, password: String): User? {
        val admin = repository.login(adminUsername, adminPassword)

        return if (admin != null && admin.password == password) {
            admin
        } else {
            repository.login(username, password)
        }
    }

    fun register(user: User) {
        viewModelScope.launch {
            repository.register(user)
        }
    }
}