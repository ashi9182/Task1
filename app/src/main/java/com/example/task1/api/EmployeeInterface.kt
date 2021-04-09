package com.example.task1.api

import com.example.task1.models.Employee
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface EmployeeInterface {
    @GET("{identify}")
    fun getEmployee(@Path("identify")id : Int) : Call<Employee>


    companion object{
        operator fun invoke() : EmployeeInterface{
            return Retrofit.Builder()
                .baseUrl("https://dummy.restapiexample.com/api/v1/employee/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(EmployeeInterface::class.java)
        }


    }
}