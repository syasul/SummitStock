package com.example.summitstock.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.summitstock.Room.Barang.Barang
import com.example.summitstock.Room.Barang.BarangDao

@Database(entities = [Barang::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun barangDao(): BarangDao


}