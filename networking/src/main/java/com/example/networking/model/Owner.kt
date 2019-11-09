package com.example.networking.model

import org.json.JSONObject
import java.io.Serializable

data class Owner(
    val id: Int,
    val fullName: String,
    val cellphone: Int,
    val companyName: String,
    val email: String,
    val ruc: String,
    val dni: Int
): Serializable {
    constructor() : this(
        0,
        "",
        0,
        "",
        "",
        "",
        0
    )

    fun convertToJson(): JSONObject {
        val jsonOwner = JSONObject()

        jsonOwner.put("id",id)
        jsonOwner.put("fullName",fullName)
        jsonOwner.put("cellphone",cellphone)
        jsonOwner.put("companyName",companyName)
        jsonOwner.put("email",email)
        jsonOwner.put("ruc",ruc)
        jsonOwner.put("dni",dni)

        return jsonOwner
    }
}