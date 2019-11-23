package com.example.myapplication.ui.Fragments

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.content.SharedPreferences
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.R
import com.example.networking.model.Parking
import com.example.networking.networking.SmartParkApi
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.MapStyleOptions


var map: String = ""
class GoogleMapFragment : Fragment(), OnMapReadyCallback
    {

    private lateinit var googleMapViewModel: GoogleMapModel


        private lateinit var locationManager: LocationManager
        private var latitud =-4.0192200
        private var longitud =-71.1028100
        private val zoom = 8.0f

        private var location: Location? = null

        internal var TAG = "Near Me Maps:"



    private var googleMap: GoogleMap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
        override fun onResume() {
            super.onResume()
            loadPreference()
        }
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        val view = layoutInflater.inflate(R.layout.item_bookings, null)
        mapFragment.getMapAsync(this)
        LocationServices.getFusedLocationProviderClient(requireActivity())

    }
        fun changeMapStyle(){
            var style = R.raw.mapnormalstyle
            if(map=="Normal") {
                style=R.raw.mapnormalstyle
            }
            else {
                if (map=="Satelital"){ //Nigth fake
                    style=R.raw.mapnightstyle
                }
            }
            try {
                // Customise the styling of the base map using a JSON object defined
                // in a raw resource file.
                val success = googleMap!!.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                        context, style
                    )
                )
                if (!success) {
                    Log.e(TAG, "Maps Fragment,Style parsing failed.")
                }
            } catch (e: Resources.NotFoundException) {
                Log.e(TAG, "Maps Fragment,Can't find style. Error: ", e)
            }
            val lima = LatLng(-12.0431800, -77.0282400)
            googleMap!!.moveCamera(CameraUpdateFactory.newLatLng(lima))
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

        locationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (ActivityCompat.checkSelfPermission(
                activity!!.applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) !== PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                activity!!.applicationContext, Manifest.permission.ACCESS_COARSE_LOCATION
            ) !== PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("SettingsFragment", "No hay permisos de geolocalizacion")
            ActivityCompat.requestPermissions(
                activity!!,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                1
            )
        } else {

            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            if (location != null) {

                latitud = location!!.latitude
                longitud = location!!.longitude
                Log.d("SettingsFragment", "Las coordenadas actuales son :: Lat:$latitud Lon:$longitud")
            } else {
                Log.e("SettingsFragment", "Location is null")
                return
            }

        }


        goToLocationInMap(latitud, longitud, zoom)


        googleMap!!.setOnMarkerClickListener {
            val markers = googleMapViewModel.locations();
            Log.d("GoogleMapFragment","Tamaño de markers"+markers.size.toString()+" Index: "+it.snippet)
            val bottomSheet = com.example.myapplication.ui.Fragments.BottomDialogParkings()
            val parking = markers.get(it.snippet.toInt())
            val args= Bundle()
            args.putString("name",parking.name)
            args.putString("description",parking.description)
            args.putString("type",parking.park_Type)
            bottomSheet.arguments=args
            bottomSheet.show(childFragmentManager, it.title)
            true

        }
        changeMapStyle()
        googleMap!!.uiSettings.isZoomControlsEnabled = true
    }
        private fun goToLocationInMap(lat: Double, lng: Double, zoom: Float) {

            val latitudLongitud = LatLng(lat, lng)
            drawUserInMap(latitudLongitud)
            val update = CameraUpdateFactory.newLatLngZoom(latitudLongitud, zoom)
            googleMap?.moveCamera(update)

        }
        private fun drawUserInMap(point: LatLng) {
            // Creating an instance of MarkerOptions and define a icon

            val markerOptions = MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)).title("me")
            // Setting latitude and longitude for the marker
            markerOptions.position(point)
            // Adding marker on the Google Map
            googleMap?.addMarker(markerOptions)
        }
        fun loadPreference(){
            val preference: SharedPreferences? = activity?.getSharedPreferences("settings", Context.MODE_PRIVATE)
            map = preference?.getString("mapStyle","Normal") as String

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
