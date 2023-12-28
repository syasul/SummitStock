package com.example.summitstock.Room

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.summitstock.R
import com.example.summitstock.Room.model.Barang

class BarangAdapterAdmin(private var dataList: List<Barang>, private val itemClickListener: OnItemClickListener, private val itemClickDelete: OnClickDelete) : RecyclerView.Adapter<BarangAdapterAdmin.ViewHolder>() {

    interface OnClickDelete {
        fun onItemClickDelete(barang: Long)
    }

    interface OnItemClickListener {
        fun onItemClickUpdate(barang: Barang)
        fun onItemClickDelete(id: Long)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_barang, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var barang = dataList[position]
        holder.bind(barang)


//        holder image
        val imageUri: Uri = Uri.parse(barang.image)

        Glide.with(holder.itemView.context)
            .load(imageUri) // Replace with the actual URL or resource ID
            .into(holder.gambarBarang)
//        val imagestring = barang.image
//
//        if(!imagestring!!.isEmpty()){
//            val inputStream = holder.itemView.context.contentResolver.openInputStream(imageUri)
//            val bitmap = BitmapFactory.decodeStream(inputStream)
//            holder.gambarBarang.setImageBitmap(bitmap)
//        }

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
                    itemClickListener.onItemClickUpdate(dataList[position])
                }
            }
            deleteButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val id = dataList[position].id
                    itemClickDelete.onItemClickDelete(id)
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
                itemClickListener.onItemClickUpdate(barang)
            }
        }
        // Deklarasikan dan inisialisasikan elemen UI lainnya di sini
    }

    fun setData(newDataList: List<Barang>) {
        dataList = newDataList
        notifyDataSetChanged()
    }

}