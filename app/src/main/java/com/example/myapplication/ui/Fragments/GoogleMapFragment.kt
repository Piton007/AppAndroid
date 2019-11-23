package com.example.myapplication.ui.Fragments

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProviders
import com.example.driverfeature.R
import com.example.networking.model.Parking
import com.example.networking.networking.SmartParkApi
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.location.LocationServices;


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
        val view = layoutInflater.inflate(R.layout.item_bookings, null)
        mapFragment.getMapAsync(this)
        LocationServices.getFusedLocationProviderClient(requireActivity())

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

        this.googleMap = p0
        Log.d("GoogleMapFragment", "Inicializando Mapa")
        addMarkersToMap()
        setUpPermissions()



        googleMap!!.setOnMarkerClickListener {
            val markers = googleMapViewModel.locations();
            Log.d("GoogleMapFragment","Tamaño de markers"+markers.size.toString()+" Index: "+it.snippet)
            val bottomSheet = com.example.myapplication.ui.Fragments.BottomDialogParkings()
            val parking=markers.get(it.snippet.toInt())
            val args= Bundle()
            args.putString("name",parking.name)
            args.putString("description",parking.description)
            args.putString("type",parking.park_Type)
            bottomSheet.arguments=args
            bottomSheet.show(childFragmentManager, it.title)
            true

        }
        googleMap!!.uiSettings.isZoomControlsEnabled = true
    }

    fun setUpPermissions(){
        if (ActivityCompat.checkSelfPermission(requireContext(),android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                GlobalConstants.LOCATION_PERMISSION_CODE
            )
        }
    }

    fun addMarkersToMap() {
        SmartParkApi.getParkings({
            it as ArrayList<Parking>
            for ((index,parking) in it.withIndex()) {
                googleMapViewModel.addParking(parking)
                val info = parking.location.split(";").toTypedArray()
                val point = LatLng(info[0].toDouble(), info[1].toDouble())
                when (parking.park_Type){
                    GlobalConstants.COCHERA -> googleMap!!.addMarker(
                        MarkerOptions().position(point).title(parking.name).snippet(
                            index.toString()
                        ).icon(BitmapDescriptorFactory.fromResource(R.drawable.cochera_icon))
                    )
                    GlobalConstants.PARKING -> googleMap!!.addMarker(
                        MarkerOptions().position(point).title(parking.name).snippet(
                            index.toString()
                        ).icon(BitmapDescriptorFactory.fromResource(R.drawable.parking_icon))
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
