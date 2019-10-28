package com.example.networking.models

import java.io.Serializable

data class Owner(
    val id: String?,
    val fullName: String?,
    val cellphone: String?,
    val companyName: String?,
    val email: String?,
    val ruc: String?,
    val dni: String?

) : Serializable{
    constructor() : this(
        "",
        "",
        "",
        "",
        "",
        "",
        ""
    )
}