package com.example.summitstock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class Register : AppCompatActivity(), View.OnClickListener {

    private lateinit var buttonRegister : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        buttonRegister = findViewById(R.id.buttonRegister)
        buttonRegister.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id) {
                R.id.buttonRegister -> run {
                    val intentBiasa = Intent(this@Register, HomeUser::class.java)
                    startActivity(intentBiasa)
                }
            }
        }

}}