package com.example.myapplication.ui.Fragments

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import com.example.myapplication.Preferences

import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_settings.*


class SettingsFragment : Fragment() {

    var MAPStyle = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onResume() {
        super.onResume()
        loadSettings()
        var selects: ArrayList<CheckBox> = ArrayList<CheckBox>()

        mapSatelital.setOnClickListener {
            changeSatelitalStyle()
        }
        mapNormal.setOnClickListener {
            changeNormalStyle()
        }

        buttonSave.setOnClickListener {
            for(item in selects){
                if(!item.isChecked){
                    if(!Preferences.removePreference(item.text.toString())){
                        Log.d("remove", "no se pudo: "+ item.text.toString())
                    }
                }else{
                    if(!Preferences.addPreference(item.text.toString())){
                        Log.d("add", "no se pudo: "+ item.text.toString())
                    }
                }
            }
            saveSettings()
        }
    }

    private fun changeNormalStyle(){
        mapNormal.scaleType = ImageView.ScaleType.FIT_CENTER
        mapSatelital.scaleType = ImageView.ScaleType.CENTER
        MAPStyle = "Normal"
    }

    private fun changeSatelitalStyle(){
        mapSatelital.scaleType = ImageView.ScaleType.FIT_CENTER
        mapNormal.scaleType = ImageView.ScaleType.CENTER
        MAPStyle = "Satelital"
    }

    private fun loadSettings(){
        val preference: SharedPreferences? = activity?.getSharedPreferences("settings", Context.MODE_PRIVATE)

        val mapStyle: String = preference?.getString("mapStyle","Normal") as String

        MAPStyle = mapStyle
        if(mapStyle == "Normal"){
            changeNormalStyle()
        }else if(mapStyle == "Satelital"){
            changeSatelitalStyle()
        }
    }

    private fun saveSettings(){
        val preference: SharedPreferences? = activity?.getSharedPreferences("settings", Context.MODE_PRIVATE)
        val mapStyle: String = MAPStyle
        val editor: SharedPreferences.Editor? = preference?.edit()
        editor?.putString("mapStyle",mapStyle)
        editor?.commit()
    }
}
