package com.example.driverfeature

import android.location.Location
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng


class Marker(val location:LatLng, val type:String) {

}

class GoogleMapModel: ViewModel(){
    private lateinit var locations:MutableList<Marker>

    private fun resetList(){
        locations= mutableListOf(
            Marker(LatLng(15.00,16.00),"cochera"),
            Marker( LatLng(16.00,17.00),"parking"),
            Marker( LatLng(20.00,28.00),"parking")

        )
    }
    init{
        resetList()
        Log.i("GameViewModel","GameViewModel Generado")
    }
    fun getlocations(): MutableList<Marker> {
        return this.locations
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel","GameViewModel Destruido")
    }

}