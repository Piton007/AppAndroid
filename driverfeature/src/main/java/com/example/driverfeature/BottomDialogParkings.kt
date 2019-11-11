package com.example.driverfeature

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomDialogParkings: BottomSheetDialogFragment(){

    private var name: String?=null
    private  var type: String?=null
    private  var description: String?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         var view = inflater.inflate(R.layout.item_bookings,container,false)
        val button=view.findViewById<Button>(R.id.descriptionButton)
        view.findViewById<TextView>(R.id.titleTextView).text=this.name
        view.findViewById<TextView>(R.id.descriptionTextView).text=this.description
        when(this.type){
            GlobalConstants.COCHERA-> view.findViewById<ImageView>(R.id.pictureImageView).setImageResource(R.drawable.cochera)
            GlobalConstants.PARKING->view.findViewById<ImageView>(R.id.pictureImageView).setImageResource(R.drawable.estacionamiento)
        }
        button.setOnClickListener{
            val intent = Intent(context, DetailParkingActivity::class.java)
            startActivity(intent);
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments!=null){
            this.name= arguments!!.getString("name")
            this.description=arguments!!.getString("description")
            this.type=arguments!!.getString("type")
        }
    }


}