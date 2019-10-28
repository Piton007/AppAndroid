package com.example.networking.models

import java.io.Serializable

data class Vehicle(
    val id: String?,
    val type: String?,
    val carPlate: String?,
    val driverId: String?,
    val driver: Driver
) : Serializable {
    constructor() : this(
        "",
        "",
        "",
        "",
        Driver()
    )
}