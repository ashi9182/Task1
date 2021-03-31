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
        setAdapter()
    }

    private fun setAdapter() {
        rv_cars.adapter = CarsAdapter(carActivityViewModel.sortedCarsName)
    }

    fun nameSort(view: View) {
        rv_cars.adapter = CarsAdapter(carActivityViewModel.sortedCarsName)

    }

    fun indexSort(view: View) {
        rv_cars.adapter = CarsAdapter(carActivityViewModel.cars)

    }
}