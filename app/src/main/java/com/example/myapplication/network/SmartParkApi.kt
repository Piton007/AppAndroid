package com.example.myapplication.network

import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.myapplication.model.Driver
import com.example.myapplication.model.Owner
import org.json.JSONObject
import java.lang.reflect.InvocationHandler

class SmartParkApi {
    companion object {
        private const val BASE_URL = "https://smartparkapi.azurewebsites.net"
        val driversUrl = "$BASE_URL/api/drivers"
        val ownersUrl = "$BASE_URL/api/owners"
        val bookingsUrl = "$BASE_URL/api/bookings"
        val parkingsUrl = "$BASE_URL/api/parkings"
        val rateUrl = "$BASE_URL/api/rates"
        val spaceUrl = "$BASE_URL/api/spaces"
        val vehiclesUrl = "$BASE_URL/api/vehicles"
        const val TAG = "SmartParkApi"

        fun getDrivers(responseHandler: (ArrayList<Driver>?) -> Unit,
                       errorHandler: (ANError) -> Unit) {
            get(driversUrl,responseHandler,errorHandler)
        }
        fun postDriver(driver: Driver, responseHandler: (JSONObject?) -> Unit,
                       errorHandler: (ANError?) -> Unit) {
            val json: JSONObject = driver.convertToJson()
            post(json, driversUrl,responseHandler, errorHandler)
        }
        fun getOwners(responseHandler: (ArrayList<Owner>?) -> Unit,
                      errorHandler: (ANError) -> Unit) {
            get(ownersUrl,responseHandler,errorHandler)
        }
        fun postOwner(owner: Owner, responseHandler: (JSONObject?) -> Unit,
                      errorHandler: (ANError?) -> Unit) {
            val json: JSONObject = owner.convertToJson()
            post(json, ownersUrl,responseHandler,errorHandler )
        }

        private inline fun <reified  T> get(url: String, crossinline responseHandler: (ArrayList<T>?) -> Unit,
                                            crossinline errorHandler: (ANError) -> Unit)
        {
            AndroidNetworking.get(url)
                .setTag(TAG)
                .setPriority(Priority.HIGH)
                .build()
                .getAsObjectList(T::class.java,
                    object : ParsedRequestListener<ArrayList<T>> {
                        override fun onResponse(response: ArrayList<T>?) {
                            response?.apply {
                                responseHandler(response)
                            }
                        }
                        override fun onError(anError: ANError?) {
                            anError?.apply {
                                Log.d(TAG, "Error $errorCode: $errorBody $localizedMessage")
                                errorHandler(this)
                            }
                        }

                }
                )
        }

        private inline fun <reified T> getWithID(url: String, crossinline responseHandler: (T?) -> Unit,
                                                 crossinline errorHandler: (ANError) -> Unit) {
            AndroidNetworking.get(url)
                .setTag(TAG)
                .setPriority(Priority.HIGH)
                .build()
                .getAsObject(T::class.java,
                    object : ParsedRequestListener<T> {
                        override fun onResponse(response: T?) {
                            response?.apply {
                                responseHandler(response)
                            }
                        }

                        override fun onError(anError: ANError?) {
                            anError?.apply {
                                Log.d(TAG, "Error $errorCode: $errorBody $localizedMessage")
                                errorHandler(this)
                            }
                        }
                    }
                )
        }

        private inline fun  post(obj: JSONObject, url: String, crossinline responseHandler: (JSONObject?) -> Unit,
                                 crossinline errorHandler: (ANError?) -> Unit) {
            AndroidNetworking.post(url)
                .addHeaders("Content-Type", "application/json")
                .addJSONObjectBody(obj)
                .setTag(TAG)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {
                        responseHandler(response)
                    }

                    override fun onError(anError: ANError?) {
                        errorHandler(anError)
                    }
                })
        }

        private inline fun put(url: String, obj: JSONObject, crossinline responseHandler: (JSONObject?) -> Unit,
                               crossinline errorHandler: (ANError?) -> Unit)
        {
            AndroidNetworking.put(url)
                .addHeaders("Content-Type", "application/json")
                .addJSONObjectBody(obj)
                .setTag(TAG)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener{
                    override fun onResponse(response: JSONObject?) {
                        responseHandler(response)
                    }

                    override fun onError(anError: ANError?) {
                        errorHandler(anError)
                    }
                })
        }
    }
}