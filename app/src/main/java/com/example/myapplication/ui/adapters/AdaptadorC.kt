package com.example.myapplication.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.ui.Fragments.CarClass



class AdaptadorC(var list: ArrayList<CarClass>):RecyclerView.Adapter<AdaptadorC.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent?.context).inflate(R.layout.fragment_car ,parent, false )
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }


    class ViewHolder(view:View): RecyclerView.ViewHolder(view){
        fun bindItems(data:CarClass) {
           // val title: TextView = itemView.findViewById(R.id.txtTitle)
            //val count: TextView = itemView.findViewById(R.id.txtCount)
            //val foto: ImageView = itemView.findViewById(R.id.foto)

            //title.text = data.name
            //count.text = data.numOfCars.toString()

            //Glide.with(itemView.context).load(data.foto).into(foto)

            itemView.setOnClickListener {

                Toast.makeText(itemView.context, "vehicu", Toast.LENGTH_LONG).show()
            }

        }

    }
}



