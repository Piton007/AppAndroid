package com.example.networking.model

import org.json.JSONObject
import java.io.Serializable

data class Vehicles(
    val id:Int,
    val type:String,
    val carPlate:String,
    val driverId:Int

):Serializable{
    constructor():this(
        id=0,
        type="",
        carPlate="",
        driverId=0
    )
    fun convertToJson(): JSONObject {
        val jsonDriver = JSONObject()

        jsonDriver.put("id",id)
        jsonDriver.put("type",type)
        jsonDriver.put("carPlate",carPlate)
        jsonDriver.put("driverId",driverId)


        return jsonDriver
    }
}