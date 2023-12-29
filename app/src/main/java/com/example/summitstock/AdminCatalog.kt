package com.example.summitstock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ImageButton
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.summitstock.Room.AppRepository
import com.example.summitstock.Room.BarangAdapterAdmin
import com.example.summitstock.Room.db.AppDatabase
import com.example.summitstock.Room.model.Barang
import com.example.summitstock.Room.model.BarangViewModel
import com.example.summitstock.Room.model.BarangViewModelFactory
//import com.google.android.ads.mediationtestsuite.viewmodels.ViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch

class AdminCatalog : AppCompatActivity(), View.OnClickListener, BarangAdapterAdmin.OnItemClickListener, BarangAdapterAdmin.OnClickDelete{
    private lateinit var buttonAdmin : ImageButton
    private lateinit var appRepository: AppRepository
    lateinit var barangViewModel: BarangViewModel
    lateinit var adapter: BarangAdapterAdmin


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


//        search
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
                    barangViewModel.searchBarang(s.toString())
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // Tidak diperlukan
            }
        })




//        button
        buttonAdmin = findViewById(R.id.buttonAdmin)
//        buttonAdmin.setOnClickListener(this)
        buttonAdmin.setOnClickListener {
            val intentBiasa = Intent(this@AdminCatalog, MainActivity::class.java)
            startActivity(intentBiasa)
        }



        val fab1: FloatingActionButton = findViewById(R.id.fab)
        fab1.setOnClickListener {
            val bottomSheetFragment = BottomSheetAdd()
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }
    }
    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id) {
                R.id.buttonAdmin -> run {
                    val intentBiasa = Intent(this@AdminCatalog, MainActivity ::class.java)
                    startActivity(intentBiasa)
                }
            }
        }
    }
    fun setupAdapter(){
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        adapter = BarangAdapterAdmin(emptyList(), this, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        barangViewModel.barangList.observe(this, Observer { barangList ->
            // Pastikan bahwa aktivitas atau fragmen masih hidup
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                // Update RecyclerView adapter with the filtered data
                adapter.setData(barangList)
                adapter.notifyDataSetChanged()  // Perbarui RecyclerView secara keseluruhan
            }
        })
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


    override fun onItemClickDelete(id : Long) {
        lifecycleScope.launch {
            appRepository.deleteBarangById(id)
            barangViewModel.deleteBarang(id)
        }
    }
}