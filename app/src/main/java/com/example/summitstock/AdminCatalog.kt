package com.example.summitstock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.textfield.TextInputEditText

class AdminCatalog : AppCompatActivity() {
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

    }


}