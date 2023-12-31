package com.example.summitstock

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.summitstock.Room.model.Barang
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetAdd : BottomSheetDialogFragment() {
    //    image upload
    private val PICK_IMAGE_REQUEST = 1
    private var imageView: ImageView? = null
    private var selectedImageUri: Uri? = null

    //    increment decreement
    private var counterValue = 0
    private lateinit var counterTextView: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false)

        val submitButton: Button = view.findViewById(R.id.btnSubmit)
        submitButton.setOnClickListener {
            saveData()
        }
//        upload image
        imageView = view.findViewById(R.id.imageView)

        val uploadButton: Button = view.findViewById(R.id.uploadBtnAdd)
        uploadButton.setOnClickListener {
            openGallery()
        }

//        increement decreement
        counterTextView = view.findViewById(R.id.counterTextView)
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

    //    upload image
    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, selectedImageUri)
            imageView?.setImageBitmap(bitmap)

            // Get the path of the selected image
            val imagePath = getPathFromUri(requireContext(), selectedImageUri!!)
            // Now, you can use the imagePath as needed
        }
    }

    private fun getPathFromUri(context: Context, uri: Uri): String? {
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context.contentResolver.query(uri, filePathColumn, null, null, null)
        cursor?.moveToFirst()
        val columnIndex = cursor?.getColumnIndex(filePathColumn[0])
        val filePath = columnIndex?.let { cursor.getString(it) }
        cursor?.close()

        Log.d("ImagePath", "Path: $filePath")

        return filePath
    }


    //    increement decreement
    private fun incrementCounter() {
        counterValue++
        updateCounterText()
    }

    private fun decrementCounter() {
        if (counterValue > 0) {
            counterValue--
            updateCounterText()
        }
    }

    private fun updateCounterText() {
        counterTextView.text = counterValue.toString()
    }

    private fun saveData() {
        try {
            val namaBarang = view?.findViewById<EditText>(R.id.namaBarang)?.text.toString()
            val descBarang = view?.findViewById<EditText>(R.id.descBarang)?.text.toString()
            val jumlahStok = counterValue
            val hargaBarang = view?.findViewById<EditText>(R.id.hargaBarang)?.text.toString().toInt()

            val imagePath = selectedImageUri?.toString() ?: ""

            val barang = Barang(
                namabarang = namaBarang,
                deskripsi = descBarang,
                stok = jumlahStok,
                harga = hargaBarang,
                image = imagePath
            )

            // Retrieve existing ViewModel instance from the parent activity
            val activity = requireActivity() as AdminCatalog
            val barangViewModel = activity.barangViewModel

            // Use the existing barangViewModel instance to insert data
            barangViewModel.insertBarang(barang)

            dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("Crash", "Terjadi kesalahan: ${e.message}")
        }
    }

}