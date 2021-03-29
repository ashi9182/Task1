package com.example.task1.ui.viewmodelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.task1.ui.viewmodels.HomeActivityViewModel

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(private val id : Int): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeActivityViewModel::class.java)){
            return HomeActivityViewModel(id) as T
        }
        throw IllegalArgumentException("ViewModel class not found")
    }
}