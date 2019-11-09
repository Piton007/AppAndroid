package com.example.driverfeature

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomDialogParkings: BottomSheetDialogFragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         var view = inflater.inflate(R.layout.bottom_sheet_parkings_layout,container,false)
        val button=view.findViewById<Button>(R.id.btnBottomSheetDialog)
        button.setOnClickListener{
            val intent = Intent(context, DetailParkingActivity::class.java)
            startActivity(intent);
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}