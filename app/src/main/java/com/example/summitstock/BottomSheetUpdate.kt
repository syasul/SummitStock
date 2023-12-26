package com.example.summitstock

import android.app.Activity
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.summitstock.Room.model.Barang
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textview.MaterialTextView
import java.util.UUID

class BottomSheetUpdate(private val barang: Barang) : BottomSheetDialogFragment() {

    private val PICK_IMAGE_REQUEST = 1
    private var imageView: ImageView? = null
    private var selectedImageUri: Uri? = null
    private var counterValue = 0
    private lateinit var counterTextView: MaterialTextView


    companion object {
        fun newInstance(barang: Barang): BottomSheetUpdate {
            return BottomSheetUpdate(barang)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_sheet_update, container, false)

        val namaBarangEditText: EditText = view.findViewById(R.id.namaBarang)
        val descBarangEditText: EditText = view.findViewById(R.id.descBarang)
        val hargaBarangEditText: EditText = view.findViewById(R.id.hargaBarang)
        imageView = view.findViewById(R.id.imageView)

        // Set existing data to the views
        namaBarangEditText.setText(barang.namabarang)
        descBarangEditText.setText(barang.deskripsi)
        hargaBarangEditText.setText(barang.harga.toString())
//        counterValue = barang.stok
//        updateCounterText()

        val incrementButton: Button = view.findViewById(R.id.incrementButton)
        val decrementButton: Button = view.findViewById(R.id.decrementButton)

        incrementButton.setOnClickListener {
            incrementCounter()
        }

        decrementButton.setOnClickListener {
            decrementCounter()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi counterTextView di sini setelah onCreateView selesai dijalankan
        counterTextView = view.findViewById(R.id.counterTextView)

        counterValue = barang.stok

        // Update counterText setelah counterTextView dan counterValue diinisialisasi
        updateCounterText()

        val imageUri = Uri.parse(barang.image)
        if (imageUri != null) {
            imageView?.let {
                Glide.with(requireContext())
                    .load(imageUri)
                    .into(it)
            }
        }

        // ... kode lainnya
    }




    private fun incrementCounter() {
        if (::counterTextView.isInitialized) {
            counterValue++
            updateCounterText()
        }
    }

    private fun decrementCounter() {
        if (::counterTextView.isInitialized && counterValue > 0) {
            counterValue--
            updateCounterText()
        }
    }

    private fun updateCounterText() {
        if (::counterTextView.isInitialized) {
            counterTextView.text = counterValue.toString()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(
                requireContext().contentResolver,
                selectedImageUri
            )
            imageView?.setImageBitmap(bitmap)
        }
    }
}
