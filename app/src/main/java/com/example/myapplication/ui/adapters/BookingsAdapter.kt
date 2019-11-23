package com.example.myapplication.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import android.view.View
import android.widget.Button
import com.example.networking.model.Booking

class BookingsAdapter(var data: List<Booking>): RecyclerView.Adapter<BookingsAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder.from(parent)

    }


    override fun getItemCount()=data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.name.text = item.arriving
        holder.description.text=item.arriving
        holder.name.text=item.arriving
        holder.image.setImageResource(R.drawable.cochera)
        holder.button.setText(item.spaceId)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.pictureImageView)
        val name: TextView = itemView.findViewById(R.id.titleTextView)
        val description: TextView = itemView.findViewById(R.id.descriptionTextView)
        val button: Button= itemView.findViewById(R.id.descriptionButton)
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.item_bookings, parent, false)
                return ViewHolder(view)
            }
        }
    }
}