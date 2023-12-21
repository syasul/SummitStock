package com.example.summitstock

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetUpdate : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_admin_catalog, container, false)

        val updateButton : ImageButton = view.findViewById(R.id.updateButton)
        updateButton.setOnClickListener {
            // Handle ImageButton click event
            // For example, you can dismiss the bottom sheet

            val intent = Intent(context, AdminCatalog::class.java)
            startActivity(intent)

            dismiss()
        }

        return inflater.inflate(R.layout.fragment_bottom_sheet_update, container, false)
    }



}
