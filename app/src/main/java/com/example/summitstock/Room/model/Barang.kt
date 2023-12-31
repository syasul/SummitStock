package com.example.summitstock.Room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "barangs")
data class Barang(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val namabarang: String,
    val deskripsi: String,
    val stok: Int,
    val harga: Int,
    val image: String?
)
