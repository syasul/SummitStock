package com.example.summitstock.Room.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.summitstock.Room.model.Barang

@Dao
interface BarangDao {
    @Insert
    suspend fun insertBarang(barang: Barang)

    @Query("SELECT * FROM barangs")
    suspend fun getAllBarang(): List<Barang>

    @Query("SELECT * FROM barangs WHERE id = :id")
    suspend fun getBarangById(id: Long): Barang

    @Query("UPDATE barangs SET namabarang = :namaBarang, deskripsi = :deskripsi, stok = :stok, harga = :harga, image = :image WHERE id = :id")
    suspend fun updateBarangById(id: Long, namaBarang: String, deskripsi: String, stok: Int, harga: Int, image: String)
//    @Update
//    suspend fun updateBarang(barangs: Barang)

    @Query("DELETE FROM barangs WHERE id = :id")
    suspend fun deleteBarangById(id: Long)
//    @Delete
//    suspend fun deleteBarang(barang: Barang)

    @Query("SELECT * FROM barangs WHERE namabarang LIKE '%' || :searchQuery || '%'")
    suspend fun searchBarang(searchQuery: String): List<Barang>
}