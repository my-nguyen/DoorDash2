package com.nguyen.doordash2

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val doorDashService: DoorDashService) {
    companion object {
        const val TAG = "Repository"
    }

    fun getStores(): MutableLiveData<List<Store>> {
        val stores = MutableLiveData<List<Store>>()
        doorDashService.getStores().enqueue(object : Callback<List<Store>> {
            override fun onResponse(call: Call<List<Store>>, response: Response<List<Store>>) {
                Log.d(TAG, "onResponse $response")
                val body = response.body()
                if (body == null) {
                    Log.w(TAG, "Did not receive a valid response from DoorDash API... exiting")
                } else {
                    Log.d(TAG, "DoorDashService returns ${body.size} records")
                    stores.value = body
                }
            }

            override fun onFailure(call: Call<List<Store>>, t: Throwable) {
                Log.d(TAG, "onFailure $t")
            }
        })
        return stores
    }

    fun getStore(id: Int): MutableLiveData<Restaurant> {
        val restaurant = MutableLiveData<Restaurant>()
        doorDashService.getStore().enqueue(object : Callback<Restaurant> {
            override fun onResponse(call: Call<Restaurant>, response: Response<Restaurant>) {
                val body = response.body()
                if (body == null) {
                    Log.w(TAG, "Did not receive a valid response from DoorDash API... exiting")
                } else {
                    restaurant.value = body
                }
            }

            override fun onFailure(call: Call<Restaurant>, t: Throwable) {
                Log.d(TAG, "onFailure $t")
            }
        })
        return restaurant
    }
}