package com.example.myapplication.ui.adapters

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.ui.Fragments.GlobalConstants
import com.example.myapplication.R
import com.example.networking.model.Parking



class ParkingsAdapter(var bookings: List<Parking>): RecyclerView.Adapter<ParkingsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      return ViewHolder.from(parent)
    }

    override fun getItemCount()=bookings.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = bookings[position]
        holder.name.text = item.name
        holder.description.text=item.description
        when(item.park_Type){
            GlobalConstants.PARKING->holder.image.setImageResource(R.drawable.estacionamiento)
            GlobalConstants.COCHERA->holder.image.setImageResource(R.drawable.cochera)
        }
        holder.button.setText(item.country)
    }


    class ViewHolder private constructor(itemView: View):RecyclerView.ViewHolder(itemView){

        val image: ImageView = itemView.findViewById(R.id.pictureImageView)
        val name: TextView = itemView.findViewById(R.id.titleTextView)
        val description: TextView = itemView.findViewById(R.id.descriptionTextView)
        val button: Button = itemView.findViewById(R.id.descriptionButton)
        companion object {
            fun from(parent: ViewGroup): ParkingsAdapter.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.item_bookings, parent, false)
                return ViewHolder(view)
            }
        }
    }
}