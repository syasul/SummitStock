package com.example.summitstock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnRegisterActivity: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnRegisterActivity = findViewById(R.id.btn_reg)
        btnRegisterActivity.setOnClickListener(this)


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
    private fun determineUserType(username: String): Boolean {
        return username=="Admin"
    }

}