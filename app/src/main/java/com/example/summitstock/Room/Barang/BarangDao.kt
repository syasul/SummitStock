package com.example.summitstock.Room.Barang

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
@Dao
interface BarangDao {
    @Insert
    suspend fun insertBarang(barang: Barang)

    @Query("SELECT * FROM barangs")
    suspend fun getAllBarang(): List<Barang>

    @Query("SELECT * FROM barangs WHERE id = :id")
    suspend fun getBarangById(id: Long): Barang

    @Update
    suspend fun updateBarang(barangs: Barang)

    @Delete
    suspend fun deleteBarang(barang: Barang)

    @Query("SELECT * FROM barangs WHERE namabarang LIKE '%' || :searchQuery || '%'")
    suspend fun searchBarang(searchQuery: String): List<Barang>
}