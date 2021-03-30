package com.example.task1.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.task1.R
import com.example.task1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        val view = binding.root
//        setContentView(view)
        onClickListeners()
    }

    private fun onClickListeners() {
        binding.btnLogin.setOnClickListener {
            Log.d(tag,binding.eTUsername.text.toString())
            if(binding.eTUsername.text.isNotEmpty()) {
                goToHome()
            }
        }
    }

    private fun goToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("USER_ID",binding.eTUsername.text.toString().toInt())
        startActivity(intent)
    }
}