package com.example.networking.model

import com.google.gson.Gson
import com.google.gson.JsonElement
import org.json.JSONObject
import java.io.Serializable

data class Spaces(
    val id:Int,
    val tag:String,
    val available:Boolean,
    val parkingsId:Int,
    val vehicleId:Int
):Serializable{
    constructor():this(
        id=0,
        tag="",
        available = false,
        parkingsId = 0,
        vehicleId = 0
        )
    fun convertToJson(): JSONObject {
        val jsonDriver = JSONObject()

        jsonDriver.put("id",id)
        jsonDriver.put("tag",tag)
        jsonDriver.put("available",available)
        jsonDriver.put("parkingsId",parkingsId)
        jsonDriver.put("vehicleId",vehicleId)

        return jsonDriver
    }
    fun JsonnToObject(jsonObject: JsonElement): Spaces{
       return Gson().fromJson(jsonObject,Spaces::class.java)
    }
}