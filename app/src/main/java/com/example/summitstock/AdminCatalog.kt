package com.example.summitstock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.summitstock.Room.AppRepository
import com.example.summitstock.Room.db.AppDatabase
import com.example.summitstock.Room.model.BarangViewModel
import com.example.summitstock.Room.model.BarangViewModelFactory
//import com.google.android.ads.mediationtestsuite.viewmodels.ViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText

class AdminCatalog : AppCompatActivity(), View.OnClickListener {

    private lateinit var buttonAdmin : ImageButton
    private lateinit var appRepository: AppRepository
    lateinit var barangViewModel: BarangViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_catalog)
        val db = AppDatabase.invoke(this)

        // Inisialisasi BarangDao
        val barangDao = db.barangDao()

        // Inisialisasi AppRepository dengan BarangDao
        appRepository = AppRepository(barangDao)

        // Inisialisasi ViewModel dengan Factory
        barangViewModel = ViewModelProvider(this, BarangViewModelFactory(appRepository))
            .get(BarangViewModel::class.java)
        val editText: TextInputEditText = findViewById(R.id.txtSearch)


        editText.hint = "Cari"
        editText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                // Fokus diperoleh, atur hint sesuai kebutuhan
                editText.hint = "Cari"
            } else {
                // Fokus kehilangan, atur hint kembali ke teks asli
                editText.hint = "Cari"
            }


        }

//        button
        buttonAdmin = findViewById(R.id.buttonAdmin)
        buttonAdmin.setOnClickListener(this)



        val fab1: FloatingActionButton = findViewById(R.id.fab)
        fab1.setOnClickListener {
            val bottomSheetFragment = BottomSheetAdd()
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }

//        val fab2: ImageButton = findViewById(R.id.fab2)
//        fab2.setOnClickListener {
//            val bottomSheetFragment = BottomSheetUpdate()
//            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
//        }

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