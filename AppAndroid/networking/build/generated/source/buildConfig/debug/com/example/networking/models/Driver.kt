package com.example.networking.models

import java.io.Serializable

data class Driver(
    val id: String?,
    val fullName: String?,
    val email: String?,
    val dni: String?,
    val cellphone: String?
) : Serializable{
    constructor() : this(
        "",
        "",
        "",
        "",
        ""
    )
}

