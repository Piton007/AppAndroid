package com.example.myapplication.ui.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

import com.example.myapplication.R



class PostBooking : Fragment() {

     var space:String?=null
     var placa:String?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val navigator=activity!!.findNavController(R.id.nav_host_fragment)
        val view= inflater.inflate(R.layout.fragment_post_booking, container, false)
        view.findViewById<Button>(R.id.btnAgregarReserva).setOnClickListener {
            Toast.makeText(requireContext(), "La reserva se ha guardado exitosamente", Toast.LENGTH_SHORT).show()
            val handler=Handler()
            handler.postDelayed(Runnable {

                navigator.navigate(R.id.navigation_bookings)
            },5000)


    }
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments!=null){
            this.space= arguments!!.getString("space")
            this.placa=arguments!!.getString("placa")

        }
    }

}
