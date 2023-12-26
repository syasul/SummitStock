package com.example.summitstock.Room.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.example.summitstock.Room.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BarangViewModel(private val repository: AppRepository) : ViewModel() {
    private val _barangList = MutableLiveData<List<Barang>>()
    val barangList: LiveData<List<Barang>> get() = _barangList

    init {
        // Load data barang pada inisialisasi
        getAllBarang()
    }

    fun getAllBarang() {
        viewModelScope.launch {
            _barangList.value = repository.getAllBarang()
        }
    }

    fun insertBarang(barang: Barang) {
        viewModelScope.launch {
            // Use withContext to switch to IO dispatcher for database operations
            withContext(Dispatchers.IO) {
                repository.insertBarang(barang)
                getAllBarang()
                Log.d("InsertData", "Data inserted successfully: $barang")
            }
        }
    }

    fun updateBarang(barang: Barang) {
        viewModelScope.launch {
            // Use withContext to switch to IO dispatcher for database operations
            withContext(Dispatchers.IO) {
                repository.updateBarang(barang)
                getAllBarang()
                Log.d("Update Data", "Data Update successfully: $barang")
            }
        }
    }

    fun deleteBarang(barang: Barang) {
        viewModelScope.launch {
            // Use withContext to switch to IO dispatcher for database operations
            withContext(Dispatchers.IO) {
                repository.deleteBarang(barang)
                getAllBarang()
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