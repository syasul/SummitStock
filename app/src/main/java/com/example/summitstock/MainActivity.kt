package com.example.summitstock

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity(), View.OnClickListener {


    private lateinit var buttonLogin: Button
    private lateinit var buttonUser: Button
    private lateinit var usernameEditText: String
    private lateinit var passwordEditText: String




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonLogin = findViewById(R.id.buttonLogin)
        buttonLogin.setOnClickListener(this)

        buttonUser = findViewById(R.id.buttonUser)
        buttonUser.setOnClickListener(this)


    }




    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id) {
                R.id.buttonLogin-> run {
                    val intent = Intent(this@MainActivity,Login::class.java)
                    startActivity(intent)
                }
            }
            when(v.id) {
                R.id.buttonUser-> run {
                    val intent = Intent(this,HomeUser::class.java)
                    startActivity(intent)
                }
            }
        }



    }






}