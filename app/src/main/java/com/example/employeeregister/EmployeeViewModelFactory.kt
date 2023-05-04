package com.example.employeeregister

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.employeeregister.db.EmployeeDao
import java.lang.IllegalArgumentException

class EmployeeViewModelFactory(
    private val dao: EmployeeDao
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EmployeeViewModel::class.java)){
            return EmployeeViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}