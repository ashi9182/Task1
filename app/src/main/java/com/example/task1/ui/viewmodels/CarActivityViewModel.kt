package com.example.task1.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task1.api.EmployeeInterface
import com.example.task1.models.Car
import com.example.task1.models.Cars
import com.google.gson.Gson

class CarActivityViewModel  : ViewModel(){

    private val tag = "CarViewModel"

    val cars : MutableLiveData<List<Car>> by lazy {
        MutableLiveData<List<Car>>()
    }

    val sortedCarsName : MutableLiveData<List<Car>> by lazy {
        MutableLiveData<List<Car>>()
    }

    init {
        getCarsDataByIndex()
        getCarsDataByName()
    }
    private fun getCarsDataByIndex() {
        val gson = Gson()
        val data = gson.fromJson(EmployeeInterface.carJsonString, Cars::class.java)
        Log.d(tag,data.cars[0].modelName)
        cars.value =data.cars


    }
    private fun getCarsDataByName() {
        val gson = Gson()
        val data = gson.fromJson(EmployeeInterface.carJsonString, Cars::class.java)
        val sortedList = mutableListOf<Car>()
        val sortedName = mutableListOf<String>()
        for (i in data.cars) {
            sortedName.add(i.modelName)
        }
        sortedName.sort()
        Log.d(tag, sortedName.toString())
        for (i in sortedName){
            for ( j in data.cars){
                if (i == j.modelName){
                    sortedList.add(j)
                }
            }
        }
        sortedCarsName.value = sortedList
    }
}