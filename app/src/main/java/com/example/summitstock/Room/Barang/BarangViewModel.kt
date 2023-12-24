package com.example.summitstock.Room.Barang

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.summitstock.Room.AppRepository
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BarangViewModel(private val repository: AppRepository) : ViewModel() {

    private val _allBarang = MutableLiveData<List<Barang>>()
    val allBarang: LiveData<List<Barang>> get() = _allBarang

    fun getAllBarang() {
        viewModelScope.launch {
            try {
                // Use withContext to switch to IO dispatcher for database operations
                val result = withContext(Dispatchers.IO) {
                    repository.getAllBarang()
                }

                // Use postValue to update LiveData on the main thread
                _allBarang.postValue(result)
            } catch (e: Exception) {
                // Handle exceptions if any
                e.printStackTrace()
            }
        }
    }

    fun insertBarang(barang: Barang) {
        viewModelScope.launch {
            // Use withContext to switch to IO dispatcher for database operations
            withContext(Dispatchers.IO) {
                repository.insertBarang(barang)
            }
        }
    }

    fun updateBarang(barang: Barang) {
        viewModelScope.launch {
            // Use withContext to switch to IO dispatcher for database operations
            withContext(Dispatchers.IO) {
                repository.updateBarang(barang)
            }
        }
    }

    fun deleteBarang(barang: Barang) {
        viewModelScope.launch {
            // Use withContext to switch to IO dispatcher for database operations
            withContext(Dispatchers.IO) {
                repository.deleteBarang(barang)
            }
        }
    }

    fun searchBarang(query: String) {
        viewModelScope.launch {
            // Use withContext to switch to IO dispatcher for database operations
            withContext(Dispatchers.IO) {
                repository.searchBarang(query)
            }
        }
    }
}