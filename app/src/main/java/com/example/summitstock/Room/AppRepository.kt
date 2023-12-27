package com.example.summitstock.Room

import com.example.summitstock.Room.db.BarangDao
import com.example.summitstock.Room.model.Barang

class AppRepository(private val barangDao: BarangDao) {
    suspend fun getAllBarang(): List<Barang> = barangDao.getAllBarang()

    suspend fun insertBarang(barang: Barang) = barangDao.insertBarang(barang)

    //suspend fun updateBarang(barang: Barang) = barangDao.updateBarang(barang)

    suspend fun updateBarangById(barang: Barang) {
        barangDao.updateBarangById(
            barang.id,
            barang.namabarang,
            barang.deskripsi,
            barang.stok,
            barang.harga,
            barang.image!!
        )
    }
    //    suspend fun deleteBarang(barang: Barang) = barangDao.deleteBarang(barang)
    suspend fun deleteBarangById(id: Long) {
        barangDao.deleteBarangById(id)
    }

    suspend fun searchBarang(query: String): List<Barang> = barangDao.searchBarang("%$query%")

}