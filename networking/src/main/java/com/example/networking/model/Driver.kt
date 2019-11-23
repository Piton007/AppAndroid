package com.example.networking.model

import org.json.JSONObject
import java.io.Serializable

data class Driver(
    val id: Int,
    val fullName: String,
    val email: String,
    val dni: Int,
    val cellphone: Int
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

        jsonDriver.put("id",id)
        jsonDriver.put("fullName",fullName)
        jsonDriver.put("email",email)
        jsonDriver.put("dni",dni)
        jsonDriver.put("cellphone",cellphone)

        return jsonDriver
    }
}