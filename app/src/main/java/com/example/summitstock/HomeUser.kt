package com.example.summitstock

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity


class HomeUser : AppCompatActivity(), View.OnClickListener {

    private lateinit var buttonAdmin : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_user)
        buttonAdmin = findViewById(R.id.buttonAdmin)
        buttonAdmin.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id) {
                R.id.buttonAdmin -> run {
                    val intentBiasa = Intent(this@HomeUser, AdminCatalog::class.java)
                    startActivity(intentBiasa)
                }
            }
        }
    }

}