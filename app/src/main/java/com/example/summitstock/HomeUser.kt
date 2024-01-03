package com.example.summitstock

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.summitstock.Room.AppRepository
import com.example.summitstock.Room.BarangAdapterUser
import com.example.summitstock.Room.db.AppDatabase
import com.example.summitstock.Room.model.BarangViewModel
import com.example.summitstock.Room.model.BarangViewModelFactory
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch


class HomeUser : AppCompatActivity(), View.OnClickListener {

    private lateinit var buttonAdmin : ImageButton
    private lateinit var appRepository: AppRepository
    private lateinit var barangViewModel: BarangViewModel
    private lateinit var adapter: BarangAdapterUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_user)
        buttonAdmin = findViewById(R.id.buttonAdmin)
        buttonAdmin.setOnClickListener(this)

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

        val searchEditText: TextInputEditText = findViewById(R.id.txtSearch)
        val searchLayout: TextInputLayout = findViewById(R.id.layoutSearch)

        searchEditText.hint = "Cari"
        searchEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                searchEditText.hint = "Cari"
            } else {
                searchEditText.hint = "Cari"
            }
        }

        txtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Tidak diperlukan
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                lifecycleScope.launch {
                    // Panggil fungsi pencarian setiap kali ada perubahan teks
                    barangViewModel.searchBarang(this@HomeUser, s.toString())
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // Tidak diperlukan
            }
        })


        setupAdapter()
        getData()

    }

    private fun setupAdapter() {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewUser)
        adapter = BarangAdapterUser(emptyList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        barangViewModel.barangList.observe(this, Observer { barangList ->
            Log.d("HomeUser", "Setup Adapter - Data size: ${barangList.size}")
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                adapter.setData(barangList)
            }
        })
    }

    private fun getData() {
        barangViewModel.barangList.observe(this, Observer { barangList ->
            Log.d("HomeUser", "Get Data - Data size: ${barangList.size}")
            adapter.setData(barangList)
        })
    }



    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id) {
                R.id.buttonAdmin -> run {
                    val intentBiasa = Intent(this@HomeUser, MainActivity::class.java)
                    startActivity(intentBiasa)
                }
            }
        }
    }

}