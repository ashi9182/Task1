package com.example.task1.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.task1.R
import com.example.task1.adapter.CarsAdapter
import com.example.task1.api.EmployeeInterface
import com.example.task1.models.Car
import com.example.task1.models.Cars
import com.example.task1.ui.viewmodelFactory.CarViewModelFactory
import com.example.task1.ui.viewmodels.CarActivityViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_car.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset


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
        val application = requireNotNull(this).application
        val carViewModelFactory = CarViewModelFactory(application)
        carActivityViewModel = ViewModelProvider(this,carViewModelFactory).get(CarActivityViewModel::class.java)
        setObservables()
    }

    private fun setObservables() {
        val carsDetails: Observer<List<Car>> = Observer<List<Car>> { carsDetails ->
            rv_cars.adapter = CarsAdapter(carsDetails)
        }
        carActivityViewModel.carData.observe(this, carsDetails)

        val details: Observer<Cars> = Observer<Cars> { details ->
            if(details != null) {
                carActivityViewModel.handleData()
            }
        }
        carActivityViewModel.remoteCarDetails.observe(this, details)
    }


    fun nameSort(view: View) {
        carActivityViewModel.getCarsDataByName()

    }

    fun indexSort(view: View) {
        carActivityViewModel.getCarsDataByIndex()
    }
}