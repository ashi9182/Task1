package com.example.task1.ui.viewmodelFactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.task1.ui.viewmodels.CarActivityViewModel

class CarViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CarActivityViewModel::class.java)) {
            return CarActivityViewModel( application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}