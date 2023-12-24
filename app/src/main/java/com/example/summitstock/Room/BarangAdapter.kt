package com.example.summitstock.Room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.summitstock.R
import com.example.summitstock.Room.model.Barang

class BarangAdapter(private var dataList: List<Barang>) : RecyclerView.Adapter<BarangAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_barang, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var barang = dataList[position]

        // Setel data pada elemen UI di sini
//        holder image
        Glide.with(holder.itemView.context)
            .load(barang.image) // Replace with the actual URL or resource ID
            .into(holder.gambarBarang)
//        holder text
        holder.namaBarang.text = barang.namabarang
        holder.deskripsiBarang.text = barang.deskripsi
        holder.stokBarang.text = barang.stok.toString()
        holder.hargaBarang.text = "RP. ${barang.harga},- / day"
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gambarBarang: ImageView = itemView.findViewById(R.id.gambarBarang)
        val namaBarang: TextView = itemView.findViewById(R.id.namaBarang)
        val deskripsiBarang: TextView = itemView.findViewById(R.id.deskripsiBarang)
        val stokBarang: TextView = itemView.findViewById(R.id.stokBarang)
        val hargaBarang: TextView = itemView.findViewById(R.id.hargaBarang)
        // Deklarasikan dan inisialisasikan elemen UI lainnya di sini
    }

    fun setData(newDataList: List<Barang>) {
        dataList = newDataList
        notifyDataSetChanged()
    }

}