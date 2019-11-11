package com.example.driverfeature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.content.Intent


class BookingActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activiy_booking)
    }

    fun reserveSecondActivity() {
        Log.d("Booking Activity", "Button clicked!");
        val intent = Intent(this, BookingSecondActivity::class.java)
        startActivity(intent);
    }
}
