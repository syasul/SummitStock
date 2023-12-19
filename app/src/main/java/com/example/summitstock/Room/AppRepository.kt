package com.example.summitstock.Room

import com.example.summitstock.Room.Barang.Barang
import com.example.summitstock.Room.Barang.BarangDao

class AppRepository(private val barangDao: BarangDao) {
    suspend fun getAllBarang(): List<Barang> = barangDao.getAllBarang()

    suspend fun insertBarang(barang: Barang) = barangDao.insertBarang(barang)

    suspend fun updateBarang(barang: Barang) = barangDao.updateBarang(barang)

    suspend fun deleteBarang(barang: Barang) = barangDao.deleteBarang(barang)

    suspend fun searchBarang(query: String): List<Barang> = barangDao.searchBarang("%$query%")

}