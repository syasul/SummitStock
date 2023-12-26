package com.example.summitstock.Room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Delete
import com.bumptech.glide.Glide
import com.example.summitstock.AdminCatalog
import com.example.summitstock.R
import com.example.summitstock.Room.model.Barang

class BarangAdapter(private var dataList: List<Barang>, private val itemClickListener: OnItemClickListener, private val itemClickDelete: OnClickDelete) : RecyclerView.Adapter<BarangAdapter.ViewHolder>() {

    interface OnClickDelete {
        fun onItemClick(barang: Barang)
    }

    fun deleteItem(barang: Barang) {
        itemClickDelete.onItemClick(barang)
    }
    interface OnItemClickListener {
        fun onItemClick(barang: Barang)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_barang, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var barang = dataList[position]
        holder.bind(barang)


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

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gambarBarang: ImageView = itemView.findViewById(R.id.gambarBarang)
        val namaBarang: TextView = itemView.findViewById(R.id.namaBarang)
        val deskripsiBarang: TextView = itemView.findViewById(R.id.deskripsiBarang)
        val stokBarang: TextView = itemView.findViewById(R.id.stokBarang)
        val hargaBarang: TextView = itemView.findViewById(R.id.hargaBarang)
        val updateButton: ImageButton = itemView.findViewById(R.id.fab2)
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)

        init {
            // Add a click listener to the update button
            updateButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    itemClickListener.onItemClick(dataList[position])
                }
            }
        }

        fun bind(barang: Barang) {
            // Bind data to UI elements
            Glide.with(itemView.context)
                .load(barang.image)
                .into(gambarBarang)
            namaBarang.text = barang.namabarang
            deskripsiBarang.text = barang.deskripsi
            stokBarang.text = barang.stok.toString()
            hargaBarang.text = "RP. ${barang.harga},- / day"

            // Set click listener on the update button
            updateButton.setOnClickListener {
                itemClickListener.onItemClick(barang)
            }
        }
        // Deklarasikan dan inisialisasikan elemen UI lainnya di sini
    }

    fun setData(newDataList: List<Barang>) {
        dataList = newDataList
        notifyDataSetChanged()
    }

}