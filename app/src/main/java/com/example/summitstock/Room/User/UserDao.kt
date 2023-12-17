package com.example.summitstock.Room.User

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

interface UserDao {
        @Query("SELECT * FROM users WHERE username = :username AND password = :password")
        suspend fun login(username: String, password: String): User?

        @Insert
        suspend fun register(user: User)
}