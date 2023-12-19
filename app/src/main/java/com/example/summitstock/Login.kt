package com.example.summitstock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Login : AppCompatActivity(), View.OnClickListener {

    private lateinit var login: Button
    private lateinit var username: EditText
    private lateinit var password: EditText

    private val usernameAdmin = "admin"
    private val passwordAdmin = "admin123"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login = findViewById(R.id.Login)
        username = findViewById(R.id.Username)
        password = findViewById(R.id.Password)

        login.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.Login -> {
                    val enteredUsername = username.text.toString()
                    val enteredPassword = password.text.toString()

                    if (enteredUsername == usernameAdmin && enteredPassword == passwordAdmin) {
                        val intent = Intent(this, AdminCatalog::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Username/Password Salah", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
