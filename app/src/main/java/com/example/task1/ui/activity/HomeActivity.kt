package com.example.task1.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.task1.databinding.ActivityHomeBinding
import com.example.task1.models.Employee
import com.example.task1.ui.viewmodelFactory.HomeViewModelFactory
import com.example.task1.ui.viewmodels.HomeActivityViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.OnCompleteListener


class HomeActivity : AppCompatActivity() {

    companion object{
        var coordinates : LatLng ? = LatLng(-33.86, 151.2)
        var latLng  :String ? = null
    }
    private lateinit var mGoogleSignInClient : GoogleSignInClient
    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeViewModel: HomeActivityViewModel
    private val tag = "HomeActivity"
    private var user: Employee? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        val id = intent.getIntExtra("USER_ID", 1)
        latLng = intent.getStringExtra("LatLng")
        setContentView(view)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        initializeViewModel(id)
        onClickListeners()

//        getUserData()

        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            Log.d(tag, "Hi")
            val personName = acct.displayName
            val personGivenName = acct.givenName
            val personFamilyName = acct.familyName
            val personEmail = acct.email
            val personId = acct.id
            val personPhoto: Uri? = acct.photoUrl

            val text = "Welcome $personName"
            binding.tvWelcomeHome.text = text
            binding.tvWelcomeHome.visibility = View.VISIBLE

            if(latLng != null){
                binding.tvLocationText.text = latLng
            }
        }

    }

    private fun onClickListeners() {
        binding.btnLocation.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }
        binding.btnSignout.setOnClickListener {
            signOut()
        }
    }

    private fun signOut() {
        mGoogleSignInClient.signOut()
            .addOnCompleteListener(this) {
                Toast.makeText(this,"User signed out",Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
    }


    private fun initializeViewModel(id: Int) {
        val homeViewModelFactory = HomeViewModelFactory(id)
        homeViewModel = ViewModelProvider(this, homeViewModelFactory).get(HomeActivityViewModel::class.java)
    }

    private fun getUserData() {
        if (user == null) {
            val employeeDetails: Observer<Employee> = Observer<Employee> { employeeDetails ->
                Log.d(tag, employeeDetails.data.employee_name)
                user = employeeDetails
                val text = "Welcome " + user?.data?.employee_name
                binding.tvWelcomeHome.text = text
                binding.tvWelcomeHome.visibility = View.VISIBLE
            }
            homeViewModel.employeeDetails.observe(this, employeeDetails)
        }
    }

    fun goToCar(view: View) {
        val intent = Intent(this, CarActivity::class.java)
        startActivity(intent)
    }
}