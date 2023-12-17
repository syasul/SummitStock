package com.example.summitstock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.summitstock.Room.AppRepository
import com.example.summitstock.Room.User.AuthViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {


    private lateinit var authViewModel: AuthViewModel
    private lateinit var buttonLogin: Button
    private lateinit var usernameEditText: String
    private lateinit var passwordEditText: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id) {
                R.id.btn_reg-> run {
                    val intent = Intent(this,Register::class.java)
                    startActivity(intent)
                }
            }
        }



    }


}