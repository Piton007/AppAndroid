package com.example.myapplication.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.ui.adapters.AdaptadorC
import com.example.myapplication.ui.adapters.BookingsAdapter
import com.example.networking.model.Booking


class CarFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        /*

        var view = inflater.inflate(R.layout.fragment_car, container, false)
        val recyclerView: RecyclerView =view.findViewById(R.id.gone)
        recyclerView.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        val cars =ArrayList<CarClass>()
        cars.add(CarClass("VH1451",1, R.drawable.playa1))
        val adapter= AdaptadorC(cars)
        recyclerView.adapter=adapter
        return view
        // Inflate the layout for this fragment*/

        return inflater.inflate(com.example.myapplication.R.layout.fragment_car, container, false)

    }


}


