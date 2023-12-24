package com.example.summitstock.Room.Barang

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.summitstock.Room.AppRepository

class ViewModelFactory(private val repository: AppRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BarangViewModel::class.java)) {
            return BarangViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}