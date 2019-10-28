package com.example.networking.models

import java.io.Serializable

data class Assessment(
    val id: String?,
    val rate: String?,
    val comments: String?,
    val parkingId: String?,
    val parking: Parking,
    val driverId: String?,
    val driver: Driver
) : Serializable {
    constructor() : this(
        "",
        "",
        "",
        "",
        Parking(),
        "",
        Driver()
    )
}