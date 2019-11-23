package com.example.myapplication.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.ui.adapters.DashboardAdapter
import com.example.myapplication.ui.adapters.ParkingsAdapter
import com.example.networking.model.Parking
import com.example.networking.networking.SmartParkApi
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {


    var parkings: List<Parking> = ArrayList()
    lateinit var parkingAdapter: DashboardAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_dashboard,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parkingAdapter = DashboardAdapter(parkings)
        parkingRecyclerView.apply {
            adapter = parkingAdapter
            layoutManager = LinearLayoutManager(this.context)
        }
        SmartParkApi.getParkings({
            it?.apply {
                parkingAdapter.parkings = this
                parkingAdapter.notifyDataSetChanged()
            }

        },{
            parkingAdapter.parkings = ArrayList()
            parkingAdapter.notifyDataSetChanged()
        })
    }


}