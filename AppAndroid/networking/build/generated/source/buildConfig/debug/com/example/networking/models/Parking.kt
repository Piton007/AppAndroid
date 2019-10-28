package com.example.networking.models

import java.io.Serializable

data class Parking(
    val id: String?,
    val name: String?,
    val nroSpaces: String?,
    val address: String?,
    val description: String?,
    val country: String?,
    val cellphone: String?,
    val location: String?,
    val parkType: String?,
    val photoReference: String?,
    val ownerId: String,
    val owner: Owner

) : Serializable{
    constructor() : this(
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        Owner()
    )
}