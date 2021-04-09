package com.example.task1.api

import com.example.task1.models.Cars
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CarsInterface {

    @GET("cars.json")
    fun getCars(
        @Query("alt") type : String,
        @Query("token") token : String
    ) : Call<Cars>


    companion object{
        operator fun invoke() : CarsInterface{
            return Retrofit.Builder()
                .baseUrl("https://firebasestorage.googleapis.com/v0/b/task1-a6304.appspot.com/o/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CarsInterface::class.java)
        }


    }
}