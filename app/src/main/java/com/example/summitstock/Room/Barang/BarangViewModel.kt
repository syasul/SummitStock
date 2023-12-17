package com.example.summitstock.Room.Barang

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.summitstock.Room.AppRepository
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope

class BarangViewModel(private val repository: AppRepository) : ViewModel() {

    private val _allBarang = MutableLiveData<List<Barang>>()
    val allBarang: LiveData<List<Barang>> get() = _allBarang

    init {
        // Call getAllBarang() inside a coroutine when the ViewModel is created
        viewModelScope.launch {
            _allBarang.value = repository.getAllBarang()
        }
    }

    fun insertBarang(barang: Barang) {
        viewModelScope.launch {
            repository.insertBarang(barang)
        }
    }

    fun updateBarang(barang: Barang) {
        viewModelScope.launch {
            repository.updateBarang(barang)
        }
    }

    fun deleteBarang(barang: Barang) {
        viewModelScope.launch {
            repository.deleteBarang(barang)
        }
    }

    fun searchBarang(query: String) {
        viewModelScope.launch {
            repository.searchBarang(query)
        }
    }
}