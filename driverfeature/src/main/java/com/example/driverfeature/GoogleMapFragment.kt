package com.example.driverfeature

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProviders
import com.example.networking.model.Parking
import com.example.networking.networking.SmartParkApi
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


class GoogleMapFragment : Fragment(), OnMapReadyCallback
    {

    private lateinit var googleMapViewModel: GoogleMapModel




    private var googleMap: GoogleMap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        val view = layoutInflater.inflate(R.layout.bottom_sheet_parkings_layout, null)
        mapFragment.getMapAsync(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        googleMapViewModel = ViewModelProviders.of(this).get(GoogleMapModel::class.java)

        return inflater.inflate(R.layout.fragment_google_map, container, false)
    }

    override fun onMapReady(p0: GoogleMap?) {
        val markers = googleMapViewModel.getlocations();
        this.googleMap = p0
        Log.d("GoogleMapFragment", "Inicializando Mapa")
        addMarkersToMap()
        setUpPermissions()
        googleMap!!.animateCamera(CameraUpdateFactory.newLatLng(markers.get(0).location))
        googleMap!!.setOnMarkerClickListener {
            val bottomSheet = BottomDialogParkings()
            bottomSheet.show(childFragmentManager, it.title)
            true

        }
        googleMap!!.uiSettings.isZoomControlsEnabled = true


    }

    fun setUpPermissions(){
        if (ActivityCompat.checkSelfPermission(requireContext(),android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                GlobalConstants.LOCATION_PERMISSION_CODE)
        }
    }

    fun addMarkersToMap() {
        SmartParkApi.getParkings({
            it as ArrayList<Parking>
            for (parking in it) {
                val info = parking.location.split(";").toTypedArray()
                val point = LatLng(info[0].toDouble(), info[1].toDouble())
                when (parking.park_Type) {
                    GlobalConstants.COCHERA -> googleMap!!.addMarker(
                        MarkerOptions().position(point).title(parking.name).snippet(
                            parking.id.toString()
                        ).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                    )
                    GlobalConstants.PARKING -> googleMap!!.addMarker(
                        MarkerOptions().position(point).title(parking.name).snippet(
                            parking.id.toString()
                        ).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    )
                    else -> { // Note the block
                        Log.e("GoogleMapModel", "No existe el tipo de estacionameinto")
                    }
                }
                Log.d("GoogleMapFragment", "Se ha generado un marker")

            }
        }, {
            Log.e("GoogleMapFragment", "Error get parkings ")
        }
        )
    }



}
