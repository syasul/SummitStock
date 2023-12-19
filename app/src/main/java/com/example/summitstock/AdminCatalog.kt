package com.example.summitstock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import com.google.android.material.textfield.TextInputEditText

class AdminCatalog : AppCompatActivity(), View.OnClickListener {

    private lateinit var buttonAdmin : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_catalog)

        val editText: TextInputEditText = findViewById(R.id.txtSearch)


        editText.hint = "Ini adalah hint text"
        editText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                // Fokus diperoleh, atur hint sesuai kebutuhan
                editText.hint = "Cari..."
            } else {
                // Fokus kehilangan, atur hint kembali ke teks asli
                editText.hint = "Ini adalah hint text"
            }


        }

//        button
        buttonAdmin = findViewById(R.id.buttonAdmin)
        buttonAdmin.setOnClickListener(this)

    }
    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id) {
                R.id.buttonAdmin -> run {
                    val intentBiasa = Intent(this@AdminCatalog, Login::class.java)
                    startActivity(intentBiasa)
                }
            }
        }
    }


}