package com.example.summitstock

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetAdd : BottomSheetDialogFragment() {
//    image upload
    private val PICK_IMAGE_REQUEST = 1
    private var imageView: ImageView? = null

//    increment decreement
    private var counterValue = 0
    private lateinit var counterTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false)

//        upload image
        val uploadButton: Button = view.findViewById(R.id.uploadBtnAdd)
        imageView = view.findViewById(R.id.imageView)

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
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST)
    }
//    upload image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, selectedImageUri)
            imageView?.setImageBitmap(bitmap)
        }
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
}