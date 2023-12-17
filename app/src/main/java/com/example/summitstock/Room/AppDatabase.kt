package com.example.summitstock.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.summitstock.Room.Barang.Barang
import com.example.summitstock.Room.Barang.BarangDao
import com.example.summitstock.Room.User.User
import com.example.summitstock.Room.User.UserDao

@Database(entities = [User::class, Barang::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun barangDao(): BarangDao


}