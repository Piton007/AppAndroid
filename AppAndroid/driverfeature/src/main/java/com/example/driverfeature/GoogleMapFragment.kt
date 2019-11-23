package com.example.driverfeature

import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class GoogleMapFragment : Fragment(),OnMapReadyCallback,LocationListener {

    private  var googleMap: GoogleMap?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment=childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_google_map, container, false)
    }

    override fun onMapReady(p0: GoogleMap?) {

        this.googleMap=p0
        val point=LatLng(13.03,77.60)
        googleMap!!.addMarker(MarkerOptions().position(point).title("Prueba").snippet("1"))
        googleMap!!.animateCamera(CameraUpdateFactory.newLatLng(point))
        googleMap!!.setOnMarkerClickListener { Toast.makeText(this.activity,"Nombre"+it.title+" Location: "+it.position,
            Toast.LENGTH_LONG).show(); true ;}
    }



    override fun onLocationChanged(location: Location?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderEnabled(provider: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderDisabled(provider: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    // TODO: Rename and change types of parameters

}
