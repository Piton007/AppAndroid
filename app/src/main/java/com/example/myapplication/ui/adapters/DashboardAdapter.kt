package com.example.myapplication.ui.adapters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import  android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.item_parking.view.*
import com.example.networking.model.Parking
import com.example.myapplication.R

class DashboardAdapter (var parkings: List<Parking>) :
    RecyclerView.Adapter<DashboardAdapter.ViewHolder>(){
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val pictureImageView = itemView.pictureImageView
        val titleTextView = itemView.titleTextView
        val moreButton = itemView.moreButton
        val spacesTextView = itemView.spacesTextView
        fun bindTo(parking: Parking) {
            pictureImageView.apply {
                setDefaultImageResId(R.mipmap.ic_launcher)
                setErrorImageResId(R.mipmap.ic_launcher)
                setImageUrl("https://www.nextlevelfairs.com/assets/images/image-not-available.png")
            }
            titleTextView.text = parking.name
            val nroSpaces = parking.nroSpaces.toString()
            val textSpaces = " Espacios Totales"

            val sb = StringBuilder()
            sb.append(nroSpaces).append(textSpaces)
            val spacesText = sb.toString()

            spacesTextView.text= spacesText
            moreButton.setOnClickListener {
                it.apply { this.findNavController().navigate(R.id.navigation_post_bookings) }


            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_parking, parent, false))
    }

    override fun getItemCount(): Int {
        return parkings.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(parkings[position])
    }

}