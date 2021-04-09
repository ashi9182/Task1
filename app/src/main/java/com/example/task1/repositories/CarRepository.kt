package com.example.task1.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.task1.api.CarsInterface
import com.example.task1.api.EmployeeInterface
import com.example.task1.models.Cars
import com.example.task1.models.Employee
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CarRepository {


    val tag = "CarRepo"
    fun get(): LiveData<Cars> {
        val itemResponse = MutableLiveData<Cars>()

        CarsInterface().getCars("media", "1d3642ef-24de-4a95-8db6-7ea1e35fe2db")
            .enqueue(object : Callback<Cars> {
                override fun onResponse(call: Call<Cars>, response: Response<Cars>) {
                    if (!response.isSuccessful) {
                        Log.d(tag, "1${response.code().toString()}")
                        return
                    }
                    itemResponse.value = response.body()
                }

                override fun onFailure(call: Call<Cars>, t: Throwable) {
                    Log.d(tag, "2 ${t.message!!}")
                }

            })
        return itemResponse
    }

}