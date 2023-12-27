package com.example.summitstock

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.summitstock.Room.AppRepository
import com.example.summitstock.Room.BarangAdapter
import com.example.summitstock.Room.db.AppDatabase
import com.example.summitstock.Room.model.Barang
import com.example.summitstock.Room.model.BarangViewModel
import com.example.summitstock.Room.model.BarangViewModelFactory
//import com.google.android.ads.mediationtestsuite.viewmodels.ViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch

class AdminCatalog : AppCompatActivity(), View.OnClickListener, BarangAdapter.OnItemClickListener, BarangAdapter.OnClickDelete{
    private lateinit var buttonAdmin : ImageButton
    private lateinit var appRepository: AppRepository
    lateinit var barangViewModel: BarangViewModel
    lateinit var adapter: BarangAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_catalog)


        val layoutSearch: TextInputLayout = findViewById(R.id.layoutSearch)
        val txtSearch: TextInputEditText = findViewById(R.id.txtSearch)


        // Inisialisasi database
        val db = AppDatabase.invoke(this)

        // Inisialisasi BarangDao
        val barangDao = db.barangDao()

        // Inisialisasi AppRepository dengan BarangDao
        appRepository = AppRepository(barangDao)

        // Inisialisasi ViewModel dengan Factory
        barangViewModel = ViewModelProvider(this, BarangViewModelFactory(appRepository))
            .get(BarangViewModel::class.java)

        setupAdapter()
        getdata()

        val editText: TextInputEditText = findViewById(R.id.txtSearch)
        editText.hint = "Cari"
        editText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                editText.hint = "Cari"
            } else {
                editText.hint = "Cari"
            }
        }


//        button
        buttonAdmin = findViewById(R.id.buttonAdmin)
//        buttonAdmin.setOnClickListener(this)
        buttonAdmin.setOnClickListener {
            val intentBiasa = Intent(this@AdminCatalog, Login::class.java)
            startActivity(intentBiasa)
        }



        val fab1: FloatingActionButton = findViewById(R.id.fab)
        fab1.setOnClickListener {
            val bottomSheetFragment = BottomSheetAdd()
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }

        txtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Handle peristiwa pembaruan teks (misalnya, lakukan pencarian)
                performSearch(s.toString())
            }



            override fun afterTextChanged(s: Editable?) {
                // Not needed
            }
        })

        txtSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                // Handle aksi pencarian di sini
                performSearch(txtSearch.text.toString())
                return@setOnEditorActionListener true
            }
            false
        }




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
    fun setupAdapter(){
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        adapter = BarangAdapter(emptyList(), this, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
    fun getdata(){
        barangViewModel.barangList.observe(this, Observer { barangList ->
            // Handle the observed data, for example, update the adapter
            adapter.setData(barangList)
        })
    }

    override fun onItemClickUpdate(barang: Barang) {
        val bottomSheetFragment = BottomSheetUpdate.newInstance(barang)
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }

    private fun performSearch(query: String) {
        // Implementasikan logika pencarian di sini

        // Anda dapat menambahkan kriteria pencarian sesuai kebutuhan

        // Misalnya, tampilkan hasil pencarian atau lakukan operasi lainnya
        Toast.makeText(this, "Searching for: $query", Toast.LENGTH_SHORT).show()
    }

    override fun onItemClickDelete(id : Long) {
        lifecycleScope.launch {
            appRepository.deleteBarangById(id)
            barangViewModel.deleteBarang(id)
        }
    }


    // Observer untuk memperbarui tampilan setelah perubahan



}