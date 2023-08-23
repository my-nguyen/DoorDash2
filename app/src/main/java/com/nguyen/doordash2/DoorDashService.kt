package com.nguyen.doordash2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DoorDashService {
    // @GET("https://dd-interview.github.io/android/v1/feed?lat=37.42274&lng=-122.139956")
    @GET("v1/feed")
    fun getStores(@Query("lat") lat: Float=37.422740f,
                  @Query("lng") lng: Float=-122.139956f,
    ): Call<List<Store>>

    @GET("v1/stores/{id}")
    fun getStore(@Path("id") id: Int = 62087): Call<Restaurant>
}