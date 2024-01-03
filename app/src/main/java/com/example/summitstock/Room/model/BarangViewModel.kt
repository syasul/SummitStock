package com.example.summitstock.Room.model

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.example.summitstock.Helper.ToastUtils
import com.example.summitstock.Room.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BarangViewModel(private val repository: AppRepository) : ViewModel() {
    private var isResultEmpty: Boolean = false

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
                repository.updateBarangById(barang)
                getAllBarang()
                Log.d("Update Data", "Data Update successfully: $barang")
            }
        }
    }



    fun deleteBarang(id : Long) {
        viewModelScope.launch {
            // Use withContext to switch to IO dispatcher for database operations
            withContext(Dispatchers.IO) {
                repository.deleteBarangById(id)
                getAllBarang()
            }
        }
    }

     fun searchBarang(context: Context, query: String) {
        viewModelScope.launch {
            val searchResult = withContext(Dispatchers.IO) {
                if (query.isNotEmpty()) {
                    repository.searchBarang(query)
                } else {
                    repository.getAllBarang()
                }
            }

            isResultEmpty = searchResult.isEmpty()

            withContext(Dispatchers.Main) {
                _barangList.value = searchResult

                if (isResultEmpty) {
                    ToastUtils.showToast(context, "$query tidak ditemukan")

                }
            }
        }
    }



}