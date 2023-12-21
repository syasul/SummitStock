package com.example.summitstock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText

class AdminCatalog : AppCompatActivity(), View.OnClickListener {

    private lateinit var buttonAdmin : ImageButton
    private lateinit var updateButton: ImageButton



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



        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            val bottomSheetFragment = MyBottomSheetFragment()
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)

        }

        // Inside your activity or fragment
        val bottomSheetFragment = MyBottomSheetFragment()
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)



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