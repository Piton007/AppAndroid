package com.example.myapplication.ui.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.myapplication.R
import com.example.myapplication.ui.adapters.BookingsAdapter
import com.example.networking.model.Booking
import com.example.networking.networking.SmartParkApi
import kotlinx.android.synthetic.main.fragment_bookings.*


class BookingsFragment : Fragment() {


    lateinit var bookingsAdapter: BookingsAdapter


    var bookings: List<Booking> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return  inflater.inflate(R.layout.fragment_bookings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*bookingsAdapter= BookingsAdapter(bookings)
        booking_list.apply {
        adapter=bookingsAdapter
        layoutManager=LinearLayoutManager(this.context)
       }
        SmartParkApi.getBookings({
            bookingsAdapter.data=it as ArrayList<Booking>
            bookingsAdapter.notifyDataSetChanged()
        }, {
            Log.e("BookingFragment", "Error get parkings ")
        })
*/
    }
}
