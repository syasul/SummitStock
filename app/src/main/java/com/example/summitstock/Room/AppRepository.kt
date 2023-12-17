package com.example.summitstock.Room

import com.example.summitstock.Room.Barang.Barang
import com.example.summitstock.Room.Barang.BarangDao
import com.example.summitstock.Room.User.User
import com.example.summitstock.Room.User.UserDao

class AppRepository(private val barangDao: BarangDao, private val userDao: UserDao) {
    suspend fun getAllBarang(): List<Barang> = barangDao.getAllBarang()

    suspend fun insertBarang(barang: Barang) = barangDao.insertBarang(barang)

    suspend fun updateBarang(barang: Barang) = barangDao.updateBarang(barang)

    suspend fun deleteBarang(barang: Barang) = barangDao.deleteBarang(barang)

    suspend fun searchBarang(query: String): List<Barang> = barangDao.searchBarang("%$query%")

    suspend fun login(username: String, password: String): User? = userDao.login(username, password)

    suspend fun register(user: User) = userDao.register(user)
}