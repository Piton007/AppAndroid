package com.example.myapplication.ui.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_settings.*


class SettingsFragment : Fragment() {
    var MapStyle = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onResume() {
        super.onResume()
        mapSatelital.setOnClickListener {
            changeSatelitalStyle()
        }
        mapNormal.setOnClickListener {
            changeNormalStyle()
        }
    }

    private fun changeNormalStyle(){
        mapNormal.scaleType = ImageView.ScaleType.FIT_CENTER
        mapSatelital.scaleType = ImageView.ScaleType.CENTER
        MapStyle = "Normal"
    }

    private fun changeSatelitalStyle(){
        mapSatelital.scaleType = ImageView.ScaleType.FIT_CENTER
        mapNormal.scaleType = ImageView.ScaleType.CENTER
        MapStyle = "Satelital"
    }





}
