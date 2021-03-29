package com.example.task1.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.task1.models.Employee
import com.example.task1.repositories.EmployeeRepository

class HomeActivityViewModel(val id : Int) : ViewModel() {
    val tag = "AddressViewModel"

    val employeeDetails : LiveData<Employee> by lazy {

        EmployeeRepository().get(id)

    }
}