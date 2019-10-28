package com.example.networking.models

import java.io.Serializable

data class Rate(
    val id: String?,
    val vehiculeType: String?,
    val price: String?,
    val frecuency: String?
) : Serializable {
    constructor() : this(
        "",
        "",
        "",
        ""
    )
}