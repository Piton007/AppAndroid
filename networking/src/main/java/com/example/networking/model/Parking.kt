package com.example.networking.model

import org.json.JSONObject
import java.io.Serializable

data class Parking(
    val id: Int,
    val name:String,
    val nroSpaces: Int,
    val address: String,
    val description: String,
    val country: String,
    val cellphone: String,
    val location: String,
    val park_Type: String,
    val photo_Reference: String,
    val ownerId:Int
): Serializable{
    constructor() : this(
        id=0,
        name="",
        nroSpaces = 0,
        address = "",
        description="",
        country="",
        cellphone="",
        location="",
        park_Type="",
        photo_Reference="",
        ownerId=0
    )
    fun convertToJson(): JSONObject {
        val jsonOwner = JSONObject()

        jsonOwner.put("id",id)
        jsonOwner.put("name",name)
        jsonOwner.put("nroSpaces",nroSpaces)
        jsonOwner.put("address",address)
        jsonOwner.put("description",description)
        jsonOwner.put("country",country)
        jsonOwner.put("cellphone",cellphone)
        jsonOwner.put("location",location)
        jsonOwner.put("park_Type",park_Type)
        jsonOwner.put("photo_Reference",photo_Reference)
        jsonOwner.put("ownerId",ownerId)

        return jsonOwner
    }

}