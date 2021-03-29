package com.example.task1.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.task1.databinding.ActivityHomeBinding
import com.example.task1.models.Employee
import com.example.task1.ui.viewmodelFactory.HomeViewModelFactory
import com.example.task1.ui.viewmodels.HomeActivityViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    lateinit var homeViewModel: HomeActivityViewModel
    private val tag = "HomeActivity"
    var user: Employee? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        val id = intent.getIntExtra("USER_ID",1)
        setContentView(view)
        initializeViewModel(id)
        getUserData()

    }

    private fun initializeViewModel( id : Int) {
        val homeViewModelFactory = HomeViewModelFactory(id)
        homeViewModel = ViewModelProvider(this,homeViewModelFactory).get(HomeActivityViewModel::class.java)
    }

    private fun getUserData() {
        if (user == null) {
            val employeeDetails: Observer<Employee> = Observer<Employee> { employeeDetails ->
                Log.d(tag, employeeDetails.message)
                Log.d(tag, employeeDetails.data.employee_name)
                user = employeeDetails
                val text = "Welcome " + user?.data?.employee_name
                binding.tvWelcomeHome.text = text
                binding.tvWelcomeHome.visibility = View.VISIBLE
            }
            homeViewModel.employeeDetails.observe(this, employeeDetails)
        }
    }
}