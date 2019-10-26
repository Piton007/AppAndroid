package com.example.driverfeature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.content.Intent


class BookingActivity : AppCompatActivity() {
    private val LOG_TAG = BookingActivity::class.java!!.getSimpleName()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)
    }

    fun reserveSecondActivity(view: View) {
        Log.d(LOG_TAG, "Button clicked!");
        val intent = Intent(this, BookingSecondActivity::class.java)
        startActivity(intent);
    }
}
