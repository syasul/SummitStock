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

class BarangAdapterUser(private var dataList: List<Barang>) : RecyclerView.Adapter<BarangAdapterUser.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val barang = dataList[position]
        holder.bind(barang)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gambarBarang: ImageView = itemView.findViewById(R.id.gambarBarang)
        val namaBarang: TextView = itemView.findViewById(R.id.namaBarang)
        val deskripsiBarang: TextView = itemView.findViewById(R.id.deskripsiBarang)
        val stokBarang: TextView = itemView.findViewById(R.id.stokBarang)
        val hargaBarang: TextView = itemView.findViewById(R.id.hargaBarang)

        fun bind(barang: Barang) {
            // Bind data to UI elements
            Glide.with(itemView.context)
                .load(barang.image)
                .into(gambarBarang)

            namaBarang.text = barang.namabarang
            deskripsiBarang.text = barang.deskripsi
            stokBarang.text = barang.stok.toString()
            hargaBarang.text = "RP. ${barang.harga},- / day"
        }
    }

    // Update data setiap kali ada perubahan
    fun setData(newDataList: List<Barang>) {
        dataList = newDataList
        notifyDataSetChanged()
    }
}