package com.example.networking.models

import java.io.Serializable

data class Booking(
    val id: String?,
    val arrivalTime: String?,
    val status: String?,
    val vehicleId: String?,
    val vehicle: Vehicle,
    val spaceId: String?,
    val space: Space,
    val driverId: String?,
    val driver: Driver
) : Serializable {
    constructor() : this(
        "",
        "",
        "",
        "",
        Vehicle(),
        "",
        Space(),
        "",
        Driver()
    )
}