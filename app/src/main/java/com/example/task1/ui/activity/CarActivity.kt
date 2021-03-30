package com.example.task1.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.task1.R
import com.example.task1.adapter.CarsAdapter
import com.example.task1.api.EmployeeInterface
import com.example.task1.models.Car
import com.example.task1.models.Cars
import com.example.task1.models.Employee
import com.example.task1.ui.viewmodels.CarActivityViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_car.*

class CarActivity : AppCompatActivity() {
    lateinit var carActivityViewModel: CarActivityViewModel
    var carsIndex: List<Car>? = null
    var carsName: List<Car>? = null
    private val tag = "CarsActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car)
        initializeViewModel()
    }

    private fun initializeViewModel() {
        carActivityViewModel = ViewModelProvider(this).get(CarActivityViewModel::class.java)
        setObservables()

    }

    private fun setObservables() {
        if (carsIndex == null) {
            val carsIndexDetails: Observer<List<Car>> = Observer<List<Car>> { indexDetails ->
                carsIndex = indexDetails
            }
            carActivityViewModel.cars.observe(this, carsIndexDetails)
        }
        if (carsName == null) {
            val carsNameDetails: Observer<List<Car>> = Observer<List<Car>> { nameDetails ->
                carsName = nameDetails
                rv_cars.adapter = CarsAdapter(carsName!!)
            }
            carActivityViewModel.sortedCarsName.observe(this, carsNameDetails)
        }

    }

    fun nameSort(view: View) {
        if (carsName != null) {
            rv_cars.adapter = CarsAdapter(carsName!!)
        }
    }

    fun indexSort(view: View) {
        if (carsIndex != null){
            rv_cars.adapter = CarsAdapter(carsIndex!!)
        }
    }
}