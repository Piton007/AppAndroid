package com.example.myapplication.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapplication.R

class PerfilFragment :Fragment(){

    private var name: String?=null
    private  var email: String?=null
    private  var password: String?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_perfil, container, false)
       view.findViewById<TextView>(R.id.perfilEmail).text=email
        view.findViewById<TextView>(R.id.perfilName).text=name
        view.findViewById<TextView>(R.id.passwordPerfil).text=password
        return view

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments!=null){
            this.name= arguments!!.getString("name")
            this.email=arguments!!.getString("email")
            this.password=arguments!!.getString("password")
        }
    }

}