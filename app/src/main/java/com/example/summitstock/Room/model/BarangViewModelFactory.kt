package com.example.summitstock.Room.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.summitstock.Room.AppRepository

class BarangViewModelFactory(private val repository: AppRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BarangViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BarangViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}