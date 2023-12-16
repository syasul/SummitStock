package com.example.summitstock.Room.User

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

interface UserDao {
        @Insert
        suspend fun insertUser(user: User)

        @Query("SELECT * FROM users WHERE username = :username AND password = :password")
        suspend fun loginUser(username: String, password: String): User?

        @Query("SELECT * FROM users WHERE role = :role")
        suspend fun getUsersByRole(role: String): List<User>
}