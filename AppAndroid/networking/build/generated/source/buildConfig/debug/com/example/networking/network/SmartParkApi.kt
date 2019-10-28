package com.example.networking.network

import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import org.json.JSONObject

class SmartParkApi{
    companion object{
        private const val BASE_URL = "https://smartparking-jecosoft.azurewebsites.net/"
        const val TAG = "SmartParkApi"
        //const val apiKey=""
        private inline fun <reified T> requestGet(url: String, parameter: Map<String, String>?,
                                               crossinline errorHandler: (ANError) -> Unit) {

            AndroidNetworking.get(url)
                //.addQueryParameter("apiKey", apiKey)
                .addQueryParameter(parameter)
                .setTag(TAG)
                .setPriority(Priority.LOW)
                .build()
                .getAsObject(T::class.java,
                    object : ParsedRequestListener<T> {
                        override fun onResponse(response: T?) {
                            response?.apply {
                                val response = this

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
        private inline fun <reified T> requestDelete(url: String, parameter: Map<String, String>?,
                                                  crossinline errorHandler: (ANError) -> Unit) {

            AndroidNetworking.delete(url)
                //.addQueryParameter("apiKey", apiKey)
                .addQueryParameter(parameter)
                .setTag(TAG)
                .setPriority(Priority.LOW)
                .build()
                .getAsObject(T::class.java,
                    object : ParsedRequestListener<T> {
                        override fun onResponse(response: T?) {
                            response?.apply {
                                val response = this

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
        private inline fun <reified T> requestPost(url: String, parameter: Map<String, String>?,
                                                    jsonObject: JSONObject,
                                                    crossinline errorHandler: (ANError) -> Unit) {

            AndroidNetworking.post(url)
                //.addQueryParameter("apiKey", apiKey)
                .addQueryParameter(parameter)
                .addJSONObjectBody(jsonObject)
                .setTag(TAG)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(T::class.java,
                    object : ParsedRequestListener<T> {
                        override fun onResponse(response: T?) {
                            response?.apply {
                                val response = this

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
        private inline fun <reified T> requestPut(url: String, parameter: Map<String, String>?,
                                                   jsonObject: JSONObject,
                                                   crossinline errorHandler: (ANError) -> Unit) {

            AndroidNetworking.put(url)
                //.addQueryParameter("apiKey", apiKey)
                .addQueryParameter(parameter)
                .addJSONObjectBody(jsonObject)
                .setTag(TAG)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(T::class.java,
                    object : ParsedRequestListener<T> {
                        override fun onResponse(response: T?) {
                            response?.apply {
                                val response = this

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
    }

}