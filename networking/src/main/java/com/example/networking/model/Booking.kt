package com.example.networking.model

import org.json.JSONObject
import java.io.Serializable

data class Booking(
    val id: Int,
    val arriving: String,
    val vehicleId: String,
    val spaceId: Int,
    val driverId: Int
): Serializable {
    constructor() :  this(
        0,
        "",
        "",
        0,
        0
    )

    fun convertToJson(): JSONObject {
        val jsonDriver = JSONObject()


        return jsonDriver
    }
}