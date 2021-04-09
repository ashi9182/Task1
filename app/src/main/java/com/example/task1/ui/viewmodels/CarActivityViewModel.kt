package com.example.task1.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.task1.api.EmployeeInterface
import com.example.task1.models.Car
import com.example.task1.models.Cars
import com.example.task1.models.Employee
import com.example.task1.repositories.CarRepository
import com.example.task1.repositories.EmployeeRepository
import com.google.firebase.storage.FirebaseStorage
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException
import java.nio.charset.Charset

class CarActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val tag = "CarViewModel"

    val carData: MutableLiveData<List<Car>> by lazy {
        MutableLiveData<List<Car>>()
    }

    val remoteCarDetails: LiveData<Cars> by lazy {
        CarRepository().get()
    }


    private val inputStream = application.assets.open("cars.json")

    fun handleData() {
        carData.value = remoteCarDetails.value?.cars
    }

    private fun firebaseStorage() {
        val ref = FirebaseStorage.getInstance().reference.child("cars.json")
        ref.downloadUrl.addOnSuccessListener {
            Log.d(tag, it.toString())
        }
            .addOnFailureListener {
                Log.d(tag, it.toString())
            }
    }

    fun getCarsDataByIndex() {
        val sortedList = mutableListOf<Car>()
        val sortedIndex = mutableListOf<Int>()
        for (i in carData.value!!) {
            sortedIndex.add(i.index)
        }
        sortedIndex.sort()
        Log.d(tag, sortedIndex.toString())
        for (i in sortedIndex) {
            for (j in carData.value!!) {
                if (i == j.index) {
                    sortedList.add(j)
                }
            }
        }
        carData.value = sortedList
    }

    private fun loadJSONFromAsset() {
        var json: String? = null
        json = try {
            val `is` = inputStream
            val size: Int = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            val charset: Charset = Charsets.UTF_8
            String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
        }.toString()
        val gson = Gson()
        val data = gson.fromJson(json, Cars::class.java)
        carData.value = data.cars
        Log.d(tag, data.cars[0].modelName)
    }


    fun getCarsDataByName() {
        val sortedList = mutableListOf<Car>()
        val sortedName = mutableListOf<String>()
        for (i in carData.value!!) {
            sortedName.add(i.modelName)
        }
        sortedName.sort()
        Log.d(tag, sortedName.toString())
        for (i in sortedName) {
            for (j in carData.value!!) {
                if (i == j.modelName) {
                    sortedList.add(j)
                }
            }
        }
        carData.value = sortedList

    }
}