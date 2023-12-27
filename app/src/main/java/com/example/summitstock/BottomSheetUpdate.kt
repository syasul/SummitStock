package com.example.summitstock

import android.app.Activity
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
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
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import android.Manifest
import android.graphics.BitmapFactory
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
    var id : Long = 0
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

        val submitButton: Button = view.findViewById(R.id.btnSubmit)
        submitButton.setOnClickListener {
            updateData()
        }

        val uploadBtnUpdate: Button = view.findViewById(R.id.uploadBtn)
        uploadBtnUpdate.setOnClickListener {
            openGallery()
        }

        imageView = view.findViewById(R.id.imageView)

        // Set existing data to the views
        namaBarangEditText.setText(barang.namabarang)
        descBarangEditText.setText(barang.deskripsi)
        hargaBarangEditText.setText(barang.harga.toString())
        id = barang.id
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
        imageView = view.findViewById(R.id.imageView)
        val imageUri = Uri.parse(barang.image)

        if (imageUri != null) {
            try {
                val inputStream = requireContext().contentResolver.openInputStream(imageUri)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                imageView!!.setImageBitmap(bitmap)
//                imageView?.let {
//                    Glide.with(requireContext())
//                        .load(imageUri)
//                        .into(it)
//                }
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle exception
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


        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            data?.data?.also { uri ->
                val takeFlags: Int = data.flags and (Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                // Permintaan izin persisten untuk akses URI
                if (takeFlags and Intent.FLAG_GRANT_READ_URI_PERMISSION == Intent.FLAG_GRANT_READ_URI_PERMISSION) {
                    requireActivity().contentResolver.takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }

                if (takeFlags and Intent.FLAG_GRANT_WRITE_URI_PERMISSION == Intent.FLAG_GRANT_WRITE_URI_PERMISSION) {
                    requireActivity().contentResolver.takePersistableUriPermission(uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                }

                selectedImageUri = uri
                updateImageView()
            }
        }
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
//            selectedImageUri = data.data
//            val contentResolver = requireContext().contentResolver
//            imageView?.let {
//                Glide.with(requireContext())
//                    .load(selectedImageUri)
//                    .into(it)
//            }
//            selectedImageUri?.let { uri ->
//                Glide.with(requireContext())
//                    .load(uri)
//                    .into(imageView!!)
//            }
//            imageView?.setImageBitmap(bitmap)
//        }
    }
    private fun updateImageView() {
        selectedImageUri?.let { uri ->
            val inputStream = requireContext().contentResolver.openInputStream(uri)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            imageView!!.setImageBitmap(bitmap)
        }
        Log.d("URI Updated" , selectedImageUri.toString())
    }

    private fun updateData() {
        try {
            val namaBarang = view?.findViewById<EditText>(R.id.namaBarang)?.text.toString()
            val descBarang = view?.findViewById<EditText>(R.id.descBarang)?.text.toString()
            val jumlahStok = counterValue
            val hargaBarang = view?.findViewById<EditText>(R.id.hargaBarang)?.text.toString().toDouble()

            val imagePath = if (selectedImageUri != null) {
                selectedImageUri.toString().also {
                    Log.d("URI post for update", it)
                }
            } else {
                barang.image.toString().also {
                    Log.d("Existing URI", it)
                }
            }

            val barang = Barang(
                id = id,
                namabarang = namaBarang,
                deskripsi = descBarang,
                stok = jumlahStok,
                harga = hargaBarang,
                image = imagePath.toString()
            )

            // Retrieve existing ViewModel instance from the parent activity
            val activity = requireActivity() as AdminCatalog
            val barangViewModel = activity.barangViewModel

            // Use the existing barangViewModel instance to insert data
            barangViewModel.updateBarang(barang)
            Log.d("Update Data", "Data Update successfully: $barang")
            dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("Crash", "Terjadi kesalahan: ${e.message}")
        }
    }

    private fun openGallery() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                PICK_IMAGE_REQUEST
            )
        } else {
            openGalleryIntent()
        }
    }

    private fun openGalleryIntent() {
        val galleryIntent = Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PICK_IMAGE_REQUEST && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openGalleryIntent()
        }
    }

}
