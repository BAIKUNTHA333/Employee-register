package com.example.employeeregister

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employeeregister.db.Employee
import com.example.employeeregister.db.EmployeeDao
import kotlinx.coroutines.launch

class EmployeeViewModel(private val dao: EmployeeDao): ViewModel() {

    val employee = dao.getAllEmployee()

    fun insertEmployee(employee: Employee)=viewModelScope.launch {
        dao.insertEmployee(employee)
    }

    fun updateEmployee(employee: Employee)=viewModelScope.launch {
        dao.updateEmployee(employee)
    }

    fun deleteEmployee(employee: Employee)=viewModelScope.launch {
        dao.deleteEmployee(employee)
    }


}