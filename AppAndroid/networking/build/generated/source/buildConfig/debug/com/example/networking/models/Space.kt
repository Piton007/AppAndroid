package com.example.networking.models

import java.io.Serializable

data class Space(
    val id: String?,
    val tag: String?,
    val available: String?,
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