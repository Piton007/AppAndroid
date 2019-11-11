package com.example.myapplication.ui.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.myapplication.R



class PostBooking : Fragment() {

     var space:String?=null
     var estacionamiento:String?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_booking, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments!=null){
            this.space= arguments!!.getString("name")
            this.estacionamiento=arguments!!.getString("email")
            this.password=arguments!!.getString("password")
        }
    }

}
