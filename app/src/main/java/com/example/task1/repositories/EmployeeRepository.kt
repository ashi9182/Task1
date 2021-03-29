package com.example.task1.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.task1.api.EmployeeInterface
import com.example.task1.models.Employee
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeeRepository {

    val tag = "EmployeeRepo"
    fun get(id : Int): LiveData<Employee> {
        val itemResponse = MutableLiveData<Employee>()

        EmployeeInterface().getEmployee(id)
            .enqueue(object : Callback<Employee> {
                override fun onResponse(call: Call<Employee>, response: Response<Employee>) {
                    if (!response.isSuccessful) {
                        Log.d(tag, "1${response.code().toString()}")
                        return
                    }
                    itemResponse.value = response.body()
                }

                override fun onFailure(call: Call<Employee>, t: Throwable) {
                    Log.d(tag, "2 ${t.message!!}")
                }

            })
        return itemResponse
    }
}