package com.example.myapplication.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
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
        activity!!.findNavController(com.example.myapplication.R.id.nav_host_fragment)
        view.findViewById<TextView>(R.id.titleTextView).text=this.name
        view.findViewById<TextView>(R.id.descriptionTextView).text=this.description
        when(this.type){
            GlobalConstants.COCHERA -> view.findViewById<ImageView>(
                R.id.pictureImageView
            ).setImageResource(R.drawable.cochera)
            GlobalConstants.PARKING ->view.findViewById<ImageView>(
                R.id.pictureImageView
            ).setImageResource(R.drawable.estacionamiento)
        }
        button.setOnClickListener{
            findNavController().navigate(R.id.navigation_post_bookings)
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