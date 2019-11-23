package com.example.myapplication.ui.Fragments

import android.location.Location
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.networking.model.Parking
import com.google.android.gms.maps.model.LatLng




class GoogleMapModel: ViewModel(){
    private val _parkings:MutableList<Parking> = arrayListOf()

    init{

        Log.i("GameViewModel","GameViewModel Generado")
    }
    fun locations(): MutableList<Parking> {
        return this._parkings
    }

    fun addParking(parking: Parking){
        _parkings.add(parking)
    }


    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel","GameViewModel Destruido")
    }

}