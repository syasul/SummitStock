package com.example.summitstock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.summitstock.Room.User.AuthViewModel

class Login : AppCompatActivity(), View.OnClickListener {

    private lateinit var authViewModel: AuthViewModel
    private lateinit var buttonLogin: Button
    private lateinit var usernameEditText: String
    private lateinit var passwordEditText: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id) {
                R.id.buttonLogin-> run {
                    val intent = Intent(this,Login::class.java)
                    startActivity(intent)
                }
            }
        }



    }

}