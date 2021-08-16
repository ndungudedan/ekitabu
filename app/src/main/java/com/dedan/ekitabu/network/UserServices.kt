package com.dedan.ekitabu.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserServices {

    @Headers("Content-Type:application/json")
    @POST("/api/v1/login")
    fun login(@Body loginRequest: Requests): retrofit2.Call<Any>
}